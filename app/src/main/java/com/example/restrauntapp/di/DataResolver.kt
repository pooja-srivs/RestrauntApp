package com.example.restrauntapp.di

import com.example.restrauntapp.network.repo.RestaurantRepo
import com.example.restrauntapp.network.repo.RestaurantRepoImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataResolver {

    @Binds
    abstract fun provideRepository(repoImpl: RestaurantRepoImpl) : RestaurantRepo
}