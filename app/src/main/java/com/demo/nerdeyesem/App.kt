package com.demo.nerdeyesem

import android.app.Application

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.init(applicationContext)
    }
}