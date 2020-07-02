package com.android.hootr.tsd.servises.barcode

import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe

class ReactivBT: FlowableOnSubscribe<DataBT> {

    private var e: FlowableEmitter<DataBT>? = null

    fun sendEmitt(data: DataBT) {

        e?.onNext(data)
    }

    override fun subscribe(emitter: FlowableEmitter<DataBT>) {

        e = emitter

    }
}