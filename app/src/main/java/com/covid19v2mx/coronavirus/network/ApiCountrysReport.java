package com.covid19v2mx.coronavirus.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.covid19v2mx.coronavirus.model.CountrysReport;
import com.covid19v2mx.coronavirus.model.OverallReport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ApiCountrysReport {
    private Context context;
    private List<CountrysReport> countrysReportList;
    public abstract void onComplete(List<CountrysReport> countrysReportList);
    public abstract void onFailure(String msg);


    public ApiCountrysReport(Context context) {
        this.context = context;
    }
    public void startwork(){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.countriesReportUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
               countrysReportList = Arrays.asList(gson.fromJson(response, CountrysReport[].class));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onFailure(error.getLocalizedMessage());
            }
        });
        queue.add(stringRequest);
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener() {
            @Override
            public void onRequestFinished(Request request) {
                if(countrysReportList !=null && countrysReportList.size()>0){
                    onComplete(countrysReportList);
                }
            }
        });

    }
}
