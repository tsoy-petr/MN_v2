package com.android.hootr.presentation.screens.countries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.domen.usecases.cities.FetchCities
import com.android.hootr.domen.usecases.countries.FetchCountries
import com.android.hootr.presentation.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.countries_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountriesFragment : Fragment() {

    companion object {
        fun newInstance() = CountriesFragment()
    }

    private lateinit var viewModel: CountriesViewModel

    @Inject
    lateinit var fetchUseCase: FetchCountries
    @Inject lateinit var fetchCities: FetchCities

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.countries_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountriesViewModel::class.java)

        GlobalScope.launch {
            fetchCities.execute(request = null, onSuccess = { text ->
                txtCountriesTest.text = text
            }, onFailure = { errorMessage ->
                txtCountriesTest.text = errorMessage
            })
        }
    }

}