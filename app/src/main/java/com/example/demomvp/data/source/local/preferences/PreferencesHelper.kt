package com.example.demomvp.data.source.local.preferences

interface PreferencesHelper {
    fun setIsLogin(isLogin: Boolean)

    fun isLogin(): Boolean
}
