package com.demo.nerdeyesem.data.sources.remote

import com.demo.nerdeyesem.data.api.RestaurantNetApi
import com.demo.nerdeyesem.data.entities.requests.SearchRestaurantsRequest
import com.demo.nerdeyesem.data.entities.responses.SearchResponse

class RestaurantNetDataSourceImpl(
    private val restaurantNetApi: RestaurantNetApi
) : RestaurantNetDataSource {
    override suspend fun searchRestaurants(request: SearchRestaurantsRequest): SearchResponse {
        return restaurantNetApi.search(request)
    }
}