
package com.covid19v2mx.coronavirus.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountrysReport implements Serializable {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("cases")
    @Expose
    private int cases;
    @SerializedName("todayCases")
    @Expose
    private int todayCases;
    @SerializedName("deaths")
    @Expose
    private int deaths;
    @SerializedName("todayDeaths")
    @Expose
    private int todayDeaths;
    @SerializedName("recovered")
    @Expose
    private int recovered;
    @SerializedName("active")
    @Expose
    private int active;
    @SerializedName("critical")
    @Expose
    private int critical;
    private LatLng latLng;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(int todayCases) {
        this.todayCases = todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(int todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    @Override
    public String toString() {
        return "CountrysReport{" +
                "country='" + country + '\'' +
                ", cases=" + cases +
                ", todayCases=" + todayCases +
                ", deaths=" + deaths +
                ", todayDeaths=" + todayDeaths +
                ", recovered=" + recovered +
                ", active=" + active +
                ", critical=" + critical +
                ", latLng=" + latLng +
                '}';
    }
}
