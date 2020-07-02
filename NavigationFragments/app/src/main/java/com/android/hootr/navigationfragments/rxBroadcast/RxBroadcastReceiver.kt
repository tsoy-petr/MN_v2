package com.android.hootr.tsd.servises.rxBroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe


class RxBroadcastReceiver(
    private val context: Context,
    private val intentFilter: IntentFilter
) : ObservableOnSubscribe<Intent> {

    override fun subscribe(emitter: ObservableEmitter<Intent>) {

        if (!Preconditions.checkLooperThread(emitter)) return

        val receiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, mIntent: Intent?) {
                mIntent?.let { emitter.onNext(it) }
            }
        }

        if (Looper.myLooper() == Looper.getMainLooper()) {
            context.registerReceiver(receiver, intentFilter)
        } else {
            context.registerReceiver(receiver, intentFilter, null, Handler(Looper.myLooper()))
        }
        emitter.setCancellable {
            context.unregisterReceiver(receiver)
        }

    }

}