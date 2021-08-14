package com.example.restrauntapp.view.detail.adapter.category

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restrauntapp.R
import com.example.restrauntapp.view.detail.adapter.MainSubAdapter
import com.example.restrauntapp.view.detail.adapter.RestCategory

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val tvTitle : TextView = view.findViewById(R.id.tv_title)
    private val rvSubList : RecyclerView = view.findViewById(R.id.rv_sublist)
    private val viewBorder : View = view.findViewById(R.id.view_border)

    fun bind(
            restCategoryData: RestCategory,
            onItemClick: (Int, Int, Boolean) -> Unit
    ){

        //set the view data
        tvTitle.text = restCategoryData.title
        viewBorder.visibility = View.VISIBLE

        //set sublist adapter
        val mainSubAdapter = MainSubAdapter.newInstance()
        rvSubList.adapter = mainSubAdapter
        rvSubList.setHasFixedSize(true)
        mainSubAdapter.submitList(restCategoryData.subList)

        if (restCategoryData.isExpanded){
            rvSubList.visibility = View.VISIBLE
            tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0)

        }else{
            rvSubList.visibility = View.GONE
            tvTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_up, 0)
        }

        itemView.setOnClickListener {
            onItemClick(absoluteAdapterPosition, restCategoryData.id, restCategoryData.isExpanded)
        }
    }
}
