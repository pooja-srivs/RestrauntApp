package com.example.restrauntapp.view.detail.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.restrauntapp.RestaurauntApp
import com.example.restrauntapp.view.detail.DetailActivity
import com.example.restrauntapp.view.detail.DetailViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
object DetailModule {

    @Provides
    fun provideDetailVMFactory(context: RestaurauntApp) : DetailVMFactory = DetailVMFactory(context)

    @Provides
    fun provideDetailVM(activity: DetailActivity, factory : DetailVMFactory) : DetailViewModel =
        ViewModelProvider(activity, factory).get(DetailViewModel::class.java)
}

@Singleton
class DetailVMFactory @Inject constructor(private val context : RestaurauntApp): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(context) as T
    }
}