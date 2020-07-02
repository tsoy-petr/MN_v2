package com.android.hootr.tsd.servises.rxBroadcast

import android.os.Looper
import io.reactivex.Emitter

class Preconditions {

    companion object {

        fun checkLooperThread(emitter: Emitter<*>): Boolean {
            return if (Looper.myLooper() == null) {
                emitter.onError(IllegalStateException("Calling thread is not associated with Looper"))
                false
            } else {
                true
            }
        }
    }
}