package com.example.restrauntapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restrauntapp.R
import com.example.restrauntapp.data.OpeningHours
import com.example.restrauntapp.view.detail.adapter.MainAdapter
import com.example.restrauntapp.view.detail.adapter.RestCategory
import com.example.restrauntapp.view.detail.adapter.RestSubData
import com.example.restrauntapp.view.entity.RestaurantItem

class DetailActivity : AppCompatActivity() {
    private lateinit var restName : TextView
    private lateinit var tv_cuisine : TextView
    private lateinit var tv_addr : TextView
    private lateinit var tv_distance : TextView
    private lateinit var ratings : RatingBar
    private lateinit var tv_discount : TextView

    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private val dataArr : MutableList<RestCategory> = mutableListOf()
    private val reviewArr : MutableList<RestSubData> = mutableListOf()
    private val openingHoursArr : MutableList<RestSubData> = mutableListOf()
    private var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        restName = findViewById(R.id.restName)
        tv_cuisine = findViewById(R.id.tv_cuisine)
        tv_addr = findViewById(R.id.tv_addr)
        tv_distance = findViewById(R.id.tv_distance)
        ratings = findViewById(R.id.ratings)
        tv_discount = findViewById(R.id.tv_discount)
        recyclerView = findViewById(R.id.rv_detail)

        val restData = intent.getSerializableExtra("restItem") as RestaurantItem

        restData.let { restItem ->
            restName.text = restItem.restName
            tv_cuisine.text = restItem.cuisineType
            tv_addr.text = restItem.loc
            tv_distance.text = restItem.distance
            tv_discount.text = restItem.discount
            ratings.rating = restItem.rating
        }

        restData.reviews.map { review ->
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

        restData.openingHours.map { openingHour ->
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

        val onItemClick : (Int, Int, Boolean) -> Unit = {position, id, isExpand ->
            val expand = adapter.getValueAtPosition(position).isExpanded
            adapter.getValueAtPosition(position).isExpanded = !expand
            adapter.notifyItemChanged(position)
        }

        adapter = MainAdapter.newInstance(onItemClick)
        recyclerView.adapter = adapter
        adapter.submitList(dataArr)
    }
}