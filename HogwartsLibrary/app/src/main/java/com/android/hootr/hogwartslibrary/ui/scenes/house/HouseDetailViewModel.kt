package com.android.hootr.hogwartslibrary.ui.scenes.house

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hootr.hogwartslibrary.R

class HouseDetailViewModel : ViewModel() {

    private val _ghost: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _founder: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _leader: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }
    private val _housesName: MutableLiveData<String> =
        MutableLiveData<String>().apply { value = "" }
    private val _housesImage: MutableLiveData<Int> =
        MutableLiveData<Int>().apply { value = R.drawable.img_gryffindor }

    val ghost: LiveData<String> = _ghost
    val founder: LiveData<String> = _founder
    val leader: LiveData<String> = _leader
    val housesName: LiveData<String> = _housesName
    val housesImage: LiveData<Int> = _housesImage

    fun fetchData(hous: Houses?) {

    }
}
