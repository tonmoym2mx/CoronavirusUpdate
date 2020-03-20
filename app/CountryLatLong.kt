
import com.google.gson.annotations.SerializedName

data class CountryLatLong(
    @SerializedName("ref_country_codes")
    var refCountryCodes: List<RefCountryCode>?
)