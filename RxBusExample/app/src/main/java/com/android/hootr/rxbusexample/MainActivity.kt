package com.android.hootr.rxbusexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val INTERVAL: Long = 1000
        const val TIMER_TIME: Long = 1 * 60 * 1000
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val counter = CountDownTimer(TIMER_TIME, INTERVAL)
        // set click event on start button
        buttonStart.setOnClickListener { counter.start() }
        // set click event on stop button
        buttonStop.setOnClickListener {
            counter.cancel()
            textViewCounter.text = "Stop"
        }
        // Listen for MessageEvents only
        RxBus.listen(RxBusEvent.MessageEvent::class.java)
            .subscribe { textViewCounter.text = it.message }
        // Listen for ProgressEvent only
        RxBus.listen(RxBusEvent.ProgressEvent::class.java)
            .subscribe {
                textViewMessage.text = it.message
                progressBar.visibility = if (it.showDialog) VISIBLE else GONE
            }
        // Listen for LogOut only
        RxBus.listen(RxBusEvent.LogOut::class.java)
            .subscribe {
                Toast.makeText(this, "Logout Done !", Toast.LENGTH_LONG).show()
            }




    }
}
