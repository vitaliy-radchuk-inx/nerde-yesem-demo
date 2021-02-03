package com.demo.nerdeyesem.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.nerdeyesem.presentation.models.RestaurantModel

class RestaurantsViewModel : ViewModel() {
    private val restaurantsLiveData = MutableLiveData<List<RestaurantModel>>()
    private val showPlaceholderLiveData = MutableLiveData<Boolean>()

    fun restaurants(): LiveData<List<RestaurantModel>> = restaurantsLiveData

    fun showPlaceholder(): LiveData<Boolean> = showPlaceholderLiveData
}