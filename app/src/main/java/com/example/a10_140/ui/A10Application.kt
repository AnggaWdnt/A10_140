package com.example.a10_140.ui

import android.app.Application
import com.example.a10_140.repository.AppContainer

class MahasiswaApplications:Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainer()
    }
}