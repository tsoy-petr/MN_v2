package com.android.hootor.quiz1c.sync

import android.util.Log
import com.android.hootor.quiz1c.base.BaseViewModel
import com.android.hootor.quiz1c.domain.QuizItem
import com.android.hootor.quiz1c.remote.core.Failure
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class SyncPresentationModel @Inject constructor(
    val interactor: SyncInteractor
) : BaseViewModel<SyncViewState, SyncAction, SyncEvent>(){

    init {
        viewState = SyncViewState.Empty
//        startSync()

    }

    private val parentJob = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var foregroundContext: CoroutineContext = Dispatchers.Main

    override fun obtainEvent(viewEvent: SyncEvent) {

        when (viewEvent) {

            is SyncEvent.StartSync -> {
                startSync()
            }
        }

    }

    private fun startSync() {
        parentJob?.apply {
            cancelChildren()
            cancel()
        }

        viewModelScope.launch() {

            Log.i("happy", "viewModelScope.launch")

            val items = withContext(backgroundContext) {
                interactor.getConfigurations()
            }

            items.either(::handleFailure, ::handleData)
        }
    }

    private fun handleFailure(failure: Failure) {
        viewState = SyncViewState.Error(message = failure.toString())
    }

    private fun handleData(items: List<QuizItem.Quiz>) {
        viewState = SyncViewState.Success(items)
    }

}

sealed class SyncViewState() {
    object Empty : SyncViewState()
    object Loading : SyncViewState()
    data class Success(val items: List<QuizItem.Quiz>) : SyncViewState()
    data class Error(val message: String) : SyncViewState()
}

sealed class SyncAction() {
    data class ShowSnackbar(val message: String) : SyncAction()
}

sealed class SyncEvent() {
    object StartSync : SyncEvent()
}