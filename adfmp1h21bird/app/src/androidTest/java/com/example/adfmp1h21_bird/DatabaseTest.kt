package com.example.adfmp1h21_bird

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.adfmp1h21_bird.database.NoteDatabase
import com.example.adfmp1h21_bird.note.MyNote
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DatabaseTest {


    @Test
    fun isInsertionCorrect(){
        val context: Context = ApplicationProvider.getApplicationContext()
        NoteDatabase.getInstance(context).clearDatabase()
        val note = MyNote(0,"1","2","3","4","5","6")
        note.ID = NoteDatabase.getInstance(context).addNote(note)
        // одинаковые объекты генерируют одинаковые строки
        assertEquals(note.toString(), NoteDatabase.getInstance(context).getNoteById(note.ID).toString())
        NoteDatabase.getInstance(context).clearDatabase()
    }

    @Test
    fun isDeleteCorrect(){
        val context: Context = ApplicationProvider.getApplicationContext()
        val note = MyNote(0,"1","2","3","4","5","6")
        note.ID = NoteDatabase.getInstance(context).addNote(note)

        NoteDatabase.getInstance(context).deleteNote(note.ID)
        assertEquals(null, NoteDatabase.getInstance(context).getNoteById(note.ID))
        // delete already deleted

        NoteDatabase.getInstance(context).deleteNote(note.ID)
    }

    @Test
    fun isUpdateCorrect(){
        val context: Context = ApplicationProvider.getApplicationContext()
        val note0 = MyNote(0,"1","2","3","4","5","6")
        NoteDatabase.getInstance(context).addNote(note0)
        val note = MyNote(0,"1","2","3","4","5","6")
        val newName = "name"
        val newImage = "img"
        val newGeoTag = "geotag"
        val newTags = "tags"
        val newDate = "date"
        val newComment = "comment"
        note.ID = NoteDatabase.getInstance(context).addNote(note)

        note.name = newName
        NoteDatabase.getInstance(context).updateNote(note)
        assertEquals(newName, NoteDatabase.getInstance(context).getNoteById(note.ID)!!.name)

        note.ImageURI = newImage
        NoteDatabase.getInstance(context).updateNote(note)
        assertEquals(newImage, NoteDatabase.getInstance(context).getNoteById(note.ID)!!.ImageURI)

        note.geotag = newGeoTag
        NoteDatabase.getInstance(context).updateNote(note)
        assertEquals(newGeoTag, NoteDatabase.getInstance(context).getNoteById(note.ID)!!.geotag)

        note.tags = newTags
        NoteDatabase.getInstance(context).updateNote(note)
        assertEquals(newTags, NoteDatabase.getInstance(context).getNoteById(note.ID)!!.tags)

        note.date = newDate
        NoteDatabase.getInstance(context).updateNote(note)
        assertEquals(newDate, NoteDatabase.getInstance(context).getNoteById(note.ID)!!.date)

        note.comment = newComment
        NoteDatabase.getInstance(context).updateNote(note)
        assertEquals(newComment, NoteDatabase.getInstance(context).getNoteById(note.ID)!!.comment)

        NoteDatabase.getInstance(context).clearDatabase()
    }

    @Test
    fun isGetListCorrect(){
        val context: Context = ApplicationProvider.getApplicationContext()
        NoteDatabase.getInstance(context).clearDatabase()
        // Empty list
        assertEquals(ArrayList<MyNote>(), NoteDatabase.getInstance(context).getAllNotes())
        // Add one
        val len = NoteDatabase.getInstance(context).getAllNotes().size
        val note = MyNote(0,"1","2","3","4","5","6")
        note.ID = NoteDatabase.getInstance(context).addNote(note)
        assertEquals(len + 1, NoteDatabase.getInstance(context).getAllNotes().size)
        NoteDatabase.getInstance(context).clearDatabase()
    }
}