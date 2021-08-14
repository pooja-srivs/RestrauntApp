package com.example.restrauntapp.view.home.adapter

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restrauntapp.R
import com.example.restrauntapp.view.entity.RestaurantItem

class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private var restName : TextView = view.findViewById(R.id.restName)
    private var restCuisine : TextView = view.findViewById(R.id.tv_cuisine)
    private var restAddress : TextView = view.findViewById(R.id.tv_addr)
    private var restDistance : TextView = view.findViewById(R.id.tv_distance)
    private var restRatings : RatingBar = view.findViewById(R.id.ratings)
    private var restDiscount : TextView = view.findViewById(R.id.tv_discount)

    fun bind(item: RestaurantItem, onItemClick: (RestaurantItem) -> Unit){

        restName.text = item.restName
        restCuisine.text = item.cuisineType
        restAddress.text = item.loc
        restDistance.text = item.distance
        restDiscount.text = item.discount
        restRatings.rating = item.rating

        itemView.setOnClickListener {
            onItemClick.invoke(item)
        }
    }
}
