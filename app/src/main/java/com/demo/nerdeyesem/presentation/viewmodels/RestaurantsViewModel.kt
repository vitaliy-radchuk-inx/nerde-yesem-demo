package com.demo.nerdeyesem.presentation.viewmodels

import android.content.Context
import android.location.Location
import androidx.lifecycle.*
import com.demo.nerdeyesem.domain.orchestrators.RestaurantOrchestrator
import com.demo.nerdeyesem.presentation.extensions.toModel
import com.demo.nerdeyesem.presentation.models.RestaurantModel
import com.demo.nerdeyesem.shared.LocationHelper
import kotlinx.coroutines.*

class RestaurantsViewModel(
    private val restaurantOrchestrator: RestaurantOrchestrator
) : ViewModel() {

    private val locationHelper by lazy {
        LocationHelper { location -> locationChanged(location) }
    }
    private val restaurantsLiveData =
        Transformations.map(restaurantOrchestrator.getRestaurantsObservable()) { restaurants ->
            restaurants.map { it.toModel() }
        }
    private val showPlaceholderLiveData = MutableLiveData<Boolean>()
    private val showProgressLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        locationHelper.dispose()
    }

    fun restaurants(): LiveData<List<RestaurantModel>> = restaurantsLiveData

    fun showPlaceholder(): LiveData<Boolean> = showPlaceholderLiveData

    fun showProgress(): LiveData<Boolean> = showProgressLiveData

    fun requestLocation(context: Context) {
        showProgressLiveData.value = true
        locationHelper.requestLocation(context)
    }

    private fun locationChanged(location: Location?) {
        if (location == null) {
            //Show error
            return
        }
        (viewModelScope + CoroutineExceptionHandler { _, _ ->
            //Show error
            showProgressLiveData.value = false
        }).launch {
            val isRestaurantsFound = withContext(Dispatchers.IO) {
                restaurantOrchestrator.searchRestaurants(location.latitude, location.longitude)
            }
            showPlaceholderLiveData.value = !isRestaurantsFound
            showProgressLiveData.value = false
        }
    }
}