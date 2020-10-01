package com.example.demomvp.ui.base

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.demomvp.MainApp
import com.example.demomvp.data.source.local.preferences.PreferencesHelperImpl
import com.example.demomvp.utils.showToast

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
    }

    protected abstract fun initPresenter()

    override fun showMessage(idString: Int) {
        showMessage(getString(idString))
    }

    override fun showMessage(str: String?) {
        if (!str.isNullOrEmpty()) {
            baseContext.showToast(str)
        }
    }
}
