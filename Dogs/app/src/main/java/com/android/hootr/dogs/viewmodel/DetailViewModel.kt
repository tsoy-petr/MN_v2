package com.android.hootr.dogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.android.hootr.dogs.model.DogBreed
import com.android.hootr.dogs.model.DogDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): BaseViewModel(application) {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int) {
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDos(uuid)
            dogLiveData.value = dog
        }

    }
}