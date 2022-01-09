package com.example.dars_35

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application() {

    companion object{
        lateinit var app:MyApp
    }

    override fun onCreate() {
        super.onCreate()

    }
}