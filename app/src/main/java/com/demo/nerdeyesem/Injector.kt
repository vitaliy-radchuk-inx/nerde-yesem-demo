package com.demo.nerdeyesem

import android.content.Context
import com.demo.nerdeyesem.domain.orchestrators.RestaurantDetailsOrchestrator
import com.demo.nerdeyesem.domain.orchestrators.RestaurantOrchestrator


interface Injector {

    fun restaurantOrchestrator(): RestaurantOrchestrator

    fun restaurantsDetailsOrchestrator(): RestaurantDetailsOrchestrator

    companion object {

        @Volatile
        private var instance: Injector? = null

        fun init(context: Context) {
            instance ?: synchronized(this) {
                instance ?: InjectorImpl(context).also { instance = it }
            }
        }

        fun instance(): Injector {
            return instance ?: throw IllegalStateException("Injector is not initialised")
        }
    }
}
