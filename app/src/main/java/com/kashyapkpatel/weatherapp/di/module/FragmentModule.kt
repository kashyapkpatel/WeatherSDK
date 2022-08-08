package com.kashyapkpatel.weatherapp.di.module

import com.kashyapkpatel.weatherapp.ui.AppInfoFragment
import com.kashyapkpatel.weatherapp.ui.LocationsFragment
import com.kashyapkpatel.weatherapp.ui.WeatherInfoFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeLocationsFragment(): LocationsFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherInfoFragment(): WeatherInfoFragment

    @ContributesAndroidInjector
    abstract fun contributeAppInfoFragment(): AppInfoFragment
}