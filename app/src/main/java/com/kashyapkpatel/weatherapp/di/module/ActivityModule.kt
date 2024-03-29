package com.kashyapkpatel.weatherapp.di.module

import com.kashyapkpatel.weatherapp.ui.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class, ViewModelModule::class])
    abstract fun contributeHomeActivity(): HomeActivity
}