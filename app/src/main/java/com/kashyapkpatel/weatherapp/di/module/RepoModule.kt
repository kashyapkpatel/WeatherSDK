package com.kashyapkpatel.weatherapp.di.module

import android.content.Context
import com.kashyapkpatel.weathersdk.network.api.WeatherApi
import com.kashyapkpatel.weathersdk.network.db.LocationDao
import com.kashyapkpatel.weathersdk.network.repo.DefaultWeatherRepository
import com.kashyapkpatel.weathersdk.network.repo.WeatherRepository
import com.kashyapkpatel.weathersdk.util.DefaultDispatcherProvider
import com.kashyapkpatel.weathersdk.util.DispatcherProvider
import dagger.Module
import dagger.Provides

@Module
class RepoModule {



    /**
     * The method returns the DispatcherProvider object
     */
    @Provides
    fun provideDefaultDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }

    /**
     * The method returns the DefaultWeatherRepository object
     */
    @Provides
    fun provideDefaultWeatherRepository(
        context: Context,
        dispatcherProvider: DefaultDispatcherProvider,
        locationDao: LocationDao,
        weatherApi: WeatherApi
    ): WeatherRepository {
        return DefaultWeatherRepository(
            context,
            dispatcherProvider,
            locationDao,
            weatherApi
        )
    }
}