package com.example.restrauntapp.view.detail.adapter.subcategory

import android.view.View
import android.widget.TextView
import com.example.restrauntapp.R
import com.example.restrauntapp.view.detail.adapter.RestSubData

class MenuVH (private val view : View)
    : BaseViewHolder<RestSubData>(view) {

    private val tvName : TextView = view.findViewById(R.id.tv_name)
    private val tvDescription : TextView = view.findViewById(R.id.tv_description)

    override fun bind(data: RestSubData) {

        tvName.text = data.name
        tvDescription.text = data.description

    }
}