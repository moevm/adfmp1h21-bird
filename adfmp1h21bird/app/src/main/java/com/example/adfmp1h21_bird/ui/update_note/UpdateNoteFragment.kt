package com.example.adfmp1h21_bird.ui.update_note

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.adfmp1h21_bird.R
import com.example.adfmp1h21_bird.note.MyNote

class UpdateNoteFragment : Fragment() {

//    companion object {
//        fun newInstance() = PageFragment()
//    }
    var image: ImageView? = null
    var isNewNote = false
    var isImageChanged = false
    var oldImage: Uri? = null

    private lateinit var viewModelUpdate: UpdateNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View =  inflater.inflate(R.layout.fragment_update_note, container, false)
        viewModelUpdate = ViewModelProvider(this).get(UpdateNoteViewModel::class.java)

        this.image = rootView.findViewById(R.id.update_ImageView)
        val name: EditText = rootView.findViewById(R.id.update_name_editText)
        val geotag: TextView = rootView.findViewById(R.id.update_geotag_textView)
        val tags: EditText = rootView.findViewById(R.id.update_tags_editText)
        val date: EditText = rootView.findViewById(R.id.update_date_editText)
        val comment: EditText = rootView.findViewById(R.id.update_comment_editText)

        val noteId:String = arguments?.getString("NoteID").toString()
        val isNewNote: Boolean? = arguments?.getBoolean("isNewNote")

        if ( !isNewNote!!){

            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                    context?.resources?.getString(R.string.nav_note_update)

            val note:MyNote = getData(noteId)
            image?.setImageResource(note.ImageURI.toInt())
            name.setText(note.name)
            geotag.text =note.geotag
            tags.setText(note.tags)
            date.setText(note.date)
            comment.setText(note.comment)
        }


        val imageButton: Button  = rootView.findViewById(R.id.update_ImageView_button)
        val geotagButton: AppCompatImageButton = rootView.findViewById(R.id.update_geotag_button)
        val saveButton: Button = rootView.findViewById(R.id.update_save_button)

        imageButton.setOnClickListener {
            Toast.makeText(context, "Change image", Toast.LENGTH_SHORT).show()
            if(ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 2000)
            }
            val cameraIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            cameraIntent.type = "image/*"
            if(cameraIntent.resolveActivity(activity?.packageManager!!) != null){
                startActivityForResult(cameraIntent, 1000)
            }
        }

        geotagButton.setOnClickListener {
            Toast.makeText(context, "Change geolocation", Toast.LENGTH_SHORT).show()
        }

        saveButton.setOnClickListener {
            Toast.makeText(context, "Save note", Toast.LENGTH_SHORT).show()
            if(isNewNote){
                findNavController().popBackStack()
            }else{
                val bundle = Bundle()
                bundle.putString("NoteID",noteId)
                findNavController().navigate(R.id.action_nav_update_note_to_nav_note_page,bundle)
            }

        }




//        val textView:TextView = rootView.findViewById(R.id.create_text)
//        textView.text = "Create new note"

        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == RESULT_OK){
            if(requestCode == 1000){
                val returnURI = data?.data
//                val bitmapImage = MediaStore.Images.Media.getBitmap(activity?.contentResolver, returnURI)
//                this.image?.setImageBitmap(bitmapImage)
                this.image?.setImageURI(returnURI)
            }
        }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        // TODO: Use the ViewModel
//    }

    private fun getData(NoteID:String): MyNote {
        // TODO получение данных

        val temp = MyNote(
                NoteID.toLong(),
                "Неро $NoteID",
                R.drawable.test.toString(),
                "Какой-то geotag",
                "Птичка, днвочка, красная",
                "31.03.2077",
                "Суперптичка которую я увидел в Найт Сити, проезжая на своей тачке."
        )
        return temp
    }

}