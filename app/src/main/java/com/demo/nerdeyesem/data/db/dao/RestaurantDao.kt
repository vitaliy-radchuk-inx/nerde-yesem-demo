package com.demo.nerdeyesem.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.nerdeyesem.data.entities.db.RestaurantEntity

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    fun getRestaurantsObservable(): LiveData<List<RestaurantEntity>>

    @Query("SELECT * FROM restaurants WHERE id = :id")
    fun getRestaurantDetailsObservable(id: String): LiveData<RestaurantEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createRestaurants(restaurants: List<RestaurantEntity>)

    @Query("DELETE FROM restaurants")
    fun deleteRestaurants()
}