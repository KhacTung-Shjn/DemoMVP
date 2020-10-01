package com.example.demomvp.data.repository

import com.example.demomvp.data.model.User
import com.example.demomvp.data.source.LoginDataSource

class LoginRepository(
    private val local: LoginDataSource.Local,
    private val remote: LoginDataSource.Remote
) : LoginDataSource.Local, LoginDataSource.Remote {

    override fun getUsers(): List<User> = local.getUsers()

    override fun addUser(user: User): Boolean = local.addUser(user)

    override fun isValidUser(userName: String, password: String): Boolean =
        local.isValidUser(userName, password)

    companion object {
        private var instance: LoginRepository? = null

        fun getInstance(
            local: LoginDataSource.Local,
            remote: LoginDataSource.Remote
        ): LoginRepository =
            instance ?: synchronized(this) {
                instance ?: LoginRepository(local, remote).also { instance = it }
            }
    }
}
