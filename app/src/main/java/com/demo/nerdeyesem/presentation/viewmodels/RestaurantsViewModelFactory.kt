package com.demo.nerdeyesem.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.nerdeyesem.domain.orchestrators.RestaurantOrchestrator

class RestaurantsViewModelFactory(
    private val restaurantOrchestrator: RestaurantOrchestrator
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantsViewModel::class.java)) {
            return RestaurantsViewModel(restaurantOrchestrator) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}