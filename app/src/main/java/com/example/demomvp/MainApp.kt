package com.example.demomvp

import android.app.Application
import com.example.demomvp.data.source.local.preferences.PreferencesHelperImpl
import com.facebook.stetho.Stetho

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        prefsHelper = PreferencesHelperImpl.getInstance(getSharedPreferences(TAG, MODE_PRIVATE))
    }

    companion object {
        val TAG: String = MainApp::class.java.simpleName
        private var prefsHelper: PreferencesHelperImpl? = null
        fun getPrefs(): PreferencesHelperImpl = prefsHelper!!
    }
}
