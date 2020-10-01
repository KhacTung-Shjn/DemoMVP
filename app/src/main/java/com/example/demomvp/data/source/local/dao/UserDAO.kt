package com.example.demomvp.data.source.local.dao

import com.example.demomvp.data.model.User

interface UserDAO{
    fun getUsers(): List<User>

    fun isValidUser(userName: String, password:String): Boolean

    fun addUser(user: User) : Boolean
}
