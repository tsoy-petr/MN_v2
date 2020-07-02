package com.android.hootr.data_class_samples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootr.data_class_samples.presentation.FeedViewModel
import com.android.hootr.data_class_samples.presentation.FeelPresentationModel
import com.android.hootr.data_class_samples.view.adapter.AdvertAdapter
import com.android.hootr.data_class_samples.view.adapter.DividerAdapter
import com.android.hootr.data_class_samples.view.adapter.OfferAdapter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {


    private lateinit var presentation: FeelPresentationModel
    private val adapter: DiffUtilCompositeAdapter by lazy { getDiffAdapter() }

    private fun getDiffAdapter(): DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(AdvertAdapter(presentation::onHideAdvertClicked))
        .add(OfferAdapter(presentation::onOfferClicked))
        .add(DividerAdapter)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        initPresentationModel()

        bindRecycler()
    }

    private fun bindRecycler() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun update(model: FeedViewModel) {
        adapter.swapData(model.items)
    }

    private fun initPresentationModel() {
        presentation = ViewModelProvider(this)[FeelPresentationModel::class.java]
        presentation.modelLiveData.observe(this, Observer { model: FeedViewModel ->
            adapter.swapData(model.items)
        })

    }
}
