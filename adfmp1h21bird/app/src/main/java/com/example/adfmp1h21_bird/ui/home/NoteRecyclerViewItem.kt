package com.example.adfmp1h21_bird.ui.home

import android.view.View

class NoteRecyclerViewItem(val name:String,
                           var imageId:Int,
                           val NoteId:String,
                           val tags: String) {

    fun setNoteImage(NoteImageId: Int){
        this.imageId = NoteImageId
    }
}

interface OnNoteClickListener{
    fun onNoteClick(v: View?, note: NoteRecyclerViewItem)
}