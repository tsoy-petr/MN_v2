package com.android.hootor.mn_v2.domain.usecases.ordersList

import com.android.hootor.mn_v2.domain.usecases.UseCase
import com.android.hootor.mn_v2.domain.usecases.ordersList.model.OrderListModel
import com.android.hootor.mn_v2.domain.usecases.ordersList.model.Status
import io.reactivex.Single
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.random.Random

class OrdersListInteractor @Inject constructor() : UseCase<Unit, List<OrderListModel>> {
    override fun execute(request: Unit?): Single<List<OrderListModel>> {
        return Single.just((0 until 10).map {
            OrderListModel(
                uuid = "$it",
                date = 1590980607000L,
                title = "Заказ с uuid: $this.title",
                status = Status.Completed,
                sum = BigDecimal(Random.nextInt(5000))
            )
        })
    }
}