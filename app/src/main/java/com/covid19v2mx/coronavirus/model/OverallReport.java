
package com.covid19v2mx.coronavirus.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OverallReport implements Serializable {

    @SerializedName("cases")
    @Expose
    private int cases;
    @SerializedName("deaths")
    @Expose
    private int deaths;
    @SerializedName("recovered")
    @Expose
    private int recovered;

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    @Override
    public String toString() {
        return "OverallReport{" +
                "cases=" + cases +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                '}';
    }
}
