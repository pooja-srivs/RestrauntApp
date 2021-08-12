package com.example.restrauntapp.data

data class RestaurantEntity (
    val restaurants : List<Restaurants>
    )

data class Restaurants(
    val id : Int,
    val name : String,
    val neighborhood : String,
    val photograph : String,
    val address : String,
    val cuisine_type : String,
    val rating : Int,
    val discount : Int,
    val reviews : List<Reviews>
)

data class Reviews(
    val name : String,
    val date : String,
    val rating : String,
    val comments : String,
)