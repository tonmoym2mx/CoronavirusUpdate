package com.covid19v2mx.coronavirus.ui.global

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.covid19v2mx.coronavirus.R
import com.covid19v2mx.coronavirus.databinding.FragmentGlobalBinding
import com.covid19v2mx.coronavirus.model.OverallReport
import com.covid19v2mx.coronavirus.network.ApiOverallReport
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class GlobalFragment : Fragment() {
    private lateinit var globalViewModel: GlobalViewModel
    private lateinit var binding: FragmentGlobalBinding
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        globalViewModel = ViewModelProvider(activity!!).get(GlobalViewModel::class.java)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_global, container, false)
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()

    }
    fun loadData(){

        binding.progressBar.visibility = View.VISIBLE
        val apiOverallReport: ApiOverallReport = object : ApiOverallReport(context) {
            override fun onComplete(overallReport: OverallReport) {
                loadOverallPie(overallReport)
                binding.overallReport = overallReport
                binding.progressBar.visibility = View.GONE
            }

            override fun onFailure(msg: String) {
                Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();
                binding.progressBar.visibility = View.GONE
            }
        }
        apiOverallReport.startwork()
    }
    @SuppressLint("NewApi")
    private fun loadOverallPie(data:OverallReport){
        var dataset = PieDataSet(listOf(
                PieEntry(data.recovered.toFloat(),"Recovered"),
                PieEntry(data.deaths.toFloat(),"Deaths")
        ),"")


        dataset.valueTextColor = Color.WHITE;
        dataset.valueTextSize = 0f;
        dataset.sliceSpace = 2f;

        var pieData = PieData()
        pieData.dataSet = dataset

        binding.pieChart.setData(pieData)
        binding.pieChart.description.isEnabled = false;

        binding.pieChart.apply {
            legend.isEnabled = false
            getDescription().setEnabled(false);
            centerText = "Total Cases\n${data.cases}"
        }

        if (android.os.Build.VERSION.SDK_INT > 21) {
            context?.getColor(R.color.green)?.let {
                dataset.setColors(it, context?.getColor(R.color.red)!!)
            }
        }else{
            context?.resources?.getColor(R.color.green)?.let {
                dataset.setColors(it, context?.resources?.getColor(R.color.red)!!)
            }
        }


        binding.pieChart.animateXY(1000, 1000)
    }


}