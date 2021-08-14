package com.example.restrauntapp.di

import com.example.restrauntapp.network.repo.RestaurantRepo
import com.example.restrauntapp.network.repo.RestaurantRepoImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataResolver {

    @Singleton
    @Binds
    abstract fun provideRepository(repoImpl: RestaurantRepoImpl) : RestaurantRepo
}