package com.example.prak8

import android.app.Application
import com.example.prak8.repository.AppContainer
import com.example.prak8.repository.MahasiswaContainer

class MahasiswaApplications: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container= MahasiswaContainer()
    }
}