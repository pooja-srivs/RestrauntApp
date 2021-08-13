package com.example.restrauntapp.network.repo

import com.example.restrauntapp.data.MenuEntity
import com.example.restrauntapp.data.RestaurantEntity
import com.example.restrauntapp.data.remote.source.ApiService
import io.reactivex.Single
import javax.inject.Inject

class RestaurantRepoImpl @Inject constructor(private val apiService: ApiService) : RestaurantRepo {

    override fun fetchRestaurantList(): Single<RestaurantEntity>  = apiService.getRestaurantList()

    override fun fetchItemDetail(): Single<MenuEntity> = apiService.getRestaurantMenu()
}