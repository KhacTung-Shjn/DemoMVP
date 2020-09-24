package com.example.demomvp.data.source.local.dao

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.demomvp.data.model.User
import com.example.demomvp.data.source.local.database.UserDatabase

class UserDAOImpl(context: Context) : UserDAO {

    private val databaseW = UserDatabase.getInstance(context).writableDatabase
    private val databaseR: SQLiteDatabase = UserDatabase.getInstance(context).readableDatabase
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
        val contentValues = ContentValues()
        contentValues.put(User.USER_NAME, user.userName)
        contentValues.put(User.PASSWORD, user.password)

        val result: Long = databaseW.insert(User.TABLE_NAME, null, contentValues)
        databaseW.close()
        return result >= 0
    }

    companion object {
        private var instance: UserDAOImpl? = null

        fun getInstance(context: Context): UserDAOImpl {
            instance = UserDAOImpl(context)
            return instance as UserDAOImpl
        }
    }
}
