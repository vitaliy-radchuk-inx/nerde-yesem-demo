package com.demo.nerdeyesem.data.sources.remote

import com.demo.nerdeyesem.data.entities.requests.SearchRestaurantsRequest
import com.demo.nerdeyesem.data.entities.responses.SearchResponse


interface RestaurantNetDataSource {
    suspend fun searchRestaurants(request: SearchRestaurantsRequest): SearchResponse
}
