package com.demo.nerdeyesem.domain.orchestrators

import androidx.lifecycle.LiveData
import com.demo.nerdeyesem.domain.entities.EntityType
import com.demo.nerdeyesem.domain.entities.OrderType
import com.demo.nerdeyesem.domain.entities.Restaurant
import com.demo.nerdeyesem.domain.entities.SortType
import com.demo.nerdeyesem.domain.repositories.RestaurantRepository

class RestaurantOrchestratorImpl(
    private val restaurantRepository: RestaurantRepository
) : RestaurantOrchestrator {
    override suspend fun searchRestaurants(lat: Double, lon: Double): Boolean {
        return restaurantRepository.searchRestaurants(
            location = Pair(lat, lon),
            entityType = EntityType.CITY,
            sort = SortType.REAL_DISTANCE,
            order = OrderType.ASC
        )
    }

    override suspend fun searchRestaurantsByCity(city: String): Boolean {
        return restaurantRepository.searchRestaurantsByCity(
            city = city,
            entityType = EntityType.CITY,
            sort = SortType.REAL_DISTANCE,
            order = OrderType.ASC
        )
    }

    override fun getRestaurantsObservable(): LiveData<List<Restaurant>> {
        return restaurantRepository.getRestaurantsObservable()
    }
}