package com.kashyapkpatel.weatherapp.ui

import android.content.Context
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.kashyapkpatel.weatherapp.R
import com.kashyapkpatel.weatherapp.databinding.FragmentLocationsBinding
import com.kashyapkpatel.weathersdk.network.db.Location
import com.kashyapkpatel.weatherapp.interfaces.IFragmentCallbacks
import com.kashyapkpatel.weatherapp.ui.adapter.LocationAdapter
import com.kashyapkpatel.weathersdk.util.Status
import com.kashyapkpatel.weathersdk.util.newFragmentInstance
import com.kashyapkpatel.weatherapp.viewmodel.WeatherViewModel


class LocationsFragment : BaseFragment(), OnMapReadyCallback {

    companion object {
        const val TAG = "LocationsFragment"
        const val LOCATION = "Location"
    }

    private val weatherViewModel by viewModels<WeatherViewModel> { viewModelProviderFactory }

    lateinit var binding : FragmentLocationsBinding

    private lateinit var googleMap: GoogleMap

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IFragmentCallbacks) {
            iFragmentCallbacks = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_locations, container, false
        )
        binding.viewModel = weatherViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iFragmentCallbacks.updateTitle(getString(R.string.app_name))
        loadMap()
        setAdapterThings()
    }

    override fun onResume() {
        super.onResume()
        observeLocations()
    }

    private fun loadMap() {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun setAdapterThings() {
        val locationAdapter = LocationAdapter()
        locationAdapter.locationActionCallback = object : LocationAdapter.LocationActionCallback {
            override fun onDelete(location: Location) {
                observeDeletionOfLocation(location)
            }
            override fun onLocation(location: Location) {
                val weatherInfoFragment = newFragmentInstance<WeatherInfoFragment>(
                    LOCATION to location,
                )
                iFragmentCallbacks.showFragment(weatherInfoFragment, WeatherInfoFragment.TAG)
            }
        }
        binding.adapter = locationAdapter
    }

    /**
     * Observes locations added to DB
     */
    private fun observeLocations() {
        binding.noItems = false
        weatherViewModel
            .getAllLocations()
            .observe(viewLifecycleOwner, Observer { locations ->
                binding.adapter?.submitList(locations)
                binding.noItems = locations?.isEmpty() == true
            })
    }

    /**
     * Observes deletion of location from DB.
     */
    private fun observeDeletionOfLocation(location: Location) {
        weatherViewModel
            .deleteLocation(location)
            .observe(viewLifecycleOwner, Observer { (status, unit, errString) ->
                when (status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {
                        Toast.makeText(
                            requireContext(), getString(
                                R.string.msg_location_deleted,
                                location.cityName
                            ), Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.ERROR -> {

                    }
                }
            })
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.setOnMapClickListener(OnMapClickListener {
            val geocoder = Geocoder(requireContext())
            val list = geocoder.getFromLocation(it.latitude, it.longitude, 1)
            if (list.isEmpty()) {
                return@OnMapClickListener
            }
            val address = list.first()
            val city: String? = address.locality
            val state: String? = address.adminArea
            val country: String? = address.countryName
            val postalCode: String? = address.postalCode
            val lat: Double = it.latitude
            val lon: Double = it.longitude

            addLocation(
                Location(
                    cityName = city,
                    administrativeAreaLevel1 = state,
                    countryName = country,
                    zipCode = postalCode,
                    lat = lat,
                    lon = lon
                )
            )
        })
    }

    private fun addLocation(location: Location) {
        weatherViewModel.insertLocation(location)
            .observe(this, Observer { (status, addedLong, errString) ->
                when (status) {
                    Status.LOADING -> {

                    }
                    Status.SUCCESS -> {

                    }
                    Status.ERROR -> {

                    }
                }
            })
    }

}