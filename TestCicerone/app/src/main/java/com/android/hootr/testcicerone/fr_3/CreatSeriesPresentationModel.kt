package com.android.hootr.testcicerone.fr_3

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hootr.testcicerone.fr_3.api.ApiFactory
import com.example.delegateadapter.delegate.diff.IComparableItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class CreatSeriesPresentationModel(
    val interactor: SeriesInteractor = SeriesInteractor(),
    val viewModelFactory: SeriesViewModelFactory = SeriesViewModelFactory()
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private var model: Model = Model(listOf())
    private var selectedSeries: Series? = null

    val modelLiveData by lazy {
        MutableLiveData<SeriesPresentationViewModel>()
    }

    init {
        refreshData()
    }

    fun onSeriesClicked(series: Series) {

        selectedSeries = series
        modelLiveData.value = modelLiveData.value?.copy(upDateList = false, showContextMenu = true)

    }

    fun refreshData() {
        model = Model(listOf())
        update(loadData = true, upDateList = false)
        loadData()
    }

    private fun loadData() {
        compositeDisposable.add(
            ApiFactory.apiService.getSeries(UUID.randomUUID().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    model = Model(items = it)
                    update(upDateList = true)
                }, {
                    Log.i("happyn", it.message)
                })
        )


    }

    private fun update(
        mapper: Model.() -> Model = { this }, loadData: Boolean = false,
        upDateList: Boolean
    ) {
        model = model.mapper()
        val viewModel = viewModelFactory.toViewModel(model, loadData, true)
        modelLiveData.value = viewModel
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    data class Model(

        val items: List<Series>
    )
}

data class SeriesPresentationViewModel(
    val isLoad: Boolean = false,
    val upDateList: Boolean = false,
    var showContextMenu: Boolean,
    var items: List<IComparableItem>
)