package com.kashyapkpatel.weatherapp.di.module

import android.content.Context
import com.kashyapkpatel.weathersdk.network.api.WeatherApi
import com.kashyapkpatel.weathersdk.network.db.LocationDao
import com.kashyapkpatel.weathersdk.network.repo.DefaultWeatherRepository
import com.kashyapkpatel.weathersdk.network.repo.WeatherRepository
import com.kashyapkpatel.weathersdk.util.TestDispatcherProvider
import com.kashyapkpatel.weathersdk.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.test.TestCoroutineDispatcher

@Module
class TestRepoModule {

    /**
     * The method returns the TestCoroutineDispatcher object
     */
    @Provides
    fun provideTestCoroutineDispatcher(): TestCoroutineDispatcher {
        return TestCoroutineDispatcher()
    }

    /**
     * The method returns the DispatcherProvider object
     */
    @Provides
    fun provideTestDispatcherProvider(testCoroutineDispatcher: TestCoroutineDispatcher): DispatcherProvider {
        return TestDispatcherProvider(testCoroutineDispatcher)
    }

    /**
     * The method returns the DefaultWeatherRepository object
     */
    @Provides
    fun provideDefaultWeatherRepository(context: Context,
                                        dispatcherProvider: TestDispatcherProvider,
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