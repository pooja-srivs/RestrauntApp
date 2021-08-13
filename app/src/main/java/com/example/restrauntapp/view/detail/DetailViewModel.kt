package com.example.restrauntapp.view.detail

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.restrauntapp.RestaurauntApp
import com.example.restrauntapp.data.MenuEntity
import com.example.restrauntapp.util.ResourceUtil
import com.example.restrauntapp.view.detail.adapter.RestCategory
import com.example.restrauntapp.view.detail.adapter.RestSubData
import com.example.restrauntapp.view.entity.RestaurantItem
import com.google.gson.Gson

class DetailViewModel(private val context: RestaurauntApp) : ViewModel() {
    private val reviewArr : MutableList<RestSubData> = mutableListOf()
    private val openingHoursArr : MutableList<RestSubData> = mutableListOf()
    private val dataArr : MutableList<RestCategory> = mutableListOf()
    private var count : Int = 0

    fun fetchData(): MenuEntity {
        val menuData = ResourceUtil.getJsonString(
                "menu.json",
                context
        )

        //current screen data
        val categoryData  = Gson().fromJson(menuData, MenuEntity::class.java)

        return categoryData
    }

    fun prepareData(restaurantItem: RestaurantItem, categoryData: MenuEntity): MutableList<RestCategory> {

        categoryData.menus.map { menu ->
            menu.categories.map { category ->
                var menuItems = mutableListOf<RestSubData>()

                category.menu_items.map { menuItem ->
                    menuItems.add(
                            RestSubData(
                                    id = menuItem.id.toInt(),
                                    name = menuItem.name,
                                    description = menuItem.description,
                                    date = menuItem.price,
                                    viewType = "menuItem"
                            )
                    )
                }
                dataArr.add(
                        RestCategory( id = category.id.toInt(), title = category.name, subList = menuItems, isExpanded = false)
                )
            }
        }

        //review
        restaurantItem.reviews.map { review ->
            reviewArr.add(
                    RestSubData(
                            id = review.id,
                            name = review.name,
                            description = review.comments,
                            date = review.date,
                            rating = review.rating.toFloat(),
                            viewType = "reviewItem"
                    ))
        }

        //opening hours
        restaurantItem.openingHours.map { openingHour ->
            count++
            openingHoursArr.add(
                    RestSubData(
                            id = count,
                            name = openingHour,
                            viewType = "openingHoursItem"
                    ))
        }
        count = 0
        dataArr.add(
                RestCategory( id = ++count, title = "Reviews", subList = reviewArr, isExpanded = false)
        )
        dataArr.add(
                RestCategory( id = ++count, title = "Opening Hours", subList = openingHoursArr, isExpanded = false)
        )

        return dataArr
    }
}