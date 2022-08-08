package com.kashyapkpatel.weatherapp.di.component

import com.kashyapkpatel.weatherapp.MyApp
import com.kashyapkpatel.weatherapp.di.module.*
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        DatabaseModule::class,
        RepoModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>()
}