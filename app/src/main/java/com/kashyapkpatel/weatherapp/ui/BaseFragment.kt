package com.kashyapkpatel.weatherapp.ui

import android.content.Context
import com.kashyapkpatel.weatherapp.di.ViewModelProviderFactory
import com.kashyapkpatel.weatherapp.interfaces.IFragmentCallbacks
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    lateinit var iFragmentCallbacks: IFragmentCallbacks

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

}