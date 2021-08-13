package com.example.restrauntapp.data.remote.source

import com.example.restrauntapp.data.MenuEntity
import com.example.restrauntapp.data.RestaurantEntity
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET("/getRestaurantListData")
    fun getRestaurantList() : Single<RestaurantEntity>

    @GET("/getRestaurantDetail")
    fun getRestaurantMenu() : Single<MenuEntity>
}