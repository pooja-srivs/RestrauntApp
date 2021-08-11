package com.example.restrauntapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.restrauntapp.MainActivity
import com.example.restrauntapp.R

class SplashActivity : AppCompatActivity() {

    private lateinit var btn_explore : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        btn_explore = findViewById(R.id.btn_explore)

        setListener()
    }

    private fun setListener(){

        btn_explore.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}