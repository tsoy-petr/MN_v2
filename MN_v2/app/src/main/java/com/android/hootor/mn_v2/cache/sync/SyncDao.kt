package com.android.hootor.mn_v2.cache.sync

import androidx.room.*
import com.android.hootor.mn_v2.domain.sync.model.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface SyncDao {

    //TypeOfPrice
    @Query("SELECT * FROM type_of_price WHERE uuid = :uuid")
    fun getTypeOfPricesByUUID(uuid: String): TypeOfPriceEntity

    @Query("SELECT * FROM type_of_price")
    fun getAllTypeOfPrices(): List<TypeOfPriceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTypeOfPriceList(typeOfPriceList: List<TypeOfPriceEntity>)

    @Query("DELETE FROM type_of_price")
    fun deleteAllTypeOfPrices()

    @Transaction
    fun updateTypeOfPrices(newTypeOfPriceList: List<TypeOfPriceEntity>) {
        deleteAllTypeOfPrices()
        insertTypeOfPriceList(newTypeOfPriceList)
    }

    //Goods
    @Query("SELECT * FROM goods_table")
    fun getAllGoods(): Flowable<List<GoodsItemEntity>>

    @Query("SELECT * FROM goods_table WHERE uuid IN (:uuidList)")
    fun getGoodsByListUuid(uuidList: List<String>): List<GoodsItemEntity>

    @Query("SELECT * FROM goods_table WHERE uuid = :uuid")
    fun getGoodsByUuid(uuid: String): GoodsItemEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGoodsList(goodsEntityList: List<GoodsItemEntity>)

    @Query("DELETE FROM goods_table")
    fun deleteAllGoods()

    @Transaction
    fun updateGoods(newGoodsEntityList: List<GoodsItemEntity>) {
        deleteAllGoods()
        insertGoodsList(newGoodsEntityList)
    }

    //Category
    @Query("SELECT * FROM category")
    fun getAllCategory(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE uuid = :uuid")
    fun getCategyByUuid(uuid: String): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategoryList(categoryEntityList: List<CategoryEntity>)

    @Query("DELETE FROM category")
    fun deleteAllCategory()

    @Transaction
    fun updateCategory(newCategoryEntityList: List<CategoryEntity>) {
        deleteAllCategory()
        insertCategoryList(newCategoryEntityList)
    }

    //PriceList
    @Query("SELECT * FROM price_list")
    fun getAllPriceLists(): List<PriceListEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceListEntityList: List<PriceListEntity>)

    @Query("DELETE FROM price_list")
    fun deleteAllPriceList()

    @Transaction
    fun updatePricesList(newPriceListEntityList: List<PriceListEntity>) {
        deleteAllPriceList()
        insertPriceList(newPriceListEntityList)
    }

    //ItemPriceList
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemPriceList(itemPriceListEntityList: List<ItemPriceListEntity>)

    @Query("DELETE FROM item_price_list")
    fun deleteAllItemsPriceList()

    @Transaction
    fun updateItemsPriceList(newItemPriceListEntityList: List<ItemPriceListEntity>) {
        deleteAllItemsPriceList()
        insertItemPriceList(newItemPriceListEntityList)
    }

    @Query("SELECT * FROM item_price_list WHERE type = 1 AND uuid_price_list = :uuidPriceList")
    fun getAllGroupItemPriceList(uuidPriceList: String): List<ItemPriceListEntity>

    @Query("SELECT id FROM item_price_list WHERE type = 2 AND parent = :uuidParent")
    fun getUuidGoodsByParent(uuidParent: String): List<String>

    //@Query("SELECT * FROM ")

    @Transaction
    fun getGoodsByUuidCategory(uuidCategory: String): List<GoodsItemEntity> {
        val listUuidGoods = getUuidGoodsByParent(uuidCategory)
        return getGoodsByListUuid(listUuidGoods)
    }

}