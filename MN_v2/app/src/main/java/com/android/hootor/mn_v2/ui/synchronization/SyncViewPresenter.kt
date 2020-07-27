package com.android.hootor.mn_v2.ui.synchronization

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.android.hootor.mn_v2.ui.common.BaseViewModel
import com.android.hootor.mn_v2.ui.synchronization.SyncFetchStatus.Loading
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class SyncViewPresenter @Inject constructor(
    private val syncRepository: SyncRepository
) : BaseViewModel<SyncFetchStatus, SyncAction, SyncEvent>() {

    private val compositeDisposable = CompositeDisposable()

    init {
        viewState = SyncFetchStatus.Empty
    }

    override fun obtainEvent(viewEvent: SyncEvent) {

        when (viewEvent) {
            is SyncEvent.GetData -> {
                if (viewState !is Loading)
                    fetchData()
            }
        }

    }

    @SuppressLint("CheckResult")
    private fun fetchData() {

       viewModelScope.launch {

           compositeDisposable.add(syncRepository.executeSyncSus().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe {
                   update(it)
               })

       }

//        compositeDisposable.add(syncRepository.executeSync().subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                update(it)
//            })
    }

    private fun update(messageSync: MessageSync) {
        when (messageSync) {
            is MessageSync.Load -> viewState = Loading(messageSync.message)
            is MessageSync.Error -> viewState = SyncFetchStatus.ShowError(messageSync.message)
            is MessageSync.Success -> {
                viewState = SyncFetchStatus.Success
                compositeDisposable.clear()
            }
        }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun selectAllGoods() {
        compositeDisposable.add(syncRepository.selectAllGoods().observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe {items->
                Log.i("happy", items?.toString() ?: "empty")
            }
//            .subscribe { t1, t2 ->
////                t1.forEach {
////                    Log.d("happy", it.toString())
////                }
//                t1?.let {
//                    Log.d("happy", t1.toString())
//                } ?: Log.d("happy", "Empty goodg")
//
//            }
        )
    }

}

data class SyncViewState(
    val fetchStatus: SyncViewState
)

sealed class SyncFetchStatus {
    object Success : SyncFetchStatus()
    object Empty : SyncFetchStatus()
    data class ShowError(val message: String) : SyncFetchStatus()
    data class Loading(val message: String) : SyncFetchStatus()
}

sealed class SyncAction {
    data class ShowSnackbar(val message: String) : SyncAction()
}

sealed class SyncEvent {
    object GetData : SyncEvent()
}
