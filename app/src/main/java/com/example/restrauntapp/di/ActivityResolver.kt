package com.example.restrauntapp.di

import com.example.restrauntapp.view.detail.DetailActivity
import com.example.restrauntapp.view.detail.di.DetailModule
import com.example.restrauntapp.view.home.HomeActivity
import com.example.restrauntapp.view.home.di.HomeModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityResolver {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun provideHomeActivity() : HomeActivity

    @ContributesAndroidInjector(modules = [DetailModule::class])
    abstract fun provideDetailActivity() : DetailActivity
}