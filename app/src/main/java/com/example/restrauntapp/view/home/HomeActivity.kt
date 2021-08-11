package com.example.restrauntapp.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restrauntapp.R
import com.example.restrauntapp.util.ResourceUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val restrauntData = ResourceUtil.getJsonString(
            "restauraunt.json",
            this
        )
    }
}