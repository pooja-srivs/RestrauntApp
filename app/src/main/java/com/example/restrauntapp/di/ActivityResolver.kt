package com.example.restrauntapp.di

import com.example.restrauntapp.view.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityResolver {

    @ContributesAndroidInjector
    abstract fun provideHomeActivity() : MainActivity
}