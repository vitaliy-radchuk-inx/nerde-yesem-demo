package com.demo.nerdeyesem.data.sources.local

import androidx.lifecycle.LiveData
import com.demo.nerdeyesem.data.entities.db.RestaurantEntity


interface RestaurantLocalDataSource {
    suspend fun createRestaurants(restaurants: List<RestaurantEntity>)
    fun getRestaurantsObservable(): LiveData<List<RestaurantEntity>>
}