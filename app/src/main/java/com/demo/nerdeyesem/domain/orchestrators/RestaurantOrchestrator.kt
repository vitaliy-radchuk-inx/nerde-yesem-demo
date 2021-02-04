package com.demo.nerdeyesem.domain.orchestrators

import androidx.lifecycle.LiveData
import com.demo.nerdeyesem.domain.entities.Restaurant

interface RestaurantOrchestrator {
    suspend fun searchRestaurants(lat: Double, lon: Double): Boolean
    fun getRestaurantsObservable(): LiveData<List<Restaurant>>
}