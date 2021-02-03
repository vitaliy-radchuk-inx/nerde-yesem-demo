package com.demo.nerdeyesem.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.nerdeyesem.data.db.AppDatabase.Companion.DB_VERSION
import com.demo.nerdeyesem.data.db.dao.RestaurantDao
import com.demo.nerdeyesem.data.entities.db.RestaurantEntity

@Database(
    entities = [RestaurantEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        private const val DB_NAME = "app_database.db"
        internal const val DB_VERSION = 1

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}