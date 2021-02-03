package com.demo.nerdeyesem.data.sources.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.nerdeyesem.data.entities.RestaurantEntity

class RestaurantLocalDataSourceImpl : RestaurantLocalDataSource {
    private val restaurantsLiveData = MutableLiveData<List<RestaurantEntity>>()

    override suspend fun createRestaurants(restaurants: List<RestaurantEntity>) {
        restaurantsLiveData.postValue(restaurants)
    }

    override fun getRestaurantsObservable(): LiveData<List<RestaurantEntity>> {
        return restaurantsLiveData
    }
}