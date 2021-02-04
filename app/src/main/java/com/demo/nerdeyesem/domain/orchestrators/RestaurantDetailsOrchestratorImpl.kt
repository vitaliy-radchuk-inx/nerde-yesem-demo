package com.demo.nerdeyesem.domain.orchestrators

import androidx.lifecycle.LiveData
import com.demo.nerdeyesem.domain.entities.Restaurant
import com.demo.nerdeyesem.domain.repositories.RestaurantRepository

class RestaurantDetailsOrchestratorImpl(
    private val restaurantRepository: RestaurantRepository
) : RestaurantDetailsOrchestrator {

    override fun getRestaurantDetailsObservable(id: String): LiveData<Restaurant?> {
        return restaurantRepository.getRestaurantDetailsObservable(id)
    }
}