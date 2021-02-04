package com.demo.nerdeyesem.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.nerdeyesem.domain.orchestrators.RestaurantDetailsOrchestrator

class RestaurantDetailsViewModelFactory(
    private val restaurantDetailsOrchestrator: RestaurantDetailsOrchestrator
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantDetailsViewModel::class.java)) {
            return RestaurantDetailsViewModel(restaurantDetailsOrchestrator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}