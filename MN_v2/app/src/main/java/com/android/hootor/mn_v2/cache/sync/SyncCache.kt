package com.android.hootor.mn_v2.cache.sync

import com.android.hootor.mn_v2.domain.sync.model.*
import javax.inject.Inject

class SyncCache @Inject constructor(private val syncDatabase: SyncDatabase) {

    fun updateTypeOfPrices(newTypeOfPriceList: List<TypeOfPriceEntity>) {
        syncDatabase.syncDao.updateTypeOfPrices(newTypeOfPriceList)
    }

    fun updateGoods(newGoodsEntityList: List<GoodsItemEntity>) {
        syncDatabase.syncDao.updateGoods(newGoodsEntityList)
    }

    fun updateCategorys(newCategoryEntityList: List<CategoryEntity>) {
        syncDatabase.syncDao.updateCategory(newCategoryEntityList)
    }

    fun updatePricesList(newPriceListEntityList: List<PriceListEntity>) {
        syncDatabase.syncDao.updatePricesList(newPriceListEntityList)
    }

    fun updateItemsPriceList(newItemPriceListEntityList: List<ItemPriceListEntity>) {
        syncDatabase.syncDao.updateItemsPriceList(newItemPriceListEntityList)
    }



}