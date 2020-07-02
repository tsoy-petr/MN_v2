package com.android.hootr.client1c.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.client1c.App
import com.android.hootr.client1c.R
import com.android.hootr.client1c.presentation.MainViewPresentation
import com.android.hootr.client1c.presentation.SettingsViewPresentation
import kotlinx.android.synthetic.main.fragment_settings.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SettingsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewPresentation
    lateinit var settingsViewModel: SettingsViewPresentation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        activity?.let {
            mainViewModel = ViewModelProvider(it, viewModelFactory)[MainViewPresentation::class.java]
        }

        settingsViewModel = ViewModelProvider(this, viewModelFactory)[SettingsViewPresentation::class.java]
        settingsViewModel.getSettingLiveData().observe(viewLifecycleOwner, Observer {
            etAdressServer.setText(it.adressServer)
        })

        btnSaveSettings.setOnClickListener {
            settingsViewModel.saveAdressServer(etAdressServer.text.toString().trim())
        }
        
    }

}
