package com.android.hootr.retrofitdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.hootr.retrofitdemo.R
import com.android.hootr.retrofitdemo.model.Result
import kotlinx.android.synthetic.main.country_item.view.*

class CountryAdapter() :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    var resultArrayList: ArrayList<Result> = arrayListOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var countryNameTextView: TextView? = null

        init {
            countryNameTextView = itemView.countryNameTextView
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount() = resultArrayList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryNameTextView?.text = resultArrayList.get(position).name
    }

}