package com.example.demomvp.ui.login

import android.os.Bundle
import android.view.View
import com.example.demomvp.MainApp
import com.example.demomvp.R
import com.example.demomvp.data.model.User
import com.example.demomvp.data.repository.LoginRepository
import com.example.demomvp.data.source.LoginDataSource
import com.example.demomvp.data.source.local.LoginLocalDataSource
import com.example.demomvp.data.source.local.dao.UserDAOImpl
import com.example.demomvp.data.source.local.database.UserDatabase
import com.example.demomvp.data.source.remote.LoginRemoteDataSource
import com.example.demomvp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContact.View, View.OnClickListener {

    private lateinit var presenter: LoginContact.Presenter
    private lateinit var localDataSource: LoginDataSource.Local
    private lateinit var remoteDataSource: LoginDataSource.Remote
    private lateinit var repository: LoginRepository
    private lateinit var userDatabase: UserDatabase
    private lateinit var userDAOImpl: UserDAOImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        checkLoginEd()
    }


    override fun initPresenter() {
        userDatabase = UserDatabase.getInstance(baseContext)
        userDAOImpl = UserDAOImpl.getInstance(userDatabase)
        localDataSource = LoginLocalDataSource.getInstance(userDAOImpl)
        remoteDataSource = LoginRemoteDataSource()
        repository = LoginRepository.getInstance(localDataSource, remoteDataSource)
        presenter = LoginPresenter(this, repository)
    }

    private fun initView() {
        buttonLogin.setOnClickListener(this)
        textFakeRegister.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonLogin -> {
                presenter.onClickLogin(editUsername.text.toString(), editPassword.text.toString())
            }
            R.id.textFakeRegister -> {
                presenter.onClickFakeRegister()
            }
        }

    }

    override fun loginSuccess(user: User) {
        showMessage(R.string.msg_login_success)
    }

    private fun checkLoginEd() {
        if (prefsHelper.isLogin()) {
            showMessage(R.string.msg_logged)
            buttonLogin.setBackgroundResource(R.color.colorAccent)
            textFakeRegister.visibility = View.INVISIBLE
        }
    }

}
