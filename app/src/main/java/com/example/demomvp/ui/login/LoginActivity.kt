package com.example.demomvp.ui.login

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.example.demomvp.MainApp
import com.example.demomvp.R
import com.example.demomvp.data.model.User
import com.example.demomvp.data.repository.LoginRepository
import com.example.demomvp.data.source.LoginDataSource
import com.example.demomvp.data.source.local.LoginLocalDataSource
import com.example.demomvp.data.source.local.dao.UserDAOImpl
import com.example.demomvp.data.source.local.database.UserDatabase
import com.example.demomvp.data.source.local.preferences.PreferencesHelperImpl
import com.example.demomvp.data.source.remote.LoginRemoteDataSource
import com.example.demomvp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContact.View, View.OnClickListener {

    private var presenter: LoginContact.Presenter? = null
    private val prefsHelper: PreferencesHelperImpl by lazy {
        PreferencesHelperImpl.getInstance(getSharedPreferences(MainApp.TAG, MODE_PRIVATE))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        checkLoginEd()
    }

    override fun initPresenter() {
        val userDatabase = UserDatabase.getInstance(baseContext)
        val userDAOImpl = UserDAOImpl.getInstance(userDatabase)
        val localDataSource = LoginLocalDataSource.getInstance(userDAOImpl)
        val remoteDataSource = LoginRemoteDataSource()
        val repository = LoginRepository.getInstance(localDataSource, remoteDataSource)
        presenter = LoginPresenter(this, repository)
    }

    private fun initView() {
        buttonLogin.setOnClickListener(this)
        textFakeRegister.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonLogin -> {
                when {
                    editUsername.text.toString().isEmpty() -> {
                        showMessage(R.string.msg_empty_username)
                    }
                    editPassword.text.toString().isEmpty() -> {
                        showMessage(R.string.msg_empty_pass)
                    }
                    else -> {
                        presenter?.onClickLogin(
                            editUsername.text.toString(),
                            editPassword.text.toString()
                        )
                    }
                }
            }
            R.id.textFakeRegister -> {
                presenter?.addUser()
            }
        }

    }

    override fun loginSuccess(user: User) {
        prefsHelper.setIsLogin(true)
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
