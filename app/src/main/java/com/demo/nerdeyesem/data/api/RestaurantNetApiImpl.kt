package com.demo.nerdeyesem.data.api

import com.demo.nerdeyesem.data.entities.requests.SearchRestaurantsRequest
import com.demo.nerdeyesem.data.entities.responses.SearchResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class RestaurantNetApiImpl(private val retrofit: Retrofit) : RestaurantNetApi {

    internal interface Api {
        @GET("search")
        suspend fun search(
            @Query("entity_type") entityType: String,
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("sort") sort: String,
            @Query("order") order: String,
        ): SearchResponse
    }

    private val restaurantApi by lazy { retrofit.create(Api::class.java) }

    override suspend fun search(request: SearchRestaurantsRequest): SearchResponse {
        return restaurantApi.search(
            entityType = request.entityType,
            lat = request.lat,
            lon = request.lon,
            sort = request.sort,
            order = request.order
        )
    }
}