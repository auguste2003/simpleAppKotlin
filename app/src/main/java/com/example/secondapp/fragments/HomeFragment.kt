package com.example.secondapp.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondapp.R

class HomeFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Il peut arriver qu'il n'est pas de inflater
        return inflater?.inflate(R.layout.fragment_home, container ,false)
    }
}