package com.example.adfmp1h21_bird.ui.note_page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.adfmp1h21_bird.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NoteFragment : Fragment() {

//    companion object {
//        fun newInstance() = NoteFragment()
//    }

    private lateinit var fab: FloatingActionButton
    private lateinit var fab_edit: FloatingActionButton
    private lateinit var fab_share: FloatingActionButton
    private lateinit var fab_delete: FloatingActionButton

    private var isFABOpen:Boolean = false

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        val rootView: View = inflater.inflate(R.layout.fragment_note, container, false)

        val NoteID:String? = arguments?.getString("NoteID")

        prepareFAB(rootView)
        fab_edit.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("NoteID", NoteID)
            findNavController().navigate(R.id.nav_change_note, bundle)
        }

        fab_share.setOnClickListener {
            // TODO поделиться заметкой
            Toast.makeText(context, "Share this note!", Toast.LENGTH_SHORT).show()
        }

        fab_delete.setOnClickListener {
            // TODO удалить из базы
            Toast.makeText(context, "Delete  this note!", Toast.LENGTH_SHORT).show()
        }




        val note_textView: TextView = rootView.findViewById(R.id.note_TextView)
        note_textView.text =NoteID

        return rootView
    }

    private fun prepareFAB(rootView:View){
        fab = rootView.findViewById(R.id.note_fab)
        fab_edit = rootView.findViewById(R.id.note_fab_edit)
        fab_share = rootView.findViewById(R.id.note_fab_delete)
        fab_delete = rootView.findViewById(R.id.note_fab_share)

        fab_edit.y = -resources.getDimension(R.dimen.standard_105)
        fab_share.y = -resources.getDimension(R.dimen.standard_105)
        fab_delete.y = -resources.getDimension(R.dimen.standard_155)

        fab.setOnClickListener {
            if(!isFABOpen){
                showFABMenu()
            }else{
                closeFABMenu()
            }
        }
    }

    private fun showFABMenu() {
        isFABOpen = true
        fab_delete.animate().translationY(0F)
        fab_share.animate().translationY(0F)
        fab_edit.animate().translationY(0F)
    }

    private fun closeFABMenu() {
        isFABOpen = false
        fab_edit.animate().translationY(-resources.getDimension(R.dimen.standard_105))
        fab_share.animate().translationY(-resources.getDimension(R.dimen.standard_105))
        fab_delete.animate().translationY(-resources.getDimension(R.dimen.standard_155))
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//    }

}