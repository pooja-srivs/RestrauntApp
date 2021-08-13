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
    val distance : Int,
    val operating_hours : OpeningHours,
    val reviews : List<Reviews>
)

data class OpeningHours(
    val monday : String,
    val tuesday : String,
    val wednesday : String,
    val thursday : String,
    val friday : String,
    val saturday : String,
    val sunday : String,

)

data class Reviews(
    val id : Int,
    val name : String,
    val date : String,
    val rating : String,
    val comments : String,
)