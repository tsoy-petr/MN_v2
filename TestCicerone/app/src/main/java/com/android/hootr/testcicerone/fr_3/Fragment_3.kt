package com.android.hootr.testcicerone.fr_3

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.hootr.testcicerone.HandleOnce
import com.android.hootr.testcicerone.MainActivityEvents
import com.android.hootr.testcicerone.MainViewModel
import com.android.hootr.testcicerone.R
import com.android.hootr.testcicerone.fr_3.adapter.SeriesAdapter
import com.android.hootr.testcicerone.rxBus.RxBus
import com.android.hootr.testcicerone.rxBus.RxBusEvent
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import kotlinx.android.synthetic.main.fragment_3.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class Fragment_3 : Fragment(R.layout.fragment_3),
    DialogCreatSeries.EditSeriesDialogListener {

    lateinit var mainViewModel: MainViewModel
    private lateinit var presentation: CreatSeriesPresentationModel
    private val adapter: DiffUtilCompositeAdapter by lazy { getDiffAdapter() }

    private fun getDiffAdapter(): DiffUtilCompositeAdapter = DiffUtilCompositeAdapter.Builder()
        .add(SeriesAdapter(presentation::onSeriesClicked))
        .build()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresentationModel()

        bindRecycler()
    }

    private fun bindRecycler() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(requireContext())

        swipeContainer.setOnRefreshListener {
            presentation.refreshData()
        }
    }

    private fun update(model: SeriesPresentationViewModel) {
        val load = if (model.isLoad) {
            View.VISIBLE
        } else View.GONE
        swipeContainer.isRefreshing = model.isLoad
        if (model.upDateList) {
            adapter.swapData(model.items)
        }

        model.showContextMenu.let { show ->
            if (show) {

            }
        }
    }

    private fun initPresentationModel() {
        presentation = ViewModelProvider(this)[CreatSeriesPresentationModel::class.java]
        presentation.modelLiveData.observe(this, androidx.lifecycle.Observer { model ->
            update(model)
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            mainViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
        }

        btnToSeconFragment.setOnClickListener {
            mainViewModel.clickShowFragment(
                HandleOnce(
                    MainActivityEvents.BackFragment_2()
                )
            )
            RxBus.publish(RxBusEvent.SelectedSeriesEvent(UUID.randomUUID().toString()))
        }

        btnToSeconFirstFragment.setOnClickListener {
            mainViewModel.clickShowFragment(
                HandleOnce(
                    MainActivityEvents.BackFragment_1()
                )
            )
        }

        btnCreateNewSeries.setOnClickListener {

            fragmentManager?.let { fm ->
                val dialogFragment =
                    DialogCreatSeries.newInstance(
                        "Что-то отпрал из Fragment_3"
                    )
                dialogFragment.setTargetFragment(this, 112)
                val ft = fm.beginTransaction()
                val prev = fm.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                dialogFragment.show(ft, "dialog")
            }


        }

    }

    override fun onFinishEditDialog(data: DialogCreatSeries.NewSeries) {

        Log.i("happy", data.toString())

    }

}
