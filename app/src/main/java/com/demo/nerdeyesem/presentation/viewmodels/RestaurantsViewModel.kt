package com.demo.nerdeyesem.presentation.viewmodels

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demo.nerdeyesem.presentation.models.RestaurantModel
import com.demo.nerdeyesem.shared.LocationHelper

class RestaurantsViewModel() : ViewModel() {

    private val locationHelper by lazy {
        LocationHelper { location -> locationChanged(location) }
    }
    private val restaurantsLiveData = MutableLiveData<List<RestaurantModel>>()
    private val showPlaceholderLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        locationHelper.dispose()
    }

    fun restaurants(): LiveData<List<RestaurantModel>> = restaurantsLiveData

    fun showPlaceholder(): LiveData<Boolean> = showPlaceholderLiveData

    fun requestLocation(context: Context) {
        locationHelper.requestLocation(context)
    }

    private fun locationChanged(location: Location?) {
        Log.w("LOG:::", "${location?.latitude}::${location?.longitude}")
    }
}