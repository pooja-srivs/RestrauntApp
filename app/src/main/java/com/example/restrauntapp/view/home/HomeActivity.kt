package com.example.restrauntapp.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.restrauntapp.R
import com.example.restrauntapp.data.RestaurantEntity
import com.example.restrauntapp.util.ResourceUtil
import com.example.restrauntapp.view.detail.DetailActivity
import com.example.restrauntapp.view.entity.RestReviews
import com.example.restrauntapp.view.entity.RestaurantItem
import com.example.restrauntapp.view.home.adapter.RestaurantAdapter
import com.google.gson.Gson
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var etSearch : EditText
    private lateinit var parent : ConstraintLayout
    private lateinit var adapter : RestaurantAdapter
    private lateinit var recyclerView : RecyclerView
    private val dataArr : MutableList<RestaurantItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parent = findViewById(R.id.parent)
        recyclerView = findViewById(R.id.rv_home)
        etSearch = findViewById(R.id.et_search)

        ResourceUtil.hideKeyboard(parent)

        val restaurauntData = ResourceUtil.getJsonString(
            "restauraunt.json",
            this
        )

        viewModel.getRestaurantList()

        val data  = Gson().fromJson(restaurauntData, RestaurantEntity::class.java)
        Log.d("*** Restaurant Data >>>>", ""+data.restaurants)

        data.restaurants.map { rest ->
            var reviewArr  = mutableListOf<RestReviews>()
            var openingTimesArr = mutableListOf<String>()

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

        adapter = RestaurantAdapter.newInstance(onItemClick)
        recyclerView.adapter = adapter
        adapter.submitData(dataArr)

        setListener()
    }

    private fun setListener(){
        etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
}