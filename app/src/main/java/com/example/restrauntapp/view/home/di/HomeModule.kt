package com.example.restrauntapp.view.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.restrauntapp.network.repo.RestaurantRepo
import com.example.restrauntapp.view.home.HomeActivity
import com.example.restrauntapp.view.home.HomeViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
object HomeModule {

    @Provides
    fun provideHomeVMFactory(repo: RestaurantRepo) : HomeVMFactory = HomeVMFactory(repo)

    @Provides
    fun provideHomeVM(context : HomeActivity, factory : HomeVMFactory) : HomeViewModel =
        ViewModelProvider(context, factory).get(HomeViewModel::class.java)
}


@Singleton
class HomeVMFactory @Inject constructor(private val repo: RestaurantRepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repo) as T
    }
}