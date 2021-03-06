package com.example.restrauntapp.view.entity

import java.io.Serializable

data class RestaurantItem(
    val id: Int,
    val restName: String,
    val cuisineType: String,
    val loc: String,
    val rating: Float,
    val discount: String,
    val distance: String,
    val openingHours: List<String>,
    val reviews: List<RestReviews>
) : Serializable

data class RestReviews(
    val id : Int,
    val name : String,
    val date : String,
    val rating : String,
    val comments : String,
) : Serializable