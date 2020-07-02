package com.android.hootr.client1c.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hootr.client1c.prefs.SharedPrefsManager
import javax.inject.Inject

class SettingsViewPresentation @Inject constructor(val prefs: SharedPrefsManager) : ViewModel() {

    private val liveData: MutableLiveData<SettingViewModel> = MutableLiveData<SettingViewModel>()

    init {
        liveData.value = SettingViewModel(
            prefs.getURLServer()?:""
        )
    }

    fun getSettingLiveData(): LiveData<SettingViewModel> {
        return liveData
    }

    fun saveAdressServer(adressServer: String) {
        prefs.setURLServer(adressServer)
    }

}

data class SettingViewModel(
    val adressServer: String
)