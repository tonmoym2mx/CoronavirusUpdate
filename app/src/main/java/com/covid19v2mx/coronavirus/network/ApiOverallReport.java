package com.covid19v2mx.coronavirus.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.covid19v2mx.coronavirus.model.OverallReport;
import com.google.gson.Gson;

public abstract class ApiOverallReport {
    private Context context;
    private OverallReport overallReport;
    public abstract void onComplete(OverallReport overallReport);
    public abstract void onFailure(String msg);


    public ApiOverallReport(Context context) {
        this.context = context;
    }
    public void startwork(){
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Urls.overallReportUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                overallReport = gson.fromJson(response, OverallReport.class);
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
                if(overallReport !=null){
                    onComplete(overallReport);
                }
            }
        });

    }
}
