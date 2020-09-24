package com.example.demomvp.ui.login

import android.text.TextUtils
import com.example.demomvp.MainApp
import com.example.demomvp.R
import com.example.demomvp.data.model.User
import com.example.demomvp.data.repository.LoginRepository

class LoginPresenter(
    private val view: LoginContact.View,
    private val repository: LoginRepository
) : LoginContact.Presenter {
    override fun onClickLogin(userName: String, pass: String) {
        if (isValidate(userName, pass)) {
            MainApp.getPrefs().setIsLogin(true)
            view.loginSuccess(User(userName, pass))
        }
    }

    override fun onClickFakeRegister() {
        repository.addUser(User("a", "b"))
    }

    override fun checkLogin() {
        if (MainApp.getPrefs().isLogin()) {
            view.loginEd()
        }
    }

    private fun isValidate(userName: String, pass: String): Boolean {
        if (TextUtils.isEmpty(userName)) {
            view.showMessage(R.string.msg_empty_username)
            return false
        }
        if (TextUtils.isEmpty(pass)) {
            view.showMessage(R.string.msg_empty_pass)
            return false
        }

        if (!repository.isValidateUser(User(userName, pass))) {
            view.showMessage(R.string.msg_login_fail)
            return false
        }

        return true
    }
}
