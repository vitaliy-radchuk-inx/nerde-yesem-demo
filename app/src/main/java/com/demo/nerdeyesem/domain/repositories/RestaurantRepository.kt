package com.demo.nerdeyesem.domain.repositories

import androidx.lifecycle.LiveData
import com.demo.nerdeyesem.domain.entities.EntityType
import com.demo.nerdeyesem.domain.entities.OrderType
import com.demo.nerdeyesem.domain.entities.Restaurant
import com.demo.nerdeyesem.domain.entities.SortType

interface RestaurantRepository {
    suspend fun searchRestaurants(
        location: Pair<Double, Double>,
        entityType: EntityType,
        sort: SortType,
        order: OrderType
    ): Boolean

    suspend fun searchRestaurantsByCity(
        city: String,
        entityType: EntityType,
        sort: SortType,
        order: OrderType
    ): Boolean

    fun getRestaurantsObservable(): LiveData<List<Restaurant>>

    fun getRestaurantDetailsObservable(id: String): LiveData<Restaurant?>
}