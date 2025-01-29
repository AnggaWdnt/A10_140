package com.example.a10_140.ui

import android.app.Application
import com.example.a10_140.repository.A10Container
import com.example.a10_140.repository.AppContainer

class A10Application:Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = A10Container()
    }
}