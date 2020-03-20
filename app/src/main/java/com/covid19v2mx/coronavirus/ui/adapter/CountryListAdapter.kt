package com.covid19v2mx.coronavirus.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.covid19v2mx.coronavirus.R
import com.covid19v2mx.coronavirus.databinding.RowCountryLsitBinding
import com.covid19v2mx.coronavirus.model.CountrysReport

class CountryListAdapter(val context:Context, val list:List<CountrysReport>) : RecyclerView.Adapter<CountryListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:RowCountryLsitBinding = DataBindingUtil.inflate(inflater, R.layout.row_country_lsit,parent,false)
        return CountryListHolder(binding)

    }



    override fun onBindViewHolder(holder: CountryListHolder, position: Int) {
        val item = list.get(position)
        holder.binding.report = item
    }
    override fun getItemCount(): Int {

        return  list.size
    }
}
class CountryListHolder(val binding: RowCountryLsitBinding) : RecyclerView.ViewHolder(binding.root) {

}