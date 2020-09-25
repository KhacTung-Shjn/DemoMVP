package com.example.demomvp.data.source.local.dao

import android.annotation.SuppressLint
import com.example.demomvp.data.model.User
import com.example.demomvp.data.source.local.database.UserDatabase

class UserDAOImpl(userDatabase: UserDatabase) : UserDAO {

    private val databaseW = userDatabase.writableDatabase
    private val databaseR = userDatabase.readableDatabase
    private val listUser: MutableList<User> = mutableListOf()

    @SuppressLint("Recycle")
    override fun getUsers(): MutableList<User> {

        val cusor = databaseR.query(User.TABLE_NAME, null, null, null, null, null, null)
        cusor.moveToFirst()
        while (!cusor.isAfterLast) {
            val userName = cusor.getString(cusor.getColumnIndex(User.USER_NAME))
            val password = cusor.getString(cusor.getColumnIndex(User.PASSWORD))
            listUser.add(User(userName, password))
            cusor.moveToNext()
        }
        return listUser
    }

    override fun isValidateUser(user: User): Boolean = getUsers().contains(user)

    override fun addUser(user: User): Boolean {
        val result = databaseW.insert(User.TABLE_NAME, null, user.getContentValues())
        databaseW.close()
        return result >= 0
    }

    companion object {
        private var instance: UserDAOImpl? = null

        fun getInstance(userDatabase: UserDatabase): UserDAOImpl =
            instance ?: synchronized(this) {
                instance ?: UserDAOImpl(userDatabase).also { instance = it }
            }
    }
}
