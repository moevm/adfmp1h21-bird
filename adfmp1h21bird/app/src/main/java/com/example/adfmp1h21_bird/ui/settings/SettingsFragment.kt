package com.example.adfmp1h21_bird.ui.settings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.adfmp1h21_bird.R

class SettingsFragment : Fragment() {

//    companion object {
//        fun newInstance() = SettingsFragment()
//    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView:View = inflater.inflate(R.layout.fragment_settings, container, false)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        val textView: TextView = rootView.findViewById(R.id.setting_text)
        viewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return rootView
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
////         TODO: Use the ViewModel
//    }

}