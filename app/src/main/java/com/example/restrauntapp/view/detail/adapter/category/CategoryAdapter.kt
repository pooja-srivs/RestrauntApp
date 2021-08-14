package com.example.restrauntapp.view.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.*
import com.example.restrauntapp.R
import com.example.restrauntapp.view.detail.adapter.category.CategoryViewHolder

class MainAdapter private constructor(
    private val diffUtil: DiffUtil.ItemCallback<RestCategory>,
    private val onItemClick: (Int, Int, Boolean) -> Unit
) : ListAdapter<RestCategory, CategoryViewHolder>(diffUtil), Filterable {

    private var context : Context? = null
    private var categoryData : MutableList<RestCategory> = mutableListOf()
    private var categoryFilteredData : MutableList<RestCategory> = mutableListOf()

    companion object{
        private val diffUtil = object : DiffUtil.ItemCallback<RestCategory>() {
            override fun areItemsTheSame(oldItem: RestCategory, newItem: RestCategory): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RestCategory, newItem: RestCategory): Boolean {
                return oldItem == newItem
            }
        }

        fun newInstance(onItemClick: (Int, Int, Boolean) -> Unit) : MainAdapter{
            return MainAdapter(diffUtil, onItemClick)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        context = null
    }

    fun submitData(restCategoryData: MutableList<RestCategory>){
        categoryData.addAll(restCategoryData)
        submitList(categoryData)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.main_menu_item, parent, false)
        )
    }

    fun getValueAtPosition(position: Int): RestCategory{
        return getItem(position)
    }
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick)
    }


    //perform filter
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    categoryFilteredData = categoryData
                } else {
                    val filteredList: MutableList<RestCategory> = mutableListOf()

                    categoryData.forEach { rest ->

                        if (rest.title.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(rest)
                        }
                    }
                    categoryFilteredData = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = categoryFilteredData
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                categoryFilteredData = filterResults.values as MutableList<RestCategory>
                submitList(categoryFilteredData)
            }
        }
    }
}

data class RestCategory(
    val id: Int,
    val title: String,
    val subList: MutableList<RestSubData>,
    var isExpanded: Boolean
)