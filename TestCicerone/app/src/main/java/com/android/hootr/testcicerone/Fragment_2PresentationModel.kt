package com.android.hootr.testcicerone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hootr.testcicerone.rxBus.RxBus
import com.android.hootr.testcicerone.rxBus.RxBusEvent
import io.reactivex.disposables.CompositeDisposable

class Fragment_2PresentationModel() : ViewModel() {

    private val modelLiveData by lazy {
        MutableLiveData<Model>()
    }

    private val compositDisposable by lazy {
        CompositeDisposable()
    }

    private var model: Model

    init {
        compositDisposable.add(RxBus.listen(RxBusEvent.SelectedSeriesEvent::class.java).subscribe { event ->
            update{
                copy(uuidSeries = event.uuidSeries)
            }
        })
        model = Model("0000-0000-000-000")
        update()
    }

    fun getSeriesLiveData(): LiveData<Model> {
        return modelLiveData
    }

    fun update(
        mapper: Model.() -> Model = {this }
    ) {

        model = model.mapper()
        modelLiveData.value = model
    }

    override fun onCleared() {
        compositDisposable.dispose()
        super.onCleared()
    }

    data class Model(
        var uuidSeries: String
    )
}

