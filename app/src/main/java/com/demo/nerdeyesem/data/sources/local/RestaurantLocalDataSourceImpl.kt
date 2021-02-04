package com.demo.nerdeyesem.data.sources.local

import androidx.lifecycle.LiveData
import com.demo.nerdeyesem.data.db.dao.RestaurantDao
import com.demo.nerdeyesem.data.entities.db.RestaurantEntity

class RestaurantLocalDataSourceImpl(
    private val restaurantDao: RestaurantDao
) : RestaurantLocalDataSource {

    override suspend fun createRestaurants(restaurants: List<RestaurantEntity>) {
        restaurantDao.createRestaurants(restaurants)
    }

    override fun getRestaurantsObservable(): LiveData<List<RestaurantEntity>> {
        return restaurantDao.getRestaurantsObservable()
    }

    override fun getRestaurantDetailsObservable(id: String): LiveData<RestaurantEntity?> {
        return restaurantDao.getRestaurantDetailsObservable(id)
    }
}