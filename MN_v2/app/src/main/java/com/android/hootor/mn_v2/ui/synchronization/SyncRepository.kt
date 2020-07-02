package com.android.hootor.mn_v2.ui.synchronization

import android.annotation.SuppressLint
import com.android.hootor.mn_v2.cache.sync.SyncDatabase
import com.android.hootor.mn_v2.domain.sync.model.*
import com.android.hootor.mn_v2.remote.SyncRemote
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

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
//                        val allItemGoods = mutableListOf<GoodsItemEntity>()
//                        for (i in 0 until it.second.size) {
//                            val curr = syncRemote.getPackage(it.second[i].uuid)
//                            allItemGoods += curr.goods
//                        }
//                        syncDatabase.syncDao.updateGoods(allItemGoods)

                        saveToDB(
                            it.second,
                            syncRemote::getPackage,
                            { it.goods },
                            syncDatabase.syncDao::updateGoods
                        )
                    }
                    TYPE_CATEGORY -> {
//                        val allCategory = mutableListOf<CategoryEntity>()
//                        for (i in 0 until it.second.size) {
//                            val curr = syncRemote.getPackage(it.second[i].uuid)
//                            allCategory += curr.categories
//                        }
//                        syncDatabase.syncDao.updateCategory(allCategory)
//
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

    private fun concatAllGoods(listPackage: List<PackageItem>): List<GoodsItemEntity> {
        val buff = mutableListOf<GoodsItemEntity>()
        listPackage.forEach {
            buff += it.goods
        }
        return buff
    }

    fun selectAllGoods() = syncDatabase.syncDao.getAllGoods()

    inline fun <T> saveToDB(
        packListItemsPackage: List<PackageItemInfo>,
        ex: (String) -> PackageItem, fg: (PackageItem) -> List<T>, upDB: (List<T>) -> Unit
    ) {
        val allItems = mutableListOf<T>()
        for (i in 0 until packListItemsPackage.size) {
            val curr = ex(packListItemsPackage[i].uuid)
            allItems += fg(curr)
        }
    }
}


sealed class MessageSync {
    data class Load(val message: String) : MessageSync()
    data class Error(val message: String) : MessageSync()
    object Success : MessageSync()
}