package com.covid19v2mx.coronavirus.ui.notifications

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.covid19v2mx.coronavirus.R
import com.covid19v2mx.coronavirus.databinding.CountrysFragmentBinding
import com.covid19v2mx.coronavirus.model.CountrysReport
import com.covid19v2mx.coronavirus.network.ApiCountrysReport
import com.covid19v2mx.coronavirus.ui.adapter.CountryListAdapter
import com.covid19v2mx.coronavirus.ui.map.CoronaMapUtlis
import com.covid19v2mx.coronavirus.ui.map.MapFragment
import com.covid19v2mx.coronavirus.ui.view.InfoAdapterCustom
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class CountryFragment : Fragment() {
    companion object{
        private val TAG = "CountryFragment"
    }
    private lateinit var binding: CountrysFragmentBinding
    private lateinit var adapter:CountryListAdapter
    private var list:MutableList<CountrysReport> = ArrayList()
    private var storeList:MutableList<CountrysReport> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.countrys_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        loadData()
        search()
    }

    private fun search() {

        binding.searchView.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
              val newlsit =  storeList.filter { countrysReport ->
                  countrysReport.country.toUpperCase(Locale.ROOT).toString().contains(s.toString().toUpperCase(Locale.ROOT))
              }
                list.clear()
                list.addAll(newlsit)
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun loadData() {
        val apiCountrysReport: ApiCountrysReport = object : ApiCountrysReport(activity) {
            override fun onComplete(countrysReportList: List<CountrysReport>) {
                list.clear()
                list.addAll(countrysReportList)
                storeList.clear()
                storeList.addAll(countrysReportList)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(msg: String) {
                Toast.makeText(context, "Try Again", Toast.LENGTH_SHORT).show();
            }
        }
        apiCountrysReport.startwork()
    }

    private fun initAdapter() {
        adapter = activity?.let { CountryListAdapter(it,list) }!!
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager  = LinearLayoutManager(activity)
    }

}