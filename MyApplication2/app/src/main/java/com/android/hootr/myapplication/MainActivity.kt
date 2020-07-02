package com.android.hootr.myapplication

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var currentAdressBT: String
    lateinit var listBTAdapters: MutableList<Pair<String, String>>
    var currentBTDevice: Pair<String, String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            val nameBTDevice = it.getString("nameBTDevice", "")
            val addressDevice = it.getString("addressDevice", "")

            if(!nameBTDevice.isEmpty() &&
                    !addressDevice.isEmpty()) {
                currentBTDevice = Pair(nameBTDevice, addressDevice)
            }
        }

        initView()

        btnStart.setOnClickListener {
            startService()
        }

        btnStop.setOnClickListener {
            stopService()
        }

        listBTAdapters = getArrayBTAdapters()
        val list = mutableListOf<String>()
        listBTAdapters.forEach { t: Pair<String, String>? -> t?.first?.let { list.add(it) } }

        val adapter =
            CustomAdapter(this, android.R.layout.simple_spinner_item, list.toTypedArray())

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentAdressBT = listBTAdapters?.get(position)?.second
                currentBTDevice = Pair(listBTAdapters?.get(position)?.first, listBTAdapters?.get(position)?.second)
                myTextView.text = currentBTDevice?.first
            }
        }
    }

    fun initView() = currentBTDevice?.let { myTextView.text = it.first }

    fun startService() {
        var serviceIntent = Intent(this, BTScanerService::class.java)
        serviceIntent.putExtra(Consatans.KEY_BTADRESS, currentAdressBT)
        serviceIntent.putExtra(Consatans.KEY_BASENAME, "1C")
        serviceIntent.putExtra(Consatans.KEY_URI_1C, "jdhfbvjdfhv")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent)
        } else {
            startService(serviceIntent)
        }
    }

    private fun stopService() {
        val serviceIntent = Intent(this, BTScanerService::class.java)
        stopService(serviceIntent)
    }

    private fun getArrayBTAdapters(): MutableList<Pair<String, String>> {

        val btAdapter = BluetoothAdapter.getDefaultAdapter()
        val list = mutableListOf<Pair<String, String>>()

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

        // If there are paired devices, add each one to the ArrayAdapter
        if (pairedDevices.size > 0) {
            for (device in pairedDevices) {
//                TextOfDevice.append(device.name + "///" + device.address + "\n")
//                list.add(device.address)
                val entri = Pair(device.name, device.address)
                list.add(entri)
            }
        } else {
            val noDevices = "No devices found"
            val entri = Pair(noDevices, noDevices)
            list.add(entri)
        }

        return list

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {

        super.onSaveInstanceState(outState, outPersistentState)

        currentBTDevice?.let {
            outState?.putString("nameBTDevice", it.first)
            outState?.putString("addressDevice", it.second)
        }
    }


}
