package com.example.demomvp.data.source.local.dao

import android.annotation.SuppressLint
import com.example.demomvp.data.model.User
import com.example.demomvp.data.source.local.database.UserDatabase

class UserDAOImpl(userDatabase: UserDatabase) : UserDAO {

    private val writableDatabase = userDatabase.writableDatabase
    private val readableDatabase = userDatabase.readableDatabase

    @SuppressLint("Recycle")
    override fun getUsers(): List<User> {
        val listUser = mutableListOf<User>()
        val mCursor = readableDatabase.query(User.TABLE_NAME, null, null, null, null, null, null)
        mCursor.moveToFirst()
        while (!mCursor.isAfterLast) {
            val userName = mCursor.getString(mCursor.getColumnIndex(User.USER_NAME))
            val password = mCursor.getString(mCursor.getColumnIndex(User.PASSWORD))
            listUser.add(User(userName, password))
            mCursor.moveToNext()
        }
        mCursor.close()
        return listUser
    }

    override fun isValidUser(userName: String, password:String): Boolean {
        val selection = User.USER_NAME + " = ? AND " + User.PASSWORD + " = ?"
        val selectionArg = arrayOf(userName, password)
        val mCursor = readableDatabase.query(User.TABLE_NAME, null, selection, selectionArg, null, null, null)
        val count = mCursor.count
        mCursor.close()
        return count >0
    }

    override fun addUser(user: User): Boolean {
        val result = writableDatabase.insert(User.TABLE_NAME, null, user.getContentValues())
        return result > 0
    }

    companion object {
        private var instance: UserDAOImpl? = null

        fun getInstance(userDatabase: UserDatabase): UserDAOImpl =
            instance ?: synchronized(this) {
                instance ?: UserDAOImpl(userDatabase).also { instance = it }
            }
    }
}
