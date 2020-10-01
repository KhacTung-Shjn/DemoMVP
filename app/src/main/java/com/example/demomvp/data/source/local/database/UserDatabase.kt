package com.example.demomvp.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.demomvp.data.model.User

class UserDatabase(
    context: Context,
    dbName: String,
    version: Int
) : SQLiteOpenHelper(context, dbName, null, version) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_LOGIN)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_TABLE_TASK)
        onCreate(db)
    }

    companion object {
        private const val NAME_DB = "login.db"
        private const val VERSION = 1

        private const val CREATE_TABLE_LOGIN = "CREATE TABLE " + User.TABLE_NAME + "(" +
                User.USER_NAME + " TEXT PRIMARY KEY, " +
                User.PASSWORD + " TEXT)"

        private const val DROP_TABLE_TASK = "DROP TABLE IF EXISTS " + User.TABLE_NAME

        private var instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
            instance ?: synchronized(this) {
                instance ?: UserDatabase(context, NAME_DB, VERSION).also { instance = it }
            }
    }
}
