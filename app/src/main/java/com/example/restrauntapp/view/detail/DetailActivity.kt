package com.example.restrauntapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restrauntapp.R
import com.example.restrauntapp.view.entity.RestaurantItem

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val restData = intent.getSerializableExtra("restItem") as RestaurantItem
    }
}