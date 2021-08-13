package com.example.restrauntapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restrauntapp.R
import com.example.restrauntapp.view.detail.adapter.MainAdapter
import com.example.restrauntapp.view.detail.adapter.RestCategory
import com.example.restrauntapp.view.detail.adapter.RestSubData
import com.example.restrauntapp.view.entity.RestaurantItem
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: DetailViewModel

    private lateinit var restName : TextView
    private lateinit var tv_cuisine : TextView
    private lateinit var tv_addr : TextView
    private lateinit var tv_distance : TextView
    private lateinit var ratings : RatingBar
    private lateinit var tv_discount : TextView
    private lateinit var etSearch : EditText

    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private val dataArr : MutableList<RestCategory> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        etSearch = findViewById(R.id.et_search)
        restName = findViewById(R.id.restName)
        tv_cuisine = findViewById(R.id.tv_cuisine)
        tv_addr = findViewById(R.id.tv_addr)
        tv_distance = findViewById(R.id.tv_distance)
        ratings = findViewById(R.id.ratings)
        tv_discount = findViewById(R.id.tv_discount)
        recyclerView = findViewById(R.id.rv_detail)

        //current screen data
        val categoryData  = viewModel.fetchData()

        //previous screen data
        val restaurantData = intent.getSerializableExtra("restItem") as RestaurantItem

        val dataList = viewModel.prepareData(restaurantData, categoryData)
        dataArr.addAll(dataList)

        setListenerNData(restaurantData)
    }

    private fun setListenerNData(restaurantData: RestaurantItem) {

        restaurantData.let { restItem ->
            restName.text = restItem.restName
            tv_cuisine.text = restItem.cuisineType
            tv_addr.text = restItem.loc
            tv_distance.text = restItem.distance
            tv_discount.text = restItem.discount
            ratings.rating = restItem.rating
        }

        val onItemClick : (Int, Int, Boolean) -> Unit = {position, id, isExpand ->
            val expand = adapter.getValueAtPosition(position).isExpanded
            adapter.getValueAtPosition(position).isExpanded = !expand
            adapter.notifyItemChanged(position)
        }

        adapter = MainAdapter.newInstance(onItemClick)
        recyclerView.adapter = adapter
        adapter.submitData(dataArr)

        etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

    }

}