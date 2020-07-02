package com.android.hootor.mn_v2.presentation.injection

import com.android.hootor.mn_v2.domain.usecases.ordersList.model.OrderListModel
import com.android.hootor.mn_v2.ui.orders.OrdersListFragment
import com.android.hootor.mn_v2.ui.synchronization.SynchronizationFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, SyncModule::class])
interface AppComponent {

    //fragments
    fun inject(fragment: OrdersListFragment)
    fun inject(fragment: SynchronizationFragment)

}