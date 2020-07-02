package com.android.hootor.mn_v2.presentation.viewModel

import com.android.hootor.mn_v2.domain.usecases.ordersList.model.OrderListModel
import com.android.hootor.mn_v2.ui.orders.OrdersListItemViewModel
import com.android.hootor.mn_v2.utils.DateUtils
import javax.inject.Inject

class OrdersListViewModelFactory @Inject constructor() {


    fun toViewModel(model: Model): OrdersListViewState {
        val items = model.items.map { item ->
            item.toViewModel()
        }

        return OrdersListViewState(
            fetchStatus = FetchStatus.Success,
            data = items
        )
    }

    private fun OrderListModel.toViewModel(): OrdersListItemViewModel = OrdersListItemViewModel(
        title = "Заказ от ${DateUtils.getDateTimeFromTimeStamp(
            this.date,
            DateUtils.DATE_FORMAT_1
        )}, ${this.sum} р. (${this.status.title})",
        payload = this
    )

}