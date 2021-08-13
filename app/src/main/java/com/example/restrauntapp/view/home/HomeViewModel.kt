package com.example.restrauntapp.view.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restrauntapp.data.RestaurantEntity
import com.example.restrauntapp.data.Restaurants
import com.example.restrauntapp.network.repo.RestaurantRepo
import com.example.restrauntapp.view.entity.RestReviews
import com.example.restrauntapp.view.entity.RestaurantItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val repo: RestaurantRepo) : ViewModel() {

    var mutableLiveData : MutableLiveData<RestaurantEntity> = MutableLiveData()
    var errorLiveData : MutableLiveData<Throwable> = MutableLiveData()
    var isLoading : MutableLiveData<Boolean> = MutableLiveData()
    private val reviewArr : MutableList<RestReviews> = mutableListOf()

    private var recyclerList : MutableList<RestaurantItem> = mutableListOf()

    fun getRestaurantList(){
        repo.fetchRestaurantList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       prepareData(it.restaurants)
            }, {
                errorLiveData.postValue(it)
            })
    }

    private fun prepareData(restaurants: List<Restaurants>) {
        restaurants.map { rest ->
            rest.reviews.map { review ->
                reviewArr.add(
                        RestReviews(
                                id = review.id,
                                name = review.name,
                                date = review.date,
                                rating = review.rating,
                                comments = review.comments
                        )
                )
            }

            Log.d("*** Review Arr Size >>>", ""+reviewArr.size)

            recyclerList.add(RestaurantItem(
                    id = rest.id,
                    restName = rest.name,
                    cuisineType = rest.cuisine_type,
                    loc = rest.address,
                    rating = rest.rating.toFloat(),
                    discount = "${rest.discount} % Off on Order abv Rs.300",
                    distance = "${rest.distance} miles",
                    openingHours = listOf<String>(
                            rest.operating_hours.monday,
                            rest.operating_hours.tuesday,
                            rest.operating_hours.wednesday,
                            rest.operating_hours.thursday,
                            rest.operating_hours.friday,
                            rest.operating_hours.saturday,
                            rest.operating_hours.sunday,
                    ),
                    reviews = reviewArr
            ))
        }
        }

}