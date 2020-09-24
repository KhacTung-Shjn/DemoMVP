package com.example.demomvp.ui.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.demomvp.R
import com.example.demomvp.data.model.User
import com.example.demomvp.data.repository.LoginRepository
import com.example.demomvp.data.source.LoginDataSource
import com.example.demomvp.data.source.local.LoginLocalDataSource
import com.example.demomvp.data.source.local.dao.UserDAOImpl
import com.example.demomvp.data.source.remote.LoginRemoteDataSource
import com.example.demomvp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContact.View, View.OnClickListener {

    private lateinit var presenter: LoginContact.Presenter
    private lateinit var localDataSource: LoginDataSource.Local
    private lateinit var remoteDataSource: LoginDataSource.Remote
    private lateinit var repository: LoginRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        checkLoginEd()
    }

    private fun checkLoginEd() {
        presenter.checkLogin()
    }

    override fun initPresenter() {
        localDataSource = LoginLocalDataSource.getInstance(UserDAOImpl.getInstance(baseContext))
        remoteDataSource = LoginRemoteDataSource()
        repository = LoginRepository.getInstance(localDataSource, remoteDataSource)
        presenter = LoginPresenter(this, repository)
    }

    private fun initView() {
        button_login.setOnClickListener(this)
        text_fake_register.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.button_login -> {
                presenter.onClickLogin(edit_username.text.toString(), edit_password.text.toString())
            }
            R.id.text_fake_register -> {
                presenter.onClickFakeRegister()
            }
        }

    }

    override fun loginSuccess(user: User) {
        Toast.makeText(baseContext, "LoginSuccess", Toast.LENGTH_SHORT).show()
    }

    override fun loginEd() {
        Toast.makeText(baseContext, "Logined", Toast.LENGTH_SHORT).show()
        button_login.setBackgroundResource(R.color.colorAccent)
        text_fake_register.visibility = View.INVISIBLE
    }
}
