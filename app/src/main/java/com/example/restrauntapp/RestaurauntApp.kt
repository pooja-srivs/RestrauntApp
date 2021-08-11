package com.example.restrauntapp

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class RestaurauntApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

    }
}