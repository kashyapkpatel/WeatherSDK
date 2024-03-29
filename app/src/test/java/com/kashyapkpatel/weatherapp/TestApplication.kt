package com.kashyapkpatel.weatherapp

import com.kashyapkpatel.weatherapp.base.BaseTest
import com.kashyapkpatel.weatherapp.di.component.DaggerTestAppComponent
import com.kashyapkpatel.weathersdk.network.repo.DefaultWeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.robolectric.TestLifecycleApplication
import java.lang.reflect.Method
import javax.inject.Inject

class TestApplication : MyApp(), TestLifecycleApplication {

    @Inject
    lateinit var testCoroutineDispatcher: TestCoroutineDispatcher

    @Inject
    lateinit var mockWebServer: MockWebServer

    @Inject
    lateinit var weatherRepository: com.kashyapkpatel.weathersdk.network.repo.DefaultWeatherRepository

    override fun initDagger() = DaggerTestAppComponent.builder()
        .create(this)
        .inject(this)

    override fun onCreate() {
        initDagger()
    }

    override fun beforeTest(method: Method?) {
        println("Test Will Start")
    }

    @ExperimentalCoroutinesApi
    override fun prepareTest(test: Any?) {
        if (test is BaseTest) {
            test.testCoroutineDispatcher = this.testCoroutineDispatcher
            test.mockWebServer = this.mockWebServer
            test.weatherRepository = this.weatherRepository
        }
        println("Test Being Prepared")
    }

    override fun afterTest(method: Method?) {
        println("Test Completed")
    }
}