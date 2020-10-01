package com.example.demomvp.data.source

import com.example.demomvp.data.model.User

interface LoginDataSource {
    interface Local {
        fun getUsers(): List<User>
        fun addUser(user: User): Boolean
        fun isValidateUser(user: User): Boolean

    }

    interface Remote {

    }
}
