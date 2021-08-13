package com.example.restrauntapp.view.detail.adapter

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import com.example.restrauntapp.R

class ReviewsVH(private val view : View)
    : BaseViewHolder<RestSubData>(view) {

    private val tvName : TextView = view.findViewById(R.id.tv_name)
    private val tvDescription : TextView = view.findViewById(R.id.tv_description)
    private val tvDate : TextView = view.findViewById(R.id.tv_date)
    private val ratings : RatingBar = view.findViewById(R.id.ratings)

    override fun bind(data: RestSubData) {

        data.rating.let {
            ratings.visibility = View.VISIBLE
            ratings.rating = data.rating
        }
        data.date.let {
            tvDate.visibility = View.VISIBLE
            tvDate.text = data.date
        }
        tvName.text = data.name
        tvDescription.text = data.description
    }
}