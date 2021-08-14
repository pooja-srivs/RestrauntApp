package com.example.restrauntapp.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.restrauntapp.R
import com.example.restrauntapp.view.entity.RestaurantItem

class RestaurantAdapter private constructor(
        private val diffUtil: DiffUtil.ItemCallback<RestaurantItem>,
        private val onItemClick: (RestaurantItem) -> Unit,
) : ListAdapter<RestaurantItem, RestaurantViewHolder>(diffUtil), Filterable {

    private var restData : MutableList<RestaurantItem> = mutableListOf()
    private var restFilterData : MutableList<RestaurantItem> = mutableListOf()

    companion object{

        private val diffCallback = object : DiffUtil.ItemCallback<RestaurantItem>() {
            override fun areItemsTheSame(
                    oldItem: RestaurantItem,
                    newItem: RestaurantItem,
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                    oldItem: RestaurantItem,
                    newItem: RestaurantItem,
            ): Boolean {
               return oldItem == newItem
            }
        }

        fun newInstance(onItemClick: (RestaurantItem) -> Unit) : RestaurantAdapter {
            return RestaurantAdapter(diffCallback, onItemClick = onItemClick)
        }
    }

    fun submitData(data: MutableList<RestaurantItem>){
        restData.addAll(data)
        submitList(restData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.rest_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }

    //perform filter
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    restFilterData = restData
                } else {
                    val filteredList: MutableList<RestaurantItem> = mutableListOf()

                    restData.forEach { rest ->
                       if (rest.restName.toLowerCase().contains(charString.toLowerCase()) || rest.cuisineType.contains(charSequence)) {
                            filteredList.add(rest)
                        }
                    }
                    restFilterData = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = restFilterData
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                restFilterData = filterResults.values as MutableList<RestaurantItem>
                submitList(restFilterData)
            }
        }
    }
}