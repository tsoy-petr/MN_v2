package com.android.hootor.mn_v2.presentation.viewModel

import android.annotation.SuppressLint
import com.android.hootor.mn_v2.domain.usecases.ordersList.OrdersListInteractor
import com.android.hootor.mn_v2.domain.usecases.ordersList.model.OrderListModel
import com.android.hootor.mn_v2.ui.common.BaseViewModel
import com.example.delegateadapter.delegate.diff.IComparableItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrdersListPresenterModel  @Inject constructor(
    val interactor: OrdersListInteractor,
    val factory: OrdersListViewModelFactory
) : BaseViewModel<OrdersListViewState, OrdersListAction, OrdersListEvent>() {

    private val compositeDisposable = CompositeDisposable()
    private var model: Model

    init {
        model = Model(items = emptyList())
        viewState = OrdersListViewState(FetchStatus.Loading, data = emptyList())
        getData()
    }

    private fun getData() {
        viewState = viewState.copy(fetchStatus = FetchStatus.Loading)

        interactor.execute(Unit)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    model = Model(items = it)
                    update()
                },
                {
                    viewState.copy(
                        fetchStatus = FetchStatus.ShowError(it.toString()),
                        data = emptyList()
                    )
                })
    }

    private fun update(mapper: Model.() -> Model = { this }) {
        model = model.mapper()
        viewState = factory.toViewModel(model)
    }

    override fun obtainEvent(viewEvent: OrdersListEvent) =
        when (viewEvent) {
            is OrdersListEvent.ShowData -> {getData()
            }
            is OrdersListEvent.SelectedItemOrder -> {
            }
            is OrdersListEvent.FabClicked -> {
            }
        }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}

data class Model(
    val items: List<OrderListModel>
)

data class OrdersListViewState(
    val fetchStatus: FetchStatus,
    val data: List<IComparableItem>
)

sealed class FetchStatus {
    object Success : FetchStatus()
    data class ShowError(val message: String) : FetchStatus()
    object Loading : FetchStatus()
}

sealed class OrdersListAction {
    data class ShowSnackbar(val message: String) : OrdersListAction()
}

sealed class OrdersListEvent {
    object ShowData : OrdersListEvent()
    data class SelectedItemOrder(val uuid: String) : OrdersListEvent()
    object FabClicked : OrdersListEvent()
}