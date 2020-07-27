package com.android.hootor.quiz1c.sync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SyncViewModelFactory(
    private val interactor: SyncInteractor
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SyncPresentationModel::class.java)) {
            return SyncPresentationModel(interactor) as T
        }
        throw IllegalArgumentException("Viewmodel class not found")
    }
}