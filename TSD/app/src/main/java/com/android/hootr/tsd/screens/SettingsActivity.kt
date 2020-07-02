package com.android.hootr.tsd.screens

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import com.android.hootr.tsd.App
import com.android.hootr.tsd.R
import com.android.hootr.tsd.data.model.BTData
import com.android.hootr.tsd.prefs.Prefs
import com.android.hootr.tsd.screens.adapter.BTAdapter
import com.android.hootr.tsd.servises.barcode.BTScanerService
import com.android.hootr.tsd.servises.rxBroadcast.RxBroadcastReceivers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_settings.*
import javax.inject.Inject

class SettingsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var prefs: Prefs
    val compositeDisposable = CompositeDisposable()

    var listBTdata = mutableListOf<BTData>()
    var currentBC: LiveData<String>? = null

    companion object {

        fun startActivity(context: Context) {
            val starter = Intent(context, SettingsActivity::class.java)
            starter.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(starter)
        }
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        (application as App).applicationComponent.inject(this)
//        val app = application as App
//        app.leakedViews.add(this)

        spinnerBTAdress.onItemSelectedListener = this

//        prefs = (application as App).getPrefs()

        getAdapters()

        listBTdata = getAdapters()

        spinnerBTAdress.adapter = BTAdapter(this, R.layout.spinner_dropdown_item, listBTdata)

        initCurrentBTData()

//        currentBC = App.instance?.vmBD?.currentBC
//        currentBC?.observe(this, Observer {
//            tvCurrentBcInfo.text = it
//        })

        compositeDisposable.add(
            RxBroadcastReceivers.fromIntentFilter(
                applicationContext,
                IntentFilter("com.hootor.broadcast.BC_EVENT")
            ).subscribe {
                tvCurrentBcInfo.text = it.getStringExtra("data")
            })

        settingsStartScaning.setOnClickListener {
            val currentBTAdress: String = prefs?.getBTAdress()?.trim() ?: ""
            var serviceIntent = Intent(this, BTScanerService::class.java)
            serviceIntent.putExtra(BTScanerService.KEY_BTADRESS, currentBTAdress)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
        }

        settingsStopScaning.setOnClickListener {
            val serviceIntent = Intent(this, BTScanerService::class.java)
            stopService(serviceIntent)
        }

    }

    private fun initCurrentBTData() {

        listBTdata.sortByDescending { it.current }

    }

    private fun getAdapters(): MutableList<BTData> {

        val currentBTAdress: String = prefs?.getBTAdress()?.trim() ?: ""

        Log.i("happy", "currentBTAdress: $currentBTAdress")

        val btAdapter = BluetoothAdapter.getDefaultAdapter()
        val list = mutableListOf<BTData>()

        if (btAdapter == null) {
            Toast.makeText(this, "Отсутствует поддержка работы с bluetooth", Toast.LENGTH_SHORT)
                .show()
        }

        if (btAdapter.isEnabled) { // Bluetooth включен. Работаем.
        } else { // Bluetooth выключен. Предложим пользователю включить его.
            Toast.makeText(baseContext, "Bluetooth выключен", Toast.LENGTH_SHORT).show()
        }

        // Get a set of currently paired devices
        var pairedDevices = btAdapter.bondedDevices

        if (pairedDevices.size > 0) {
            for (device in pairedDevices) {
                val entri = BTData(
                    device.name, device.address,
                    device.address.equals(currentBTAdress)
                )
                list.add(entri)
            }
        } else {
            val noDevices = "No devices found"
            val entri = BTData(noDevices, noDevices, false)
            list.add(entri)
        }

        return list

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

        Log.i("happy", "p0: $p0")

    }

    override fun onItemSelected(adapter: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        var btData = listBTdata[p2]

        if (btData != null) {
            prefs?.setBTAdress(btData.adress)
        }

    }

    override fun onDestroy() {

        compositeDisposable.dispose()

        super.onDestroy()
    }

}
