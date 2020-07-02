package com.android.hootor.mn_v2.presentation.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.hootor.mn_v2.presentation.viewModel.OrdersListPresenterModel
import com.android.hootor.mn_v2.ui.synchronization.SyncViewPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(OrdersListPresenterModel::class)
    internal abstract fun ordersListPresentationModel(viewModel: OrdersListPresenterModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SyncViewPresenter::class)
    internal abstract fun syncViewModel(viewModel: SyncViewPresenter) : ViewModel

}