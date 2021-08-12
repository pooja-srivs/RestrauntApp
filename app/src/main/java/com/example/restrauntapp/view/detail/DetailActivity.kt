package com.example.restrauntapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import com.example.restrauntapp.R
import com.example.restrauntapp.view.entity.RestaurantItem

class DetailActivity : AppCompatActivity() {
    private lateinit var restName : TextView
    private lateinit var tv_cuisine : TextView
    private lateinit var tv_addr : TextView
    private lateinit var tv_distance : TextView
    private lateinit var ratings : RatingBar
    private lateinit var tv_discount : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        restName = findViewById(R.id.restName)
        tv_cuisine = findViewById(R.id.tv_cuisine)
        tv_addr = findViewById(R.id.tv_addr)
        tv_distance = findViewById(R.id.tv_distance)
        ratings = findViewById(R.id.ratings)
        tv_discount = findViewById(R.id.tv_discount)

        val restData = intent.getSerializableExtra("restItem") as RestaurantItem

        restData.let { restItem ->
            restName.text = restItem.restName
            tv_cuisine.text = restItem.cuisineType
            tv_addr.text = restItem.loc
            tv_distance.text = restItem.distance
            tv_discount.text = restItem.discount
            ratings.rating = restItem.rating

        }
    }
}