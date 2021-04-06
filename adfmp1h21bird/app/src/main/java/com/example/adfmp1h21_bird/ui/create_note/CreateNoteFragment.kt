package com.example.adfmp1h21_bird.ui.create_note

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adfmp1h21_bird.R

class CreateNoteFragment : Fragment() {

//    companion object {
//        fun newInstance() = PageFragment()
//    }

    private lateinit var viewModelCreate: CreateNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View =  inflater.inflate(R.layout.fragment_create_note, container, false)
        viewModelCreate = ViewModelProvider(this).get(CreateNoteViewModel::class.java)


        return rootView
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        // TODO: Use the ViewModel
//    }

}