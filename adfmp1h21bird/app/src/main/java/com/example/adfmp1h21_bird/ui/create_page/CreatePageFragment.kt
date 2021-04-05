package com.example.adfmp1h21_bird.ui.create_page

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adfmp1h21_bird.R

class CreatePageFragment : Fragment() {

//    companion object {
//        fun newInstance() = PageFragment()
//    }

    private lateinit var viewModelCreate: CreatePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View =  inflater.inflate(R.layout.fragment_create_page, container, false)
        viewModelCreate = ViewModelProvider(this).get(CreatePageViewModel::class.java)


        return rootView
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        // TODO: Use the ViewModel
//    }

}