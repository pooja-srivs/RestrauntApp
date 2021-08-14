package com.example.restrauntapp.view.detail.adapter.subcategory

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<RestSubData>(view : View) : RecyclerView.ViewHolder(view){

    abstract fun bind(data : RestSubData)
}