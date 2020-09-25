package com.example.demomvp.data.source.local.preferences

import android.content.SharedPreferences
import com.example.demomvp.utils.PrefsConst.PREF_IS_LOGIN

class PreferencesHelperImpl(sharedPreferences: SharedPreferences) :
    PreferencesHelper {
    private var sharedPreferences: SharedPreferences? = null

    init {
        this.sharedPreferences = sharedPreferences
    }

    override fun setIsLogin(isLogin: Boolean) {
        sharedPreferences?.edit()?.putBoolean(PREF_IS_LOGIN, isLogin)?.apply()
    }

    override fun isLogin(): Boolean {
        return sharedPreferences!!.getBoolean(PREF_IS_LOGIN, false)
    }

    companion object {
        private var instance: PreferencesHelperImpl? = null
        fun getInstance(sharedPreferences: SharedPreferences): PreferencesHelperImpl =
            instance ?: synchronized(this) {
                instance ?: PreferencesHelperImpl(sharedPreferences).also { instance = it }
            }
    }
}
