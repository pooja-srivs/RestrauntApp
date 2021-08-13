package com.example.restrauntapp.network.repo

import com.example.restrauntapp.data.MenuEntity
import com.example.restrauntapp.data.RestaurantEntity
import io.reactivex.Single

interface RestaurantRepo {

    fun fetchRestaurantList() : Single<RestaurantEntity>

    fun fetchItemDetail() : Single<MenuEntity>
}