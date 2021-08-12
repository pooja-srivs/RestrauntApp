package com.example.restrauntapp.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restrauntapp.R

class RestaurantAdapter private constructor(
    private val diffUtil : DiffUtil.ItemCallback<RestaurantItem>,
    private val onItemClick : (Int) -> Unit
) : ListAdapter<RestaurantItem, RestaurantAdapter.RestaurantViewHolder>(diffUtil) {

    companion object{

        private val diffCallback = object : DiffUtil.ItemCallback<RestaurantItem>() {
            override fun areItemsTheSame(
                oldItem: RestaurantItem,
                newItem: RestaurantItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RestaurantItem,
                newItem: RestaurantItem
            ): Boolean {
               return oldItem == newItem
            }
        }

        fun newInstance(onItemClick : (Int) -> Unit) : RestaurantAdapter{
            return RestaurantAdapter(diffCallback, onItemClick = onItemClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.landing_offers, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RestaurantViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private var restName : TextView = view.findViewById(R.id.restName)
        private var restCuisine : TextView = view.findViewById(R.id.tv_cuisine)
        private var restAddress : TextView = view.findViewById(R.id.tv_addr)
        private var restNeighbour : TextView = view.findViewById(R.id.tv_neighbour)
        private var restDistance : TextView = view.findViewById(R.id.tv_distance)
        private var restRatings : RatingBar = view.findViewById(R.id.ratings)
        private var restDiscount : TextView = view.findViewById(R.id.tv_discount)

        fun bind(item : RestaurantItem){

            restName.text = item.restName
            restCuisine.text = item.cuisineType
            restAddress.text = item.loc
            restNeighbour.text = item.neighbour
            restDistance.text = item.distance
            restDiscount.text = item.discount
            restRatings.rating = item.rating
        }
    }
}

data class RestaurantItem(
    val id: Int,
    val restName: String,
    val cuisineType: String,
    val loc: String,
    val neighbour: String,
    val rating: Float,
    val discount: String,
    val distance: String
)