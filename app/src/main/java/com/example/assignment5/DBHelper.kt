package com.example.assignment5

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                DIST_COL + " TEXT," +
                SWIM_COL + " TEXT," +
                CALORIES_COL + " TEXT" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    fun save(dist : Float, swim : Float, calories : Int ){


        val values = ContentValues()


        values.put(DIST_COL, dist)
        values.put(SWIM_COL, swim)
        values.put(CALORIES_COL, calories)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)

        db.close()
    }


    fun getName(): Cursor? {

        val db = this.readableDatabase

        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }


    companion object{

        private val DATABASE_NAME = "VALUES"
        private val DATABASE_VERSION = 1

        val TABLE_NAME = "gfg_table"

        val ID_COL = "id"

        val DIST_COL = "dist"

        val SWIM_COL = "swim"

        val CALORIES_COL = "calories"
    }
}
