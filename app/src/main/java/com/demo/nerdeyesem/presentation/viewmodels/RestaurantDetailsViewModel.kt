package com.demo.nerdeyesem.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.demo.nerdeyesem.domain.orchestrators.RestaurantDetailsOrchestrator
import com.demo.nerdeyesem.presentation.extensions.toModel
import com.demo.nerdeyesem.presentation.models.RestaurantModel

class RestaurantDetailsViewModel(
    private val restaurantDetailsOrchestrator: RestaurantDetailsOrchestrator
) : ViewModel() {

    private val restaurantIdLiveData = MutableLiveData<String>()
    private val restaurantDetailsLiveData =
        Transformations.switchMap(restaurantIdLiveData) { id ->
            Transformations.map(restaurantDetailsOrchestrator.getRestaurantDetailsObservable(id)) { restaurant ->
                restaurant?.toModel()
            }
        }

    fun loadDetails(id: String) {
        if (restaurantIdLiveData.value == id) return
        restaurantIdLiveData.value = id
    }

    fun details(): LiveData<RestaurantModel?> = restaurantDetailsLiveData
}