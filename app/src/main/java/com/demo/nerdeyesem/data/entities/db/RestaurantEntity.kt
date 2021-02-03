package com.demo.nerdeyesem.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "cuisines") val cuisines: String,
    @ColumnInfo(name = "timings") val timings: String,
    @ColumnInfo(name = "phone_numbers") val phoneNumbers: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "locality") val locality: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "city_id") val cityId: Long,
    @ColumnInfo(name = "lat") val lat: Double,
    @ColumnInfo(name = "lon") val lon: Double,
    @ColumnInfo(name = "zip_code") val zipCode: String,
    @ColumnInfo(name = "locality_verbose") val localityVerbose: String,
    @ColumnInfo(name = "aggregate_rating") val aggregateRating: Double,
    @ColumnInfo(name = "rating_text") val ratingText: String,
    @ColumnInfo(name = "rating_color") val ratingColor: String,
    @ColumnInfo(name = "votes") val votes: Long
)
