package com.android.hootor.mn_v2.domain.usecases.ordersList.model

import java.math.BigDecimal

data class OrderListModel(
    val uuid: String,
    val date: Long,
    val title: String,
    val status: Status,
    val sum: BigDecimal
)

sealed class Status(open val title: String){
    object InWork : Status("В работе")
    object Completed: Status("Выполнен")
}