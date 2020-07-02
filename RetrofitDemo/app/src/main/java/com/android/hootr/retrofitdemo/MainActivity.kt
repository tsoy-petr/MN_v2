package com.android.hootr.retrofitdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootr.retrofitdemo.adapters.CountryAdapter
import com.android.hootr.retrofitdemo.model.CountryInfo
import com.android.hootr.retrofitdemo.model.Result
import com.android.hootr.retrofitdemo.service.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var resultArrayLiist: ArrayList<Result> = arrayListOf<Result>()
    val countryApapter = CountryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.adapter = countryApapter

        getCountries()
    }

    private fun getCountries() {

        val countryService = RetrofitInstance.getService()
        val call = countryService.getResults()

        call.enqueue(object : Callback<CountryInfo> {
            override fun onResponse(call: Call<CountryInfo>, response: Response<CountryInfo>) {

                val countryInfo = response.body()
                countryInfo?.let { it ->
                    resultArrayLiist = it.restResponse.result
                    countryApapter.resultArrayList = resultArrayLiist
                }

            }

            override fun onFailure(call: Call<CountryInfo>, t: Throwable) {
                Log.i("happy", t.toString())

            }
        })

    }
}
