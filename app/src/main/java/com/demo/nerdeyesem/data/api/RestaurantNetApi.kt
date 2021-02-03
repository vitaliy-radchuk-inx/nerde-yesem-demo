package com.demo.nerdeyesem.data.api

import com.demo.nerdeyesem.data.entities.requests.SearchRestaurantsRequest
import com.demo.nerdeyesem.data.entities.responses.SearchResponse

interface RestaurantNetApi {
    suspend fun search(request: SearchRestaurantsRequest): SearchResponse
}