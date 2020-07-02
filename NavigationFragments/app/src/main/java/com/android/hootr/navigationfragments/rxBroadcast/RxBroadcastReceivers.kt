package com.android.hootr.tsd.servises.rxBroadcast

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import io.reactivex.Observable

class RxBroadcastReceivers {

    companion object {

        fun fromIntentFilter(context: Context, intentFilter: IntentFilter): Observable<Intent> {
            return Observable.create(RxBroadcastReceiver(context, intentFilter))
        }
    }
}