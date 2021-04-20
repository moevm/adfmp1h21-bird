package com.example.adfmp1h21_bird.database

import android.content.ContentValues
import android.content.Context
import com.example.adfmp1h21_bird.note.MyNote
import com.example.adfmp1h21_bird.util.SingletonHolder

class NoteDatabase private constructor(context: Context){
    private var helper: DBHelper// = DBHelper(context);
    //private var database: SQLiteDatabase;
    init{
        helper = DBHelper(context);
    }
    companion object : SingletonHolder<NoteDatabase, Context>(::NoteDatabase)

    fun getAllNotes(): List<MyNote>{
        val notes = ArrayList<MyNote>()
        val cursor = helper.writableDatabase.rawQuery("SELECT * FROM ${helper.TABLE_NAME}", null)
        try{
            if (cursor.count>0) {
                cursor.moveToFirst()
                do {
                    val note = MyNote(cursor.getInt(0)
                            , cursor.getString(1)
                            , cursor.getString(2)
                            , cursor.getString(3)
                            , cursor.getString(4)
                            , cursor.getString(5)
                            , cursor.getString(6))
                    notes.add(note)
                } while (cursor.moveToNext())
            }
        }catch (e : Exception){
            e.printStackTrace()
        }finally {
            cursor.close()
        }
        return notes
    }

    // Картинку устанавливать так: imageView.setImageURI(Uri.fromFile(File(note.imageURI)))
    // Это говнокод, но вроде работает
    fun getNoteById(Id: Int): MyNote?{
        var note: MyNote? = null

        var notes = getAllNotes()

        val cursor = helper.writableDatabase.rawQuery("SELECT * FROM ${helper.TABLE_NAME} WHERE _id = $Id", null)
        try{
            if (cursor.count > 0){
                cursor.moveToFirst()
              note = MyNote(cursor.getInt(0)
                        ,cursor.getString(1)
                        ,cursor.getString(2)
                        ,cursor.getString(3)
                        ,cursor.getString(4)
                        ,cursor.getString(5)
                        ,cursor.getString(6))
            }
        }catch (e: Exception){
            e.printStackTrace()
        }finally {
            cursor.close()
        }
        return note
    }
    fun updateNote(note: MyNote){
        val content = ContentValues()
        content.put("_id", note.ID)
        content.put("name", note.name)
        content.put("imageURI", note.ImageURI)
        content.put("geotag", note.geotag)
        content.put("tags", note.tags)
        content.put("date", note.date)
        content.put("comment", note.comment)
        helper.writableDatabase.replace(helper.TABLE_NAME,null, content)
    }
    fun addNote(note: MyNote): Int{ // Игнорирует Id в note, возвращает валидный Id
        val content = ContentValues()
//        content.put("_id", note.ID)
        content.put("name", note.name)
        content.put("imageURI", note.ImageURI)
        content.put("geotag", note.geotag)
        content.put("tags", note.tags)
        content.put("date", note.date)
        content.put("comment", note.comment)
        val id = helper.writableDatabase.insert(helper.TABLE_NAME,null, content)
        helper.close()
        return id.toInt()
    }
    fun deleteNote(Id: Int){
        helper.writableDatabase.delete(helper.TABLE_NAME, "_id = $Id",null)
        helper.close()
    }

    fun clearDatabase(){
        helper.writableDatabase.execSQL("delete from ${helper.TABLE_NAME}")
    }
}