package com.covid19v2mx.coronavirus.ui.global

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.covid19v2mx.coronavirus.MainActivity
import com.covid19v2mx.coronavirus.model.OverallReport
import com.covid19v2mx.coronavirus.network.ApiOverallReport
import java.security.AccessControlContext

class GlobalViewModel : ViewModel() {
    companion object{
        private var TAG = "GlobalViewModel"
    }
    val overallReportLiveData: MutableLiveData<OverallReport> by lazy {
        MutableLiveData<OverallReport>()
    }
    fun loadData(context: Context){

        val apiOverallReport: ApiOverallReport = object : ApiOverallReport(context) {
            override fun onComplete(overallReport: OverallReport) {
                overallReportLiveData.postValue(overallReport)
            }

            override fun onFailure(msg: String) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        }
        apiOverallReport.startwork()
    }
}