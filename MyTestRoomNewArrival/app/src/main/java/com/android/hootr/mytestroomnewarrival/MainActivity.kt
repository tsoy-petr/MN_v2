package com.android.hootr.mytestroomnewarrival

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(TestViewModel::class.java)

        viewModel.listNewArrival.observe(this, Observer {
//            Log.i("happy", it.toString())
        })

        viewModel.listFullArrival.observe(this, Observer {
            Log.i("happy", it.toString())
        })

        btnTest.setOnClickListener {
            viewModel.testInsertData()
        }

    }
}
