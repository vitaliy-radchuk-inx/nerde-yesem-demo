package com.demo.nerdeyesem.domain.orchestrators

import androidx.lifecycle.LiveData
import com.demo.nerdeyesem.domain.entities.Restaurant

interface RestaurantDetailsOrchestrator {
    fun getRestaurantDetailsObservable(id: String): LiveData<Restaurant?>
}