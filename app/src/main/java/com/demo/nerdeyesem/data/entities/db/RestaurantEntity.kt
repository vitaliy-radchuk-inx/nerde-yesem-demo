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
    @ColumnInfo(name = "phones") val phones: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "votes") val votes: Long,
    @ColumnInfo(name = "highlights") val highlights: String
)
