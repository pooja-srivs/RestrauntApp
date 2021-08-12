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
    val openingHours: OpeningTimes,
    val reviews: List<RestReviews>
) : Serializable

data class OpeningTimes(
    val monday : String,
    val tuesday : String,
    val wednesday : String,
    val thursday : String,
    val friday : String,
    val saturday : String,
    val sunday : String,
) : Serializable

data class RestReviews(
    val name : String,
    val date : String,
    val rating : String,
    val comments : String,
) : Serializable