package com.example.demomvp.ui.login

import com.example.demomvp.R
import com.example.demomvp.data.model.User
import com.example.demomvp.data.repository.LoginRepository

class LoginPresenter(
    private val view: LoginContact.View,
    private val repository: LoginRepository
) : LoginContact.Presenter {

    override fun onClickLogin(userName: String, pass: String) {
        if (isValid(userName, pass)) {
            view.showMessage(R.string.msg_login_success)
            view.loginSuccess(User(userName, pass))
        }
    }

    override fun addUser() {
        repository.addUser(User("a", "b"))
    }

    private fun isValid(userName: String, pass: String): Boolean {
        if (!repository.isValidUser(userName, pass)) {
            view.showMessage(R.string.msg_login_fail)
            return false
        }
        return true
    }
}
