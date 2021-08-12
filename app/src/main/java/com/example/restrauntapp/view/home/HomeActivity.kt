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
                        review.name,
                        review.date,
                        review.rating,
                        review.comments
                    )
                )
            }

            dataArr.add(RestaurantItem(
                id = rest.id,
                restName = rest.name,
                cuisineType = rest.cuisine_type,
                loc = rest.address,
                rating = rest.rating.toFloat(),
                discount = "${rest.discount} % Off on Order abv Rs.300",
                distance = "${rest.distance} miles",
                openingHours = OpeningTimes(
                    monday = rest.operating_hours.monday,
                    tuesday = rest.operating_hours.tuesday,
                    wednesday = rest.operating_hours.wednesday,
                    thursday = rest.operating_hours.thursday,
                    friday = rest.operating_hours.friday,
                    saturday = rest.operating_hours.saturday,
                    sunday = rest.operating_hours.sunday
                ),
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