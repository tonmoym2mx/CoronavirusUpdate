package com.covid19v2mx.coronavirus.ui.map

import android.content.Context
import android.util.Log
import com.covid19v2mx.coronavirus.model.CountrysReport
import com.covid19v2mx.coronavirus.model.country.CountryLatlong
import com.covid19v2mx.coronavirus.model.country.RefCountryCode
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList

class CoronaMapUtlis(val context: Context) {
    companion object{
        private val TAG_ = "CoronaMapUtlis"
    }
    fun countrysString():String?{
        return context.assets?.open("countrylatlong.json")?.bufferedReader().use { it?.readText() }
    }

    fun findLatlong(countryList:List<CountrysReport>): List<CountrysReport> {
        val objs = Gson().fromJson(countrysString(),CountryLatlong::class.java)
        countryList.forEach { countrysReport: CountrysReport ->
            objs.refCountryCodes?.forEach { refCountryCode: RefCountryCode ->

                val cStr = "${refCountryCode.country}${refCountryCode.alpha2}${refCountryCode.alpha2}${refCountryCode.alpha3}"
                        .toUpperCase(Locale.ROOT).toString()
                val rStr = countrysReport.country.toString().toUpperCase(Locale.ROOT).toString()
                if(cStr.contains(rStr) || rStr.contains(cStr)){
                    countrysReport.latLng = LatLng(refCountryCode.latitude!!,refCountryCode.longitude!!)
                }
            }
        }
        val notNullcountry = countryList.filter { countrysReport ->
            countrysReport.latLng != null
        }

        return notNullcountry



    }


}
