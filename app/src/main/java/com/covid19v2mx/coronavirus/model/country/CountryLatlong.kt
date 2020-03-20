package com.covid19v2mx.coronavirus.model.country


import com.google.gson.annotations.SerializedName

data class CountryLatlong(
    @SerializedName("ref_country_codes")
    var refCountryCodes: List<RefCountryCode>?
)