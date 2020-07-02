package com.android.hootr.hogwartslibrary.ui.scenes.students

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.hootr.hogwartslibrary.domains.repositories.StudentsRepositoriesImpl
import com.android.hootr.hogwartslibrary.ui.scenes.students.adapters.StudentCellModel
import com.android.hootr.hogwartslibrary.ui.scenes.students.adapters.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudientsViewModel : ViewModel() {

    private val studentsRepositories = StudentsRepositoriesImpl()

    private val _students = MutableLiveData<List<StudentCellModel>>().apply {
        value = ArrayList()
    }

    private val _isLoading = MutableLiveData<Boolean>().apply {
        value = false
    }

    val isLoading: LiveData<Boolean> = _isLoading
    val students: LiveData<List<StudentCellModel>> = _students


    fun fetchStudents() {
        viewModelScope.launch {
            _isLoading.value = true
            withContext(Dispatchers.IO) {
                val students = studentsRepositories.fetchStudents()
                _isLoading.postValue(false)
                _students.postValue(
                    students.map {
                        it.toUI()
                    }
                )
            }
        }
    }
}