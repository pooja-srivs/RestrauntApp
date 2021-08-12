package com.example.restrauntapp

import com.example.restrauntapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class RestaurauntApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
        .builder()
        .application(this)
        .build()
}