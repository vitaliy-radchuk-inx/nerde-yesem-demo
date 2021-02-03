package com.demo.nerdeyesem.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.nerdeyesem.presentation.models.RestaurantModel

class RestaurantsViewModel : ViewModel() {
    private val restaurantsLiveData = MutableLiveData<List<RestaurantModel>>()

    fun restaurants(): LiveData<List<RestaurantModel>> = restaurantsLiveData
}