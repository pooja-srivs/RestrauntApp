package com.example.restrauntapp.view.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.example.restrauntapp.R

class MainAdapter private constructor(
    private val diffUtil: DiffUtil.ItemCallback<RestCategory>,
    private val onItemClick: (Int, Int, Boolean) -> Unit
) : ListAdapter<RestCategory, MainAdapter.MainViewHolder>(diffUtil) {

    private var context : Context? = null

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.main_menu_item, parent, false)
        )
    }

    fun getValueAtPosition(position: Int): RestCategory{
        return getItem(position)
    }
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClick, context)
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val tvTitle : TextView = view.findViewById(R.id.tv_title)
        private val rvSubList : RecyclerView = view.findViewById(R.id.rv_sublist)
        private val viewBorder : View = view.findViewById(R.id.view_border)

        fun bind(
            restCategoryData: RestCategory,
            onItemClick: (Int, Int, Boolean) -> Unit,
            context: Context?
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
}

data class RestCategory(
    val id: Int,
    val title: String,
    val subList: MutableList<RestSubData>,
    var isExpanded: Boolean
)