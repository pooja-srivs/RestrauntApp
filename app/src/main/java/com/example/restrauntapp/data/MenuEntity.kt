package com.example.restrauntapp.data

data class MenuEntity (
    val menus : List<Menus>
    )

data class Menus(
    val restaurantId : Int,
    val categories : List<Category>
)

data class Category(
    val id : String,
    val name : String,
    val menu_items : List<MenuItems>
)

data class MenuItems(
    val id : String,
    val name : String,
    val description : String,
    val price : String
)