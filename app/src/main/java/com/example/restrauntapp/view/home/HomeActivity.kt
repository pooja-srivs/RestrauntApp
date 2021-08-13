package com.example.restrauntapp.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.restrauntapp.R
import com.example.restrauntapp.data.RestaurantEntity
import com.example.restrauntapp.util.ResourceUtil
import com.example.restrauntapp.view.detail.DetailActivity
import com.example.restrauntapp.view.entity.OpeningTimes
import com.example.restrauntapp.view.entity.RestReviews
import com.example.restrauntapp.view.entity.RestaurantItem
import com.google.gson.Gson
import dagger.android.AndroidInjection

class HomeActivity : AppCompatActivity() {

    private lateinit var adapter : RestaurantAdapter
    private lateinit var recyclerView : RecyclerView
    private val dataArr : MutableList<RestaurantItem> = mutableListOf()
    private val reviewArr : MutableList<RestReviews> = mutableListOf()
    private val openingTimesArr : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
     //   AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_home)

        val restaurauntData = ResourceUtil.getJsonString(
            "restauraunt.json",
            this
        )

        val data  = Gson().fromJson(restaurauntData, RestaurantEntity::class.java)
        Log.d("*** Restaurant Data >>>>", ""+data.restaurants)

        data.restaurants.map { rest ->

            rest.reviews.map { review ->
                reviewArr.add(
                    RestReviews(
                        id = review.id,
                        name = review.name,
                        date = review.date,
                        rating = review.rating,
                        comments = review.comments
                    )
                )
            }

            rest.operating_hours.let { openingHour ->
                openingTimesArr.add("Monday : ${openingHour.monday}")
                openingTimesArr.add("Tuesday : ${openingHour.tuesday}")
                openingTimesArr.add("Wednesday : ${openingHour.wednesday}")
                openingTimesArr.add("Thursday : ${openingHour.thursday}")
                openingTimesArr.add("Friday : ${openingHour.friday}")
                openingTimesArr.add("Saturday : ${openingHour.saturday}")
                openingTimesArr.add("Sunday : ${openingHour.sunday}")
            }

            dataArr.add(RestaurantItem(
                id = rest.id,
                restName = rest.name,
                cuisineType = rest.cuisine_type,
                loc = rest.address,
                rating = rest.rating.toFloat(),
                discount = "${rest.discount} % Off on Order abv Rs.300",
                distance = "${rest.distance} miles",
                openingHours = openingTimesArr,
                reviews = reviewArr
            ))
        }

        val onItemClick : (RestaurantItem) -> Unit = { restItem ->

            Log.d("*** onItemClick >>>>>", ""+restItem)
            startActivity(Intent(this, DetailActivity::class.java).putExtra("restItem", restItem))
        }

        adapter = RestaurantAdapter.newInstance(onItemClick).apply {
            recyclerView.adapter = this
            submitList(dataArr)
        }


    }
}