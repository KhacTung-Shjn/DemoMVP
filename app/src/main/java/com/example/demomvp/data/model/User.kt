package com.example.demomvp.data.model

import android.content.ContentValues

data class User(val userName: String, val password: String) {

    fun getContentValues() = ContentValues().apply {
        put(USER_NAME, userName)
        put(PASSWORD, password)
    }

    companion object {
        const val TABLE_NAME = "login"
        const val USER_NAME = "user_name"
        const val PASSWORD = "password"
    }

}
