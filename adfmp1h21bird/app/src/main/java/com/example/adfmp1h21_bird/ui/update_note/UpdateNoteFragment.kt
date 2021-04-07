package com.example.adfmp1h21_bird.ui.update_note

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.navigation.fragment.findNavController
import com.example.adfmp1h21_bird.R
import com.example.adfmp1h21_bird.note.myNote

class UpdateNoteFragment : Fragment() {

//    companion object {
//        fun newInstance() = PageFragment()
//    }

    private lateinit var viewModelUpdate: UpdateNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View =  inflater.inflate(R.layout.fragment_update_note, container, false)
        viewModelUpdate = ViewModelProvider(this).get(UpdateNoteViewModel::class.java)

        val image: ImageView = rootView.findViewById(R.id.update_ImageView)
        val name: EditText = rootView.findViewById(R.id.update_name_editText)
        val geotag: TextView = rootView.findViewById(R.id.update_geotag_textView)
        val tags: EditText = rootView.findViewById(R.id.update_tags_editText)
        val date: EditText = rootView.findViewById(R.id.update_date_editText)
        val comment: EditText = rootView.findViewById(R.id.update_comment_editText)

        val NoteID:String = arguments?.getString("NoteID").toString()
        val isNewNote: Boolean? = arguments?.getBoolean("isNewNote")

        if ( !isNewNote!!){

            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                    context?.resources?.getString(R.string.nav_note_update)

            val note:myNote = getData(NoteID)
            image.setImageResource(note.ImageId)
            name.setText(note.name)
            geotag.text =note.geotag
            tags.setText(note.tags)
            date.setText(note.date)
            comment.setText(note.comment)
        }


        val image_button: Button  = rootView.findViewById(R.id.update_ImageView_button)
        val geotag_button: AppCompatImageButton = rootView.findViewById(R.id.update_geotag_button)
        val save_button: Button = rootView.findViewById(R.id.update_save_button)

        image_button.setOnClickListener {
            Toast.makeText(context, "Change image", Toast.LENGTH_SHORT).show()
        }

        geotag_button.setOnClickListener {
            Toast.makeText(context, "Change geolocation", Toast.LENGTH_SHORT).show()
        }

        save_button.setOnClickListener {
            Toast.makeText(context, "Save note", Toast.LENGTH_SHORT).show()
            if(isNewNote){
                findNavController().popBackStack()
            }else{
                val bundle = Bundle()
                bundle.putString("NoteID",NoteID)
                findNavController().navigate(R.id.action_nav_update_note_to_nav_note_page,bundle)
            }

        }




//        val textView:TextView = rootView.findViewById(R.id.create_text)
//        textView.text = "Create new note"

        return rootView
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        // TODO: Use the ViewModel
//    }

    private fun getData(NoteID:String): myNote {
        // TODO получение данных

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

}