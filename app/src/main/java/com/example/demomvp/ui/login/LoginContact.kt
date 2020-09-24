package com.example.demomvp.ui.login

import com.example.demomvp.data.model.User
import com.example.demomvp.ui.base.BasePresenter
import com.example.demomvp.ui.base.BaseView

interface LoginContact {
    interface View : BaseView {
        fun loginSuccess(user: User)
        fun loginEd()
    }

    interface Presenter : BasePresenter {
        fun onClickLogin(userName: String, pass: String)
        fun onClickFakeRegister()
        fun checkLogin()
    }
}
