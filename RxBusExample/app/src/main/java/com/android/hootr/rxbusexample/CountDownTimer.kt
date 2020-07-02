package com.android.hootr.rxbusexample

import android.os.CountDownTimer
import android.os.Handler
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * @timer is timer value in milliseconds (suppose 5 min = 5*60*1000)
 * @interval is a time interval of one count ideally it should be 1000
 */
class CountDownTimer(timer: Long, interval: Long) :
    CountDownTimer(timer, interval) {
    companion object {
        private const val SECONDS = 60
    }

    override fun onTick(millisUntilFinished: Long) {
        val textToShow = String.format(
            Locale.getDefault(), "%02d min: %02d sec",
            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % SECONDS,
            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % SECONDS
        )
        // Publish MessageEvents
        RxBus.publish(RxBusEvent.MessageEvent(textToShow))
    }

    override fun onFinish() {
        // Publish ProgressEvent for showing progressbar
        RxBus.publish(RxBusEvent.ProgressEvent(true, "Logging out ..."))
        Handler().postDelayed({
            // Publish ProgressEvent for dismissing progressbar
            RxBus.publish(RxBusEvent.ProgressEvent(false, "Log out Successfully"))
            RxBus.publish(RxBusEvent.LogOut(true))
        }, 3000)
    }
}