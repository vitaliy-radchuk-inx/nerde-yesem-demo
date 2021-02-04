package com.demo.nerdeyesem

import android.content.Context
import com.demo.nerdeyesem.data.api.AuthInterceptor
import com.demo.nerdeyesem.data.api.RestaurantNetApi
import com.demo.nerdeyesem.data.api.RestaurantNetApiImpl
import com.demo.nerdeyesem.data.db.AppDatabase
import com.demo.nerdeyesem.data.repositories.RestaurantRepositoryImpl
import com.demo.nerdeyesem.data.sources.local.RestaurantLocalDataSource
import com.demo.nerdeyesem.data.sources.local.RestaurantLocalDataSourceImpl
import com.demo.nerdeyesem.data.sources.remote.RestaurantNetDataSource
import com.demo.nerdeyesem.data.sources.remote.RestaurantNetDataSourceImpl
import com.demo.nerdeyesem.domain.orchestrators.RestaurantDetailsOrchestrator
import com.demo.nerdeyesem.domain.orchestrators.RestaurantDetailsOrchestratorImpl
import com.demo.nerdeyesem.domain.orchestrators.RestaurantOrchestrator
import com.demo.nerdeyesem.domain.orchestrators.RestaurantOrchestratorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class InjectorImpl(context: Context) : Injector {

    private val apiClient: OkHttpClient by lazy {
        initApiClient()
    }
    private val retrofit: Retrofit by lazy {
        initRetrofit(apiClient)
    }
    private val database: AppDatabase by lazy {
        AppDatabase.getInstance(context)
    }
    private val restaurantLocalDataSource: RestaurantLocalDataSource by lazy {
        RestaurantLocalDataSourceImpl(database.restaurantDao())
    }
    private val restaurantNetApi: RestaurantNetApi by lazy {
        RestaurantNetApiImpl(retrofit)
    }
    private val restaurantNetDataSource: RestaurantNetDataSource by lazy {
        RestaurantNetDataSourceImpl(restaurantNetApi)
    }
    private val restaurantRepository = RestaurantRepositoryImpl(
        restaurantLocalDataSource, restaurantNetDataSource
    )

    companion object {
        private const val CONNECTION_TIMEOUT = 60L
        private const val READ_TIMEOUT = 60L
    }

    override fun restaurantOrchestrator(): RestaurantOrchestrator {
        return RestaurantOrchestratorImpl(restaurantRepository)
    }

    override fun restaurantsDetailsOrchestrator(): RestaurantDetailsOrchestrator {
        return RestaurantDetailsOrchestratorImpl(restaurantRepository)
    }

    private fun initApiClient(): OkHttpClient {
        synchronized(this) {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val auth = AuthInterceptor(BuildConfig.API_USER_KEY)

            return OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .addInterceptor(auth)
                .build()
        }
    }

    private fun initRetrofit(apiClient: OkHttpClient): Retrofit {
        synchronized(this) {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(apiClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}