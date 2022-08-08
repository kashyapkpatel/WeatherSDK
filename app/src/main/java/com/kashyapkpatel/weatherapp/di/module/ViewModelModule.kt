package com.kashyapkpatel.weatherapp.di.module

import androidx.lifecycle.ViewModel
import com.kashyapkpatel.weatherapp.di.ViewModelKey
import com.kashyapkpatel.weatherapp.viewmodel.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindWeatherViewModel(weatherViewModel: WeatherViewModel): ViewModel
}