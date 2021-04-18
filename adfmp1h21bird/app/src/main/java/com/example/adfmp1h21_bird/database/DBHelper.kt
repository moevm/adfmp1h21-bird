package com.example.adfmp1h21_bird.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "BirdDB", null, 1) {
    val TABLE_NAME = "Birds";

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $TABLE_NAME( _id long primary key, name text, imageURI text, geotag text, tags text, date text, comment text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists $TABLE_NAME")
    }
}