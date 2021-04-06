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
import com.example.adfmp1h21_bird.note.myNote
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

        val NoteID:String = arguments?.getString("NoteID").toString()

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

        val name: TextView = rootView.findViewById(R.id.note_name_textView)
        val geotag: TextView = rootView.findViewById(R.id.note_geotag_textView)
        val tags: TextView = rootView.findViewById(R.id.note_tags_textView)
        val date: TextView = rootView.findViewById(R.id.note_date_textView)
        val comment: TextView = rootView.findViewById(R.id.note_comment_textView)

        val note:myNote = getData(NoteID)

        name.text = note.name
        geotag.text =note.geotag
        tags.text = note.tags
        date.text = note.date
        comment.text = note.comment


        return rootView
    }

    private fun getData(NoteID:String): myNote {

        val temp = myNote(
                NoteID,
                "Неро $NoteID",
                R.drawable.test,
                "Какой-то geotag",
                "Птичка, днвочка, красная",
                "31.03.2077",
                "Суперптичка которую я увидел в Найт Сити, проезжая на своей тачке."

        )
        return temp
    }

    private fun prepareFAB(rootView:View){
        fab = rootView.findViewById(R.id.note_fab)
        fab_edit = rootView.findViewById(R.id.note_fab_edit)
        fab_share = rootView.findViewById(R.id.note_fab_share)
        fab_delete = rootView.findViewById(R.id.note_fab_delete)

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