package com.android.hootr.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.android.hootr.cryptoapp.api.ApiFactory
import com.android.hootr.cryptoapp.database.AppDatabase
import com.android.hootr.cryptoapp.pojo.CoinPriceInfo
import com.android.hootr.cryptoapp.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)

    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    private fun loadData() {

        compositeDisposable.add(
            ApiFactory.apiService.getTopCoinsInfo(limit = 50)
                .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
                .flatMap {
                    ApiFactory.apiService.getFullPriceList(fSyms = it)
                }
                .map { getPriceListFromRawData(it) }
                .delaySubscription(10, TimeUnit.SECONDS) //Переодическое повторение, через интервал времени
//                .repeat() //Бесконочно повторяет, до первой ошибки
//                .retry()  //Бесконечно потряет, если есть ошибка (интернета нет), после исчезновения ошибка запускается заново
                .subscribeOn(Schedulers.io())
                .subscribe({
                    db.coinPriceInfoDao().insertPriceList(it)
                }, {
                    Log.i("happyn", it.message)
                })

        )

    }

    fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo>? {

        val result = ArrayList<CoinPriceInfo>()

        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result

        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {

            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currenceKeySet = currencyJson.keySet()

            for (currencyKey in currenceKeySet) {

                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                priceInfo?.let {
                    result.add(it)
                }
            }
        }

        return result
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}