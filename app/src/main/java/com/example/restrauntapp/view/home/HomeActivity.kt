package com.example.restrauntapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.restrauntapp.R
import com.example.restrauntapp.data.RestaurantEntity
import com.example.restrauntapp.util.ResourceUtil
import com.google.gson.Gson
import dagger.android.AndroidInjection

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
     //   AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restrauntData = ResourceUtil.getJsonString(
            "restauraunt.json",
            this
        )

        val data  = Gson().fromJson(restrauntData, RestaurantEntity::class.java)
        Log.d("*** Restaurant Data >>>>", ""+data.restaurants)


    }
}