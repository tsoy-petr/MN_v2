package com.android.hootr.mvvmretrofitdemo.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.android.hootr.mvvmretrofitdemo.R
import com.android.hootr.mvvmretrofitdemo.adapter.ResultAdapter
import com.android.hootr.mvvmretrofitdemo.model.Result
import com.android.hootr.mvvmretrofitdemo.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel
    private var results: List<Result> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider.AndroidViewModelFactory(application)
            .create(MainActivityViewModel::class.java)

        getPopularMovies()

        swiperefresh.setColorSchemeResources(R.color.colorPrimary)
        swiperefresh.setOnClickListener {
            getPopularMovies()
        }
    }

    fun getPopularMovies() {
        mainActivityViewModel.getAllMovieData().observe(this, Observer {
            results = it
            fillRecyclerView()
        })
    }

    private fun fillRecyclerView() {

        val adapter = ResultAdapter(this, results)

        recyclerView.layoutManager =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(this, 2)
            } else {
                GridLayoutManager(this, 4)
            }

        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        swiperefresh.isRefreshing = false
    }
}
