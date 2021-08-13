package com.example.restrauntapp.view.detail.adapter

import android.view.View
import android.widget.TextView
import com.example.restrauntapp.R

class OpeningHoursVH(private val view: View) : BaseViewHolder<RestSubData>(view) {

   private val tvName : TextView = view.findViewById(R.id.tv_name)
   private val tvDescription : TextView = view.findViewById(R.id.tv_description)

    override fun bind(data: RestSubData) {

        tvName.text = data.name
        tvDescription.text = data.description
    }
}