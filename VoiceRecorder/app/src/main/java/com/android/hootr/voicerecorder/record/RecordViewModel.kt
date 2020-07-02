package com.android.hootr.voicerecorder.record

import android.app.Application
import android.content.Context
import android.os.CountDownTimer
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class RecordViewModel(private val app: Application) : AndroidViewModel(app) {

    private val TRIGGER_TIME = "TRIGGER_AT"
    private val second: Long = 1_000L

    private val prefs =
        app.getSharedPreferences("com.android.hootr.voicerecorder.", Context.MODE_PRIVATE)

    private val _elapsedTime = MutableLiveData<String>()

    val elapsedTime: LiveData<String>
        get() = _elapsedTime

    private lateinit var timer: CountDownTimer

    init {
        createTimer()
    }
    fun timFormatter(time: Long): String {
        return String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(time) % 60,
            TimeUnit.MILLISECONDS.toMinutes(time) % 60,
            TimeUnit.MILLISECONDS.toSeconds(time) % 60
        )
    }

    fun stopTimer() {
        timer.cancel()
        resetTime()
    }

    fun startTimer() {
        val trigger = SystemClock.elapsedRealtime()
        viewModelScope.launch {
            saveTime(trigger)
            createTimer()
        }
    }

    private fun createTimer() {
        viewModelScope.launch {
            val triggerTime = loadTime()
            timer = object : CountDownTimer(triggerTime, second) {
                override fun onTick(p0: Long) {
                    _elapsedTime.value = timFormatter(SystemClock.elapsedRealtime() - triggerTime)
                }

                override fun onFinish() {
                    resetTime()
                }
            }
            timer.start()
        }


    }

    fun resetTime() {
        _elapsedTime.value = timFormatter(0)
        viewModelScope.launch {
            saveTime(0)
        }
    }

    private suspend fun saveTime(triggerTime: Long) {
        withContext(Dispatchers.IO) {
            prefs.edit().putLong(TRIGGER_TIME, triggerTime)
        }
    }

    private suspend fun loadTime(): Long =
        withContext(Dispatchers.IO) {
            prefs.getLong(TRIGGER_TIME, 0)
        }
}