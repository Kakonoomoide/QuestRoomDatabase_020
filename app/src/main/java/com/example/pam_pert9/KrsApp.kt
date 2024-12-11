package com.example.pam_pert9

import android.app.Application
import com.example.pam_pert9.dependeciesinjection.ContainerApp

//context
//instance -> object yang di buat dari class
class KrsApp : Application() {
    lateinit var containerApp: ContainerApp // menyimpan instance

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp(this) //membuat instance containerapp
    }
}