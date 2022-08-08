package com.kashyapkpatel.weatherapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.kashyapkpatel.weatherapp.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProvideFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}