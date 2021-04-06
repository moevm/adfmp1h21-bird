package com.example.adfmp1h21_bird.ui.change_note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.adfmp1h21_bird.R

class ChangeNoteFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_change_note, container, false)


        val NoteID:String? = arguments?.getString("NoteID")

        val changeTextView: TextView = rootView.findViewById(R.id.change_textView)
        changeTextView.text = "change note with ID $NoteID"

        return rootView
    }


}