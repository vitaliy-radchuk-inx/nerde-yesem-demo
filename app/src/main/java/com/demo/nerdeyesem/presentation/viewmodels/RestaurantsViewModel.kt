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
    private val errorMessageLiveData = MutableLiveData<String>()

    override fun onCleared() {
        super.onCleared()
        locationHelper.dispose()
    }

    fun restaurants(): LiveData<List<RestaurantModel>> = restaurantsLiveData

    fun showPlaceholder(): LiveData<Boolean> = showPlaceholderLiveData

    fun showProgress(): LiveData<Boolean> = showProgressLiveData

    fun showError(): LiveData<String> = errorMessageLiveData

    fun requestLocation(context: Context) {
        showProgressLiveData.value = true
        locationHelper.requestLocation(context)
    }

    fun cityLocation(city: String) {
        if (city.isEmpty()) {
            return
        }
        showProgressLiveData.value = true
        (viewModelScope + CoroutineExceptionHandler { _, throwable ->
            notifyErrorMessage(throwable.message)
            showProgressLiveData.value = false
        }).launch {
            val isRestaurantsFound = withContext(Dispatchers.IO) {
                restaurantOrchestrator.searchRestaurantsByCity(city)
            }
            notifySearchFinished(isRestaurantsFound)
        }
    }

    private fun locationChanged(location: Location?) {
        if (location == null) {
            //Show error
            return
        }
        (viewModelScope + CoroutineExceptionHandler { _, throwable ->
            notifyErrorMessage(throwable.message)
            showProgressLiveData.value = false
        }).launch {
            val isRestaurantsFound = withContext(Dispatchers.IO) {
                restaurantOrchestrator.searchRestaurants(location.latitude, location.longitude)
            }
            notifySearchFinished(isRestaurantsFound)
        }
    }

    private fun notifySearchFinished(isRestaurantsFound: Boolean) {
        if (!isRestaurantsFound) {
            notifyErrorMessage("Restaurants not found!")
        }
        showPlaceholderLiveData.value = !isRestaurantsFound
        showProgressLiveData.value = false
    }

    private fun notifyErrorMessage(message: String?) {
        errorMessageLiveData.value = message
        errorMessageLiveData.value = null
    }
}