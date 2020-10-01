package com.example.demomvp.data.source.local

import com.example.demomvp.data.model.User
import com.example.demomvp.data.source.LoginDataSource
import com.example.demomvp.data.source.local.dao.UserDAO

class LoginLocalDataSource(private val userDAO: UserDAO) : LoginDataSource.Local {
    override fun getUsers(): List<User> = userDAO.getUsers()

    override fun addUser(user: User): Boolean = userDAO.addUser(user)

    override fun isValidUser(userName: String, password: String): Boolean =
        userDAO.isValidUser(userName, password)

    companion object {
        private var instance: LoginLocalDataSource? = null

        fun getInstance(userDAO: UserDAO): LoginLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LoginLocalDataSource(userDAO).also { instance = it }
            }
    }
}
