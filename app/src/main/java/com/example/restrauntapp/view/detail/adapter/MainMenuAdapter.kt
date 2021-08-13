package com.example.restrauntapp.view.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.restrauntapp.R

class MainSubAdapter private constructor(private val diffUtil: DiffUtil.ItemCallback<RestSubData>)
    : ListAdapter<RestSubData, BaseViewHolder<RestSubData>>(diffUtil) {

    companion object{

        private const val menuItem = "menuItem"
        private const val reviewItem = "reviewItem"
        private const val openingHoursItem = "openingHoursItem"

        private val diffUtil = object : DiffUtil.ItemCallback<RestSubData>(){
            override fun areItemsTheSame(oldItem: RestSubData, newItem: RestSubData): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: RestSubData,
                newItem: RestSubData
            ): Boolean {
                return oldItem == newItem
            }
        }

        fun newInstance() : MainSubAdapter {
            return MainSubAdapter(diffUtil)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<RestSubData> {
         when(viewType){

            1 -> {
                return OpeningHoursVH(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.sub_menu_item, parent, false)
                )
            }

            2 -> {
                val view = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.sub_menu_item, parent, false)

                return ReviewsVH(view)
            }

             3 -> {
                 val view = LayoutInflater
                     .from(parent.context)
                     .inflate(R.layout.sub_menu_item, parent, false)

                 return MenuItemsVH(view)
             }

             else ->{
                return throw NullPointerException()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {

        return when(getItem(position).viewType){

            openingHoursItem -> 1

            reviewItem -> 2

            menuItem -> 3

            else -> 0
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<RestSubData>, position: Int) {
        holder.bind(getItem(position))
    }
}

data class RestSubData(
    val id: Int,
    val name: String = "",
    val description: String = "",
    val date: String = "",
    val rating: Float = 2.4F,
    val viewType: String
)