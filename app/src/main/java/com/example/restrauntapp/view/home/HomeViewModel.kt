package com.example.restrauntapp.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restrauntapp.RestaurauntApp
import com.example.restrauntapp.data.RestaurantEntity
import com.example.restrauntapp.data.Restaurants
import com.example.restrauntapp.network.repo.RestaurantRepo
import com.example.restrauntapp.util.ResourceUtil
import com.example.restrauntapp.view.entity.RestReviews
import com.example.restrauntapp.view.entity.RestaurantItem
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val context: RestaurauntApp, private val repo: RestaurantRepo) : ViewModel() {

    var mutableLiveData : MutableLiveData<RestaurantEntity> = MutableLiveData()
    var errorLiveData : MutableLiveData<Throwable> = MutableLiveData()
    var isLoading : MutableLiveData<Boolean> = MutableLiveData()
    private val reviewArr : MutableList<RestReviews> = mutableListOf()
    private val dataArr : MutableList<RestaurantItem> = mutableListOf()

    private var recyclerList : MutableList<RestaurantItem> = mutableListOf()

    fun fetchHomeData(): MutableList<RestaurantItem> {
        val restaurauntData = ResourceUtil.getJsonString(
                "restauraunt.json",
                context
        )

        val homeData  = Gson().fromJson(restaurauntData, RestaurantEntity::class.java)

      return prepareHomeData(homeData)
    }

    private fun prepareHomeData(homeData: RestaurantEntity): MutableList<RestaurantItem> {
        homeData.restaurants.map { rest ->
            var reviewArr  = mutableListOf<RestReviews>()
            var openingTimesArr = mutableListOf<String>()

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

            rest.operating_hours.let { openingHour ->
                openingTimesArr.add("Monday : ${openingHour.monday}")
                openingTimesArr.add("Tuesday : ${openingHour.tuesday}")
                openingTimesArr.add("Wednesday : ${openingHour.wednesday}")
                openingTimesArr.add("Thursday : ${openingHour.thursday}")
                openingTimesArr.add("Friday : ${openingHour.friday}")
                openingTimesArr.add("Saturday : ${openingHour.saturday}")
                openingTimesArr.add("Sunday : ${openingHour.sunday}")
            }

            dataArr.add(RestaurantItem(
                    id = rest.id,
                    restName = rest.name,
                    cuisineType = rest.cuisine_type,
                    loc = rest.address,
                    rating = rest.rating.toFloat(),
                    discount = "${rest.discount} % Off on Order abv Rs.300",
                    distance = "${rest.distance} miles",
                    openingHours = openingTimesArr,
                    reviews = reviewArr
            ))
        }
        return dataArr
    }

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