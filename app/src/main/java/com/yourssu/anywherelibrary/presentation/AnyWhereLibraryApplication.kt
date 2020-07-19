package com.yourssu.anywherelibrary.presentation

import android.app.Application
import android.content.Context
import com.yourssu.anywherelibrary.util.SharedPreferenceManager

class AnyWhereLibraryApplication : Application() {
    companion object {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        SharedPreferenceManager.init(appContext!!)
    }
}