package com.demo.nerdeyesem.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.demo.nerdeyesem.data.entities.requests.SearchRestaurantsRequest
import com.demo.nerdeyesem.data.extensions.toDomain
import com.demo.nerdeyesem.data.extensions.toEntity
import com.demo.nerdeyesem.data.sources.local.RestaurantLocalDataSource
import com.demo.nerdeyesem.data.sources.remote.RestaurantNetDataSource
import com.demo.nerdeyesem.domain.entities.EntityType
import com.demo.nerdeyesem.domain.entities.OrderType
import com.demo.nerdeyesem.domain.entities.Restaurant
import com.demo.nerdeyesem.domain.entities.SortType
import com.demo.nerdeyesem.domain.repositories.RestaurantRepository


class RestaurantRepositoryImpl(
    private val restaurantLocalDataSource: RestaurantLocalDataSource,
    private val restaurantNetDataSource: RestaurantNetDataSource
) : RestaurantRepository {
    override suspend fun searchRestaurants(
        location: Pair<Double, Double>,
        entityType: EntityType,
        sort: SortType,
        order: OrderType
    ) {
        val request = SearchRestaurantsRequest(
            location.first,
            location.second,
            entityType.toString(),
            sort.toString(),
            order.toString()
        )
        try {
            val response = restaurantNetDataSource.searchRestaurants(request)
            val restaurants = response.restaurantItems.map { it.restaurant.toEntity() }
            restaurantLocalDataSource.createRestaurants(restaurants)
        } catch (e: Exception) {
            //Notify error if needed
        }
    }

    override fun getRestaurantsObservable(): LiveData<List<Restaurant>> {
        return Transformations.map(restaurantLocalDataSource.getRestaurantsObservable()) { entities ->
            entities.map { it.toDomain() }
        }
    }
}