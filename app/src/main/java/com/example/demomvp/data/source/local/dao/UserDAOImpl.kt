package com.example.demomvp.data.source.local.dao

import android.annotation.SuppressLint
import com.example.demomvp.data.model.User
import com.example.demomvp.data.source.local.database.UserDatabase

class UserDAOImpl(userDatabase: UserDatabase) : UserDAO {

    private val databaseW = userDatabase.writableDatabase
    private val databaseR = userDatabase.readableDatabase

    @SuppressLint("Recycle")
    override fun getUsers(): List<User> {
        val listUser = mutableListOf<User>()
        val mCursor = databaseR.query(User.TABLE_NAME, null, null, null, null, null, null)
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

    override fun isValidateUser(user: User): Boolean {
        val selection = User.USER_NAME + " = ? AND " + User.PASSWORD + " = ?";
        val selectionArg = arrayOf(user.userName, user.password)
        val mCursor = databaseR.query(User.TABLE_NAME, null, selection, selectionArg, null, null, null)
        if (mCursor.count > 0) {
            mCursor.close()
            return true
        }
        return false
    }

    override fun addUser(user: User): Boolean {
        val result = databaseW.insert(User.TABLE_NAME, null, user.getContentValues())
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
