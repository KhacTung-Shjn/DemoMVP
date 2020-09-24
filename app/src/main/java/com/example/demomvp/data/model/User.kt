package com.example.demomvp.data.model

data class User(val userName: String, val password: String) {
    companion object {
        const val TABLE_NAME = "login"
        const val USER_NAME = "user_name"
        const val PASSWORD = "password"
    }
}
