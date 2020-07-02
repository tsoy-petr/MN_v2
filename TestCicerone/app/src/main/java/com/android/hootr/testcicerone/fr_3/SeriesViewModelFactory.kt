package com.android.hootr.testcicerone.fr_3

import com.android.hootr.testcicerone.fr_3.adapter.SeriesViewModel

class SeriesViewModelFactory {

    fun toViewModel(model: CreatSeriesPresentationModel.Model, loadData: Boolean, upDateList: Boolean): SeriesPresentationViewModel {
        val items = model.items.map { item ->
            item.toViewModel()
        }

        return SeriesPresentationViewModel(isLoad = loadData, upDateList = upDateList, items = items)
    }

   private fun Series.toViewModel(): SeriesViewModel = SeriesViewModel(
       title = name,
       payLoad = this
   )
}