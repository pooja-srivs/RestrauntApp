package com.example.restrauntapp.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.restrauntapp.R
import com.example.restrauntapp.util.ResourceUtil
import com.example.restrauntapp.view.detail.DetailActivity
import com.example.restrauntapp.view.entity.RestaurantItem
import com.example.restrauntapp.view.home.adapter.RestaurantAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var etSearch : EditText
    private lateinit var parent : ConstraintLayout
    private lateinit var adapter : RestaurantAdapter
    private lateinit var recyclerView : RecyclerView
    private val homeDataArr : MutableList<RestaurantItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parent = findViewById(R.id.parent)
        recyclerView = findViewById(R.id.rv_home)
        etSearch = findViewById(R.id.et_search)

        ResourceUtil.hideKeyboard(parent)

        val homeList = viewModel.fetchHomeData()
        homeDataArr.addAll(homeList)

        val onItemClick : (RestaurantItem) -> Unit = { restItem ->

            startActivity(Intent(this, DetailActivity::class.java).putExtra("restItem", restItem))
        }

        adapter = RestaurantAdapter.newInstance(onItemClick)
        recyclerView.adapter = adapter
        adapter.submitData(homeDataArr)

        setListener()
    }

    private fun setListener(){
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