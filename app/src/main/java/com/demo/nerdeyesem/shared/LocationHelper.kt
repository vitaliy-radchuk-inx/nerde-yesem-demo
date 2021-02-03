package com.demo.nerdeyesem.shared

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.content.ContextCompat

class LocationHelper(private val onLocationChanged: (Location?) -> Unit) {

    companion object {
        internal const val MIN_TIME_MS = 500L
        internal const val MIN_DISTANCE_M = 50F
    }

    private val locationListener by lazy {
        LocationListener { location -> onLocationUpdates(location) }
    }
    private var locationManager: LocationManager? = null

    @SuppressLint("MissingPermission")
    fun requestLocation(context: Context) {
        if (locationManager == null) {
            locationManager = initLocationManager(context)
        }

        locationManager?.getBestProvider(Criteria(), false)?.let { provider ->
            val location = locationManager?.getLastKnownLocation(provider)
            if (location == null) {
                locationManager?.requestLocationUpdates(
                    provider,
                    MIN_TIME_MS,
                    MIN_DISTANCE_M,
                    locationListener
                )
            } else {
                onLocationChanged(location)
            }
        }
    }

    fun dispose() {
        runCatching {
            locationManager?.removeUpdates(locationListener)
        }
    }

    private fun onLocationUpdates(location: Location?) {
        onLocationChanged(location)
        locationManager?.removeUpdates(locationListener)
    }

    private fun initLocationManager(context: Context): LocationManager? {
        return ContextCompat.getSystemService(context, LocationManager::class.java)
    }
}