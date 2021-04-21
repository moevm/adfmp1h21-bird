package com.example.adfmp1h21_bird.ui.note_page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.adfmp1h21_bird.R
import com.example.adfmp1h21_bird.database.NoteDatabase
import com.example.adfmp1h21_bird.note.MyNote
import com.google.android.material.floatingactionbutton.FloatingActionButton


class NoteFragment : Fragment() {

//    companion object {
//        fun newInstance() = NoteFragment()
//    }

    private lateinit var fab: FloatingActionButton
    private lateinit var fabEdit: FloatingActionButton
    private lateinit var fabShare: FloatingActionButton
    private lateinit var fabDelete: FloatingActionButton

    private var isFABOpen:Boolean = false

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO: Use the ViewModel
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        val rootView: View = inflater.inflate(R.layout.fragment_note, container, false)

        val noteId:String = arguments?.getString("NoteID").toString()

        prepareFAB(rootView)
        fabEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("NoteID", noteId)
            bundle.putBoolean("isNewNote", false)
            findNavController().navigate(R.id.nav_update_note,bundle)
        }

        fabShare.setOnClickListener {
            // TODO поделиться заметкой
            Toast.makeText(context, "Share this note!", Toast.LENGTH_SHORT).show()
        }

        fabDelete.setOnClickListener {
            context?.let {
                NoteDatabase.getInstance(it).deleteNote(noteId.toInt())
            }
            Toast.makeText(context, "Note deleted", Toast.LENGTH_SHORT).show()

            val bundle = Bundle()
            findNavController().navigate(R.id.action_nav_note_page_to_nav_home, bundle)
        }

        val name: TextView = rootView.findViewById(R.id.note_name_textView)
        val geotag: TextView = rootView.findViewById(R.id.note_geotag_textView)
        val tags: TextView = rootView.findViewById(R.id.note_tags_textView)
        val date: TextView = rootView.findViewById(R.id.note_date_textView)
        val comment: TextView = rootView.findViewById(R.id.note_comment_textView)
        val image: ImageView = rootView.findViewById(R.id.note_imageView)

        val note = getData(noteId)
        name.text = note!!.name
        geotag.text = note!!.geotag
        tags.text = note.tags
        date.text = note.date
        comment.text = note.comment
        image.setImageURI(Uri.parse(note.ImageURI))

        val geotagButton: AppCompatImageButton = rootView.findViewById(R.id.note_geotag_button)
        geotagButton.setOnClickListener{
            val gmmIntentUri = Uri.parse("geo:37.7749,-122.4194")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
//            mapIntent.resolveActivity(packageManager)?.let {
//                startActivity(mapIntent)
//            }

            startActivity(mapIntent)

        }


        return rootView
    }


    private fun getData(NoteID:String): MyNote? {
        var note = NoteDatabase.getInstance(requireContext()).getNoteById(NoteID.toInt())


        if (note == null) {
            note = MyNote(
                NoteID.toInt(),
                "Неро $NoteID",
                R.drawable.test.toString(),
                "Какой-то geotag",
                "Птичка, днвочка, красная",
                "31.03.2077",
                "Суперптичка которую я увидел в Найт Сити, проезжая на своей тачке."
            )
        }

        return note
    }

    private fun prepareFAB(rootView:View){
        fab = rootView.findViewById(R.id.note_fab)
        fabEdit = rootView.findViewById(R.id.note_fab_edit)
        fabShare = rootView.findViewById(R.id.note_fab_share)
        fabDelete = rootView.findViewById(R.id.note_fab_delete)

        fabEdit.y = -resources.getDimension(R.dimen.standard_105)
        fabShare.y = -resources.getDimension(R.dimen.standard_105)
        fabDelete.y = -resources.getDimension(R.dimen.standard_155)

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
        fabDelete.animate().translationY(0F)
        fabShare.animate().translationY(0F)
        fabEdit.animate().translationY(0F)
    }
    private fun closeFABMenu() {
        isFABOpen = false
        fabEdit.animate().translationY(-resources.getDimension(R.dimen.standard_105))
        fabShare.animate().translationY(-resources.getDimension(R.dimen.standard_105))
        fabDelete.animate().translationY(-resources.getDimension(R.dimen.standard_155))
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//
//    }

}