package com.example.adfmp1h21_bird.ui.exit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adfmp1h21_bird.R
import kotlin.system.exitProcess


class ExitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView:View = inflater.inflate(R.layout.fragment_exit, container, false)
        // Inflate the layout for this fragment
        exitProcess(1)
        return rootView
    }

}