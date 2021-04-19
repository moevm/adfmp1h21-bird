package com.example.adfmp1h21_bird.ui.update_note

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
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
import com.example.adfmp1h21_bird.database.NoteDatabase
import com.example.adfmp1h21_bird.note.MyNote
import java.io.File
import java.net.URI

class UpdateNoteFragment : Fragment() {

//    companion object {
//        fun newInstance() = PageFragment()
//    }
    var image: ImageView? = null
    var name: EditText? = null
    var geotag: TextView? = null
    var tags: EditText? = null
    var date: EditText? = null
    var comment: EditText? = null
    var isNewNote = false
    var isImageChanged = false

    var note: MyNote? = null
    var oldImage: String = ""
    var newImageURI: String = ""

    private lateinit var viewModelUpdate: UpdateNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View =  inflater.inflate(R.layout.fragment_update_note, container, false)
        viewModelUpdate = ViewModelProvider(this).get(UpdateNoteViewModel::class.java)

        this.image = rootView.findViewById(R.id.update_ImageView)
        this.name = rootView.findViewById(R.id.update_name_editText)
        this.geotag = rootView.findViewById(R.id.update_geotag_textView)
        this.tags = rootView.findViewById(R.id.update_tags_editText)
        this.date = rootView.findViewById(R.id.update_date_editText)
        this.comment = rootView.findViewById(R.id.update_comment_editText)

        val noteArg = arguments?.getString("NoteID")
        var noteId: Long = 0
        if(noteArg != null){
            noteId = noteArg!!.toLong()
        }
        isNewNote = arguments?.getBoolean("isNewNote") == true

        if (!isNewNote){

            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                    context?.resources?.getString(R.string.nav_note_update)

            getData(noteId)
            image?.setImageResource(note!!.ImageURI.toInt())
            name?.setText(note!!.name)
            geotag?.text = note!!.geotag
            tags?.setText(note!!.tags)
            date?.setText(note!!.date)
            comment?.setText(note!!.comment)
        }


        val imageButton: Button  = rootView.findViewById(R.id.update_ImageView_button)
        val geotagButton: AppCompatImageButton = rootView.findViewById(R.id.update_geotag_button)
        val saveButton: Button = rootView.findViewById(R.id.update_save_button)

        imageButton.setOnClickListener {
            Toast.makeText(context, "Change image", Toast.LENGTH_SHORT).show()
            if(ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 2000)
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

            if(isNewNote){
                if(newImageURI.isEmpty()) {
                    Toast.makeText(context, "Select an image at first", Toast.LENGTH_SHORT).show()
                }
                else {
                    // copy image
                    var fin = File(newImageURI)
                    var fout = File(requireContext().filesDir,"${System.currentTimeMillis()}.jpg")
//                    fout.createNewFile()
                    fin.copyTo(fout)

                    this.newImageURI = Uri.fromFile(fout).path.toString() // TODO

                    note = MyNote(0, this.name!!.text.toString(), this.newImageURI, this.geotag!!.text.toString(), this.tags!!.text.toString(), this.date!!.text.toString(), this.comment!!.text.toString())
                    note?.let { it1 -> NoteDatabase.getInstance(requireContext()).addNote(it1) }
                    Log.d("BIRD_DATABASE", "after adding new: ${NoteDatabase.getInstance(requireContext()).getAllNotes().toString()}")
                    findNavController().popBackStack()
                }
            }else{
                if(isImageChanged) {
                    note?.let { it1 ->
                        //copy new
                        var fin = File(it1.ImageURI)
                        var fout = File(requireContext().filesDir,"${System.currentTimeMillis()}.jpg")
//                        fout.createNewFile()
                        fin.copyTo(fout)
                        it1.ImageURI = getRealPathUri(Uri.fromFile(fout)).toString()
                        //delete old
                        var fdel = File(oldImage)
                        fdel.delete()
                    }
                }
                note?.let { it ->
                    it.name = this.name!!.text.toString()
                    it.geotag = this.geotag!!.text.toString()
                    it.tags = this.tags!!.text.toString()
                    it.date = this.date!!.text.toString()
                    it.comment = this.comment!!.text.toString()
                }
                note?.let { it1 -> NoteDatabase.getInstance(requireContext()).updateNote(it1) }
                Log.d("BIRD_DATABASE", "after updating old: ${NoteDatabase.getInstance(requireContext()).getAllNotes()}")
                val bundle = Bundle()
                bundle.putString("NoteID", noteId.toString())
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
                if (!isNewNote && !isImageChanged) {
                    this.oldImage = note!!.ImageURI
                    note!!.ImageURI = returnURI?.let { getRealPathUri(it) }.toString()
                    this.isImageChanged = true
                }
//                val bitmapImage = MediaStore.Images.Media.getBitmap(activity?.contentResolver, returnURI)
//                this.image?.setImageBitmap(bitmapImage)
                if (isNewNote){
                    this.newImageURI = returnURI?.let { getRealPathUri(it) }.toString()
                }
//                note!!.ImageURI = returnURI.toString()
                this.image?.setImageURI(returnURI)
            }
        }
    }
    private fun getRealPathUri(contentUri: Uri): String{
        val result: String
        val cursor = activity?.contentResolver?.query(contentUri, null, null, null, null)
        if (cursor == null){
            result = contentUri.path.toString();
        }
        else{
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        // TODO: Use the ViewModel
//    }

    private fun getData(NoteID:Long) {
        context?.let {
            var temp = NoteDatabase.getInstance(it).getNoteById(NoteID)
            if(temp == null)
                temp = MyNote(
                        NoteID,
                    "Неро $NoteID",
                    R.drawable.test.toString(),
                    "Какой-то geotag",
                    "Птичка, девочка, красная",
                    "31.03.2077",
                    "Суперптичка которую я увидел в Найт Сити, проезжая на своей тачке."
                )
            this.note = temp
        }
    }
    }
