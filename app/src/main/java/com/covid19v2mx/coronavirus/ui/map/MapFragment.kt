package com.covid19v2mx.coronavirus.ui.map

import android.content.Context
import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.covid19v2mx.coronavirus.R
import com.covid19v2mx.coronavirus.databinding.FragmentMapBinding
import com.covid19v2mx.coronavirus.model.CountrysReport
import com.covid19v2mx.coronavirus.network.ApiCountrysReport
import com.covid19v2mx.coronavirus.ui.view.InfoAdapterCustom
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson


class MapFragment : Fragment(),OnMapReadyCallback {
    companion object{
        private val TAG ="MapFragment"
    }
    private lateinit var dashboardViewModel: MapViewModel
    private lateinit var binding:FragmentMapBinding
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dashboardViewModel = ViewModelProvider(activity!!).get(MapViewModel::class.java)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_map, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.onResume()

        try {
            MapsInitializer.initialize(getActivity());
        } catch ( e: GooglePlayServicesNotAvailableException) {

            Toast.makeText(activity, e.localizedMessage, Toast.LENGTH_SHORT).show();
        }
        binding.mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        try {
            val success: Boolean = googleMap!!.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(activity,R.raw.mapstyle) )
            if (!success) {
                Log.e(TAG, "Style parsing failed.")
            }
        } catch (e: NotFoundException) {
            Log.e(TAG, "Can't find style. Error: ", e)
        }

        laodData(googleMap)

    }
    fun  laodData(googleMap: GoogleMap?) {
        val apiCountrysReport: ApiCountrysReport = object : ApiCountrysReport(activity) {
            override fun onComplete(countrysReportList: List<CountrysReport>) {


              val list =   activity?.let { CoronaMapUtlis(it).findLatlong(countrysReportList) }
                if(list !=null){
                    for(cr in list){
                        val marker = MarkerOptions().title(cr.country)
                                .snippet(Gson().toJson(cr))
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.circle_image))
                                .position(cr.latLng)


                        googleMap?.addMarker(marker)


                    }
                }
                try {
                    googleMap?.setInfoWindowAdapter(InfoAdapterCustom(layoutInflater))
                }catch (e:IllegalStateException){}

            }

            override fun onFailure(msg: String) {
                Log.i(TAG, "onFailure: $msg")
            }
        }
        apiCountrysReport.startwork()
    }
}