package com.example.restrauntapp.view.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restrauntapp.view.home.HomeActivity
import com.example.restrauntapp.view.home.HomeViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
object HomeModule {

    @Provides
    fun provideHomeVMFactory() : HomeVMFactory = HomeVMFactory()

    @Provides
    fun provideHomeVM(context : HomeActivity, factory : HomeVMFactory) : HomeViewModel =
        ViewModelProvider(context, factory).get(HomeViewModel::class.java)
}


@Singleton
class HomeVMFactory @Inject constructor() : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel() as T
    }
}