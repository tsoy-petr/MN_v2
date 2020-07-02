package com.android.hootr.client1c.presentation

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.hootr.client1c.domain.navigate.MainViewEvent
import com.android.hootr.client1c.domain.type.HandleOnce
import com.android.hootr.client1c.interactor.ImpMainInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewPresentation @Inject constructor(val mainInteractor: ImpMainInteractor) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val viewEvent: MutableLiveData<MainViewModel> = MutableLiveData<MainViewModel>()

    init {
        loginSettings()
    }
    fun getViewEventLivedata(): LiveData<MainViewModel> {
        return viewEvent
    }

    fun setScreen(navEvent: MainViewEvent) {
        viewEvent.value =
            viewEvent.value?.copy(isProgress = false, viewState = HandleOnce(navEvent))
    }

    @SuppressLint("CheckResult")
    fun loginSettings() {

        compositeDisposable.add(
            mainInteractor.getChackSettingsLigins()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    viewEvent.value = MainViewModel(HandleOnce("Проверяю настройки"),true, HandleOnce(MainViewEvent.None))
                }
                .subscribe { check ->
                    viewEvent.value = viewEvent.value?.copy(isProgress = false)
                    if (!check.isSetting) {
                        viewEvent.value =
                            viewEvent.value?.copy(viewState = HandleOnce(MainViewEvent.ShowSettings()))
                    } else if(!check.isLogin) {
                        viewEvent.value =
                            viewEvent.value?.copy(viewState = HandleOnce(MainViewEvent.ShowLogin()))
                    } else {
                        viewEvent.value =
                            viewEvent.value?.copy(viewState = HandleOnce(MainViewEvent.ShowMainMenu()))
                    }
                })
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}

data class MainViewModel(
    val message: HandleOnce<String>,
    val isProgress: Boolean,
    val viewState: HandleOnce<MainViewEvent>
)