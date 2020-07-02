package com.android.hootr.hogwartslibrary.ui.scenes.hat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hootr.hogwartslibrary.domains.repositories.HatRepositories
import com.android.hootr.hogwartslibrary.domains.repositories.HatRepositoriesImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HatViewModel : ViewModel() {

    private lateinit var hatRepositories: HatRepositories

    init {
        hatRepositories = HatRepositoriesImpl()
    }

    private val _userName: MutableLiveData<String> = MutableLiveData<String>().apply { value = "" }

    private val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    private val _facultyName: MutableLiveData<String> =
        MutableLiveData<String>().apply { value = "" }

    val facultyName: LiveData<String> = _facultyName
    val isLoading: LiveData<Boolean> = _isLoading

    fun applayUserName(name: String) {
        _userName.postValue(name)
    }

    fun getFacultyName() {

        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                _facultyName.postValue(hatRepositories.generateFaculty(_userName.value ?: "").name)
                _isLoading.postValue(false)
            }
        }

    }



}