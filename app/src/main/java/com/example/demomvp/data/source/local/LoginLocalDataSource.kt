package com.example.demomvp.data.source.local

import com.example.demomvp.data.model.User
import com.example.demomvp.data.source.LoginDataSource
import com.example.demomvp.data.source.local.dao.UserDAO

class LoginLocalDataSource(private val userDAO: UserDAO) : LoginDataSource.Local {
    override fun getUsers(): MutableList<User> = userDAO.getUsers()

    override fun addUser(user: User): Boolean = userDAO.addUser(user)

    override fun isValidateUser(user: User): Boolean = userDAO.isValidateUser(user)

    companion object {
        private var instance: LoginLocalDataSource? = null

        /*fun getInstance(userDAO: UserDAO): LoginLocalDataSource {
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = LoginLocalDataSource(userDAO)
                    }
                }
            }
            return instance!!
        }*/

        fun getInstance(userDAO: UserDAO): LoginLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LoginLocalDataSource(userDAO).also { instance = it }
            }

    }
}
