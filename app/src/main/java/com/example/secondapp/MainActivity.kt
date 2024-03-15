package com.example.secondapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.secondapp.ui.theme.SecondAppTheme
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
//import androidx.navigation.NavController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  //  Greeting("Android")
                 //   UserForm()
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun UserForm(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var pronoun by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nom") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = pronoun,
            onValueChange = { pronoun = it },
            label = { Text("Pronom") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Mot de passe") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Handle the login logic here
                println("Nom: $name, Pronom: $pronoun, Mot de passe: $password")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Soumettre")
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Willkommen bei Tryhard",
        modifier = modifier
    )
}

/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SecondAppTheme {
        Greeting("Android")
    }
}
@Preview(showBackground = true)
@Composable
fun UserFormPreview() {
    SecondAppTheme {
        UserForm()
    }
}
*/
// Petite application de calcul et d'incrementation


@Composable
fun MyApp() {
    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize() /// lA Colonne ocupe tout l'espace disponible dans le container parent
                .padding(16.dp) // Le séparé de 16 ps du composant .
        ) {
            // Header with search bar and notification icon
            Header()

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Grid of 6 boxes
            BoxGrid()

            // Footer
            Footer()

            // Navigation
        //    Navigation(navController)
        }
    }
}

@Composable
fun Header() {
  //  var showNotification by remember{ mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) } // Une variable qui peut déclencher la modification d'un composant
    Row(
        modifier = Modifier
            .fillMaxWidth() // Il remplit en largeur
            .padding(vertical = 8.dp), // il est de 8 dp éloigné
        verticalAlignment = Alignment.CenterVertically // verticlement centré
    ) {
        // Search bar
        OutlinedTextField(
            value = "", // Vide initialement
            onValueChange = { /*TODO*/ },
            modifier = Modifier
                .weight(1f) // eppaisseur des allentours á 1 f
                .padding(end = 8.dp), // 8dp loin des 8.dp
            label = { Text("Search") } //
        )

        // Notification icon
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = Color.Gray, // couleur
            modifier = Modifier
                .size(24.dp) // epaisseur
                .clickable { showDialog = true }

        )
        if (showDialog){
            NotificationDialog (onDismiss ={showDialog =false})


        }
     /*   TextButton(onClick = { showNotification = true }) {
            Text("Show Notification")
        }
        if(showNotification){
Text("Aucune notification ")
        }

      */
    }

}
@Composable
fun NotificationDialog(onDismiss: () -> Unit) { // la founction prend en paramétre un lambda
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Aucune notification pour le moment")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = { onDismiss() }) {
                Text("Fermer")
            }
        }
    }
}

@Composable
fun BoxGrid() {
    var count by remember { mutableStateOf(0) } // Une variable qui peut déclencher une récomposition des élements de compose

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally // aligne aurizontalement
    ) {
        (1..6).forEach { index -> // Chaque index
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp) // Espace de 8dp en vertical
                    .clickable { count += index } // Un box cliquable
                    .background(Color.LightGray, shape = CircleShape) // forme circulaire
                    .size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}
/*
@Composable
fun Navigation(navController: NavController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Footer(navController)
        }
        composable("person") {
            PersonPage(navController)
        }
    }
}
*/

@Composable
fun Footer() {
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()

                .padding(horizontal = 16.dp), // Espace entre les composants

            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // First icon
            IconButton(onClick = { /* Handle click */ }) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }

            // Second icon
            IconButton(onClick = { /* Handle click */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }

            // Third icon
            IconButton(onClick = {  }) {
                Icon(Icons.Default.Person, contentDescription = "Person")
            }
        }
    }
}
/*
@Composable
fun PersonPage(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Person Page")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}

 */
@Preview(showBackground = true )
@Composable
fun DefaultPreview() {
    MyApp()
}