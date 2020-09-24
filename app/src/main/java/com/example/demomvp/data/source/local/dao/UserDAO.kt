package com.example.demomvp.data.source.local.dao

import com.example.demomvp.data.model.User

interface UserDAO{
    fun getUsers(): MutableList<User>

    fun isValidateUser(user: User): Boolean

    fun addUser(user: User) : Boolean
}
