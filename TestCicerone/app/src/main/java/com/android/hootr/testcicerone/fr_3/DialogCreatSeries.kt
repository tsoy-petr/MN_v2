package com.android.hootr.testcicerone.fr_3

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.android.hootr.testcicerone.R
import com.android.hootr.testcicerone.hideKeyboard
import com.jakewharton.rxbinding3.view.touches
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.dialogfragment_create_series.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class DialogCreatSeries() : DialogFragment() {

    val compositeDisposable = CompositeDisposable()
    var dateSeries: Long = 0
    var nameSeries = ""
    var countries: Countries? = null

    val myCalendar = Calendar.getInstance()
    val date = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(
            p0: DatePicker?,
            year: Int,
            monthOfYear: Int,
            dayOfMonth: Int
        ) {

            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            dateSeries = myCalendar.timeInMillis

            val myFormat = "dd MMMM yyyy" //In which you need put here

            val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
            password.setText(sdf.format(myCalendar.getTime()))
        }

    }

    companion object {
        fun newInstance(seriesName: String = "<не указано>"): DialogCreatSeries {
            val args = Bundle()
            args.putString("seriesName", seriesName)
            val frag = DialogCreatSeries()
            frag.arguments = args
            return frag
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        savedInstanceState?.let {
            dateSeries = savedInstanceState.getLong("dateSeries", 0)
            nameSeries = savedInstanceState.getString("nameSeries", "")
            countries = savedInstanceState.getParcelable("countries")
        }

        return inflater.inflate(R.layout.dialogfragment_create_series, container, false);

    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.ThemeOverlay_Material_Dialog_Alert)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("dateSeries", dateSeries)
        outState.putString("nameSeries", nameSeries)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (countries != null) {

        } else {
            val con: MutableList<Countre> = mutableListOf()
            con.add(Countre("Россия", UUID.randomUUID().toString()))
            con.add(Countre("Китай", UUID.randomUUID().toString()))
            countries = Countries(
                con
            )
        }

        initSpinner()

        btnCreateSeries.setOnClickListener {

            targetFragment?.let { tf ->
                if (tf is EditSeriesDialogListener) {
                    (tf as EditSeriesDialogListener).onFinishEditDialog(
                        NewSeries(
                            username.text.toString().trim(),
                            dateSeries, UUID.randomUUID().toString()
                        )
                    )
                }
                dismiss()
            }
        }

        username.setText(nameSeries)


        compositeDisposable.add(
            password.touches().throttleWithTimeout (100, TimeUnit.MILLISECONDS
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                activity?.let {

                    hideKeyboard()

                    if (dateSeries > 0) {
                        myCalendar.timeInMillis = dateSeries
                    }

                    DatePickerDialog(
                        it, date, myCalendar[Calendar.YEAR], myCalendar[Calendar.MONTH],
                        myCalendar[Calendar.DAY_OF_MONTH]
                    ).show()
                }
            })

    }

    override fun onPause() {
        super.onPause()
            compositeDisposable.clear()
    }

    private fun initSpinner() {

        if (countries != null) {
            val adapter = ArrayAdapter<Countre>(
                this.requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                countries!!.countries
            )

            spinnewCountries.adapter = adapter
        }
    }

    interface EditSeriesDialogListener {
        fun onFinishEditDialog(data: NewSeries)
    }

    data class NewSeries(
        val name: String,
        val date: Long,
        val uuidCountry: String
    )

    @Parcelize
    data class Countre(val name: String, val uuidCountry: String) : Parcelable {
        override fun toString(): String {
            return name
        }
    }

    @Parcelize
    data class Countries(val countries: List<Countre>) : Parcelable
}
