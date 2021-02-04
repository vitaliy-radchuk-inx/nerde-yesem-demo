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
    ): Boolean {
        val request = SearchRestaurantsRequest(
            location.first,
            location.second,
            entityType.toString(),
            sort.toString(),
            order.toString()
        )
        return try {
            val response = restaurantNetDataSource.searchRestaurants(request)
            if (response.restaurantItems.isEmpty()) {
                false
            } else {
                val restaurants = response.restaurantItems.map { it.restaurant.toEntity() }
                restaurantLocalDataSource.deleteRestaurants()
                restaurantLocalDataSource.createRestaurants(restaurants)
                true
            }
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun searchRestaurantsByCity(
        city: String,
        entityType: EntityType,
        sort: SortType,
        order: OrderType
    ): Boolean {
        return try {
            val location = restaurantNetDataSource.cityLocation(city)
            val suggestion = location.locationSuggestions.firstOrNull {
                it.cityName.equals(city, ignoreCase = true)
            }
            if (suggestion == null) {
                false
            } else {
                searchRestaurants(
                    Pair(suggestion.lat.toDouble(), suggestion.lon.toDouble()),
                    entityType,
                    sort,
                    order
                )
            }
        } catch (e: Exception) {
            false
        }
    }

    override fun getRestaurantsObservable(): LiveData<List<Restaurant>> {
        return Transformations.map(restaurantLocalDataSource.getRestaurantsObservable()) { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getRestaurantDetailsObservable(id: String): LiveData<Restaurant?> {
        return Transformations.map(restaurantLocalDataSource.getRestaurantDetailsObservable(id)) { entity ->
            entity?.toDomain()
        }
    }
}