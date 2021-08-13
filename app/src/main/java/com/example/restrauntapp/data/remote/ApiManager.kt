package com.example.restrauntapp.data.remote

import com.example.restrauntapp.data.remote.source.ApiService
import retrofit2.Retrofit

class ApiManager(private val retrofit: Retrofit) {

    val apiService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}