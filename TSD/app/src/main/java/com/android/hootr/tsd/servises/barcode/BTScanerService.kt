package com.android.hootr.tsd.servises.barcode

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.android.hootr.tsd.R
import com.android.hootr.tsd.screens.SettingsActivity
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.io.InputStream

class BTScanerService : Service() {

    private val BLOCK = Any()
    private val attempts = 10

    private var btAdress: String? = null

    private val disposable = CompositeDisposable()

    private var reactivBT: ReactivBT? = null
    var ob: ObservableOnSubscribe<DataBT>? = null

    private var btAdapter: BluetoothAdapter? = null
    private var btSocket: BluetoothSocket? = null

    private var connectedThread: ConnectedThread? = null

    override fun onBind(p0: Intent?) = null

    companion object {
        val MY_UUID = java.util.UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        val ATTEMTS = 10
        val CHANNEL_ID = "ForegroundServiceChannelBTScaner"
        val KEY_BTADRESS = "brAdress"
    }

    override fun onCreate() {
        super.onCreate()


        reactivBT = ReactivBT()

        val dispose = Flowable.create(reactivBT, BackpressureStrategy.MISSING)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    sendBroadCast(String(it.buffer))
                }
            )

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        btAdress = intent?.getStringExtra(KEY_BTADRESS)

        if (btAdress == null || btAdress?.isEmpty()!!) {
            stopSelf()
        }

        connectedThread?.fgh = reactivBT

        var btAdapterIsReady = false

        if (connectedThread == null) {

            btAdapter = BluetoothAdapter.getDefaultAdapter()

            btAdapterIsReady = if (btAdapter != null) {
                btAdapter?.isEnabled ?: false
            } else false

            if (!btAdapterIsReady) {
                // Bluetooth выключен. Предложим пользователю включить его.
                Toast.makeText(baseContext, "Bluetooth выключен", Toast.LENGTH_SHORT).show()
                stopSelf()
            }

            val device = btAdapter?.getRemoteDevice(btAdress)

            try {
                btSocket = device?.createRfcommSocketToServiceRecord(MY_UUID)
            } catch (e: IOException) {
                Toast.makeText(baseContext, "Ошибка создания подключения", Toast.LENGTH_SHORT)
                    .show()
                stopSelf()
            }

            val value = Observable.create<Boolean> {

                var isOk = true

                for (i in 0..ATTEMTS - 1) {
                    try {

                        try {
                            btSocket = device?.createRfcommSocketToServiceRecord(MY_UUID)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                        btSocket?.connect()

                        isOk = btSocket?.isConnected ?: false

                        if (isOk) {
                            break
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                it.onNext(isOk)
            }

            disposable.add(
                value.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (it) {
                            startReadBT()
                        } else {
                            stopSelf()
                        }
                        btAdapter?.cancelDiscovery()
                    }
            )

            createNotificationChannel()

            val notificationIntent = Intent(this, SettingsActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Запушена компонента сканирования")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .build()

            startForeground(1, notification)
        }

        return START_NOT_STICKY
    }

    fun startReadBT() {

        connectedThread = btSocket?.let { ConnectedThread(it, fgh = reactivBT) }
        connectedThread?.start()

    }

    fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel For Scaning",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }


    }

    private fun sendBroadCast(bc: String) {

        val sb = StringBuilder()
        bc.forEach {
            if (it.isDigit()) sb.append(it)
        }

//        App.instance?.vmBD?.currentBC?.value = sb.toString()
//        Log.i("happy", "bc: ${sb.toString()}")
//        Intent().also { intent ->
//            intent.setAction("com.hootor.broadcast.BC_EVENT")
//            intent.putExtra("data", sb.toString())
//            sendBroadcast(intent)
        val intent = Intent()
        intent.setAction("com.hootor.broadcast.BC_EVENT")
        intent.putExtra("data", sb.toString())
        sendBroadcast(intent)

    }

    override fun onDestroy() {

        clearRef()
        disposable.dispose()



        super.onDestroy()
    }

    private fun clearRef() {

        connectedThread?.cancel()

        try {
            btSocket?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    class ConnectedThread(private @Volatile var socket: BluetoothSocket, @Volatile var fgh: ReactivBT?) :
        Thread() {

        private var inStream: InputStream? = null

        init {
            try {
                inStream = socket.inputStream
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        override fun run() {

            var buffer = ByteArray(28)
            var bytes: Byte = 0

            while (true) {


                try {

                    synchronized(ConnectedThread::class.java) {
                        if (socket.isConnected) {
                            inStream?.apply {
                                bytes = read(buffer).toByte()
                                fgh?.sendEmitt(DataBT(false, buffer))

                            }
                        }
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                    fgh?.sendEmitt(DataBT(true, buffer))
                    break
                }
            }

        }

        fun cancel() {

            try {
                socket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            try {
                inStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }


        }
    }

}