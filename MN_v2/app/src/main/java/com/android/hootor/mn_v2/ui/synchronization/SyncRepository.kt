package com.android.hootor.mn_v2.ui.synchronization

import android.annotation.SuppressLint
import com.android.hootor.mn_v2.cache.sync.SyncDatabase
import com.android.hootor.mn_v2.domain.sync.model.*
import com.android.hootor.mn_v2.remote.SyncRemote
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class SyncRepository @Inject constructor(
    val syncRemote: SyncRemote,
    val syncDatabase: SyncDatabase
) {

    private val publisher = PublishSubject.create<MessageSync>()
    private val compositeDisposable = CompositeDisposable()

    fun executeSync(): PublishSubject<MessageSync> {
        startLoad()
        return publisher
    }

    @SuppressLint("CheckResult")
    private fun startLoad() {

        compositeDisposable.add(syncRemote.getArrayPackage().subscribeOn(Schedulers.io())
            .flatMap { arrayPackage ->

                arrayPackage.items.sortedBy { it.order }

                val listOfType = mutableListOf<Triple<String, List<PackageItemInfo>, Boolean>>()

                val sizOfTyps = arrayPackage.items.groupBy { it.type }.size
                arrayPackage.items.groupBy { it.type }.iterator().withIndex().forEach {
                    listOfType.add(
                        Triple(
                            it.value.key,
                            it.value.value,
                            it.index == sizOfTyps - 1
                        )
                    )
                }

                return@flatMap Flowable.fromIterable(listOfType)

            }
            .concatMap {

                publisher.onNext(MessageSync.Load("пришел тип данных - ${it.first}, количество пакетов: ${it.second.size}"))

                Thread.sleep(1200)
                when (it.first) {
                    TYPE_GOODS -> {
                        saveToDB(
                            it.second,
                            syncRemote::getPackage,
                            { it.goods },
                            syncDatabase.syncDao::updateGoods
                        )
                    }
                    TYPE_CATEGORY -> {
                        saveToDB(
                            it.second,
                            syncRemote::getPackage,
                            { it.categories },
                            syncDatabase.syncDao::updateCategory
                        )
                    }
                    TYPE_OF_PRICE -> {

                        saveToDB(
                            it.second,
                            syncRemote::getPackage,
                            { it.typesOfPrice },
                            syncDatabase.syncDao::updateTypeOfPrices
                        )

                    }
                    TYPE_PRICE_LIST -> {
                        saveToDB(
                            it.second,
                            syncRemote::getPackage,
                            { it.priceLists },
                            syncDatabase.syncDao::updatePricesList
                        )
                    }
                    TYPE_ITEM_PRICE_LIST -> {
                        saveToDB(
                            it.second,
                            syncRemote::getPackage,
                            { it.itemsPriceList },
                            syncDatabase.syncDao::updateItemsPriceList
                        )
                    }
                }

                Flowable.fromCallable {
                    it.third
                }
            }.subscribe(
                {
                    if (it) {
                        publisher.onNext(MessageSync.Success)
                        compositeDisposable.clear()
                    }
                }, {
                    publisher.onNext(
                        MessageSync.Error(
                            it.message?.toString() ?: "error of sync"
                        )
                    )
                }
            )
        )

    }

    fun selectAllGoods() = syncDatabase.syncDao.getAllGoods()

    inline fun <T> saveToDB(
        packListItemsPackage: List<PackageItemInfo>,
        loading: (String) -> PackageItem, fg: (PackageItem) -> List<T>, upDB: (List<T>) -> Unit
    ) {
        val allItems = mutableListOf<T>()
        for (i in 0 until packListItemsPackage.size) {
            val curr = loading(packListItemsPackage[i].uuid)
            allItems += fg(curr)
        }
        upDB(allItems)
    }

    suspend fun executeSyncSus(): PublishSubject<MessageSync> {
        startLoadAlternativ()
        return publisher
    }

    suspend private fun startLoadAlternativ() {

        delay(1000)
        coroutineScope {
            val arrayUuidPackages = async {
                syncRemote.getPacageArraySus()
            }.await()

            publisher.onNext(MessageSync.Load("Получены заголовки пакетов"))

        }


    }

}


sealed class MessageSync {
    data class Load(val message: String) : MessageSync()
    data class Error(val message: String) : MessageSync()
    object Success : MessageSync()
}