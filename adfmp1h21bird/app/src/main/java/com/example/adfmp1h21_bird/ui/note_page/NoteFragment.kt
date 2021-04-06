package com.example.adfmp1h21_bird.ui.note_page

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.adfmp1h21_bird.R

class NoteFragment : Fragment() {

//    companion object {
//        fun newInstance() = NoteFragment()
//    }

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        val rootView: View = inflater.inflate(R.layout.fragment_note, container, false)


        val NoteID:String? = arguments?.getString("NoteID")
        val note_textView: TextView = rootView.findViewById(R.id.note_TextView)
        note_textView.text =NoteID

        return rootView
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//    }

}