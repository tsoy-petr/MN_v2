package com.android.hootor.mn_v2.domain.sync.model

import androidx.room.*

data class PackageItem(
    val uuid: String,
    val typesOfPrice: List<TypeOfPriceEntity> = listOf(),
    val categories: List<CategoryEntity> = listOf(),
    val goods: List<GoodsItemEntity> = listOf(),
    val priceLists: List<PriceListEntity> = listOf(),
    val itemsPriceList: List<ItemPriceListEntity> = listOf()
)

@Entity(tableName = "type_of_price")
data class TypeOfPriceEntity(
    @PrimaryKey
    val uuid: String,
    val title: String
)

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey
    val uuid: String,
    val title: String
)

@Entity(tableName = "goods_table")
data class GoodsItemEntity(
    @PrimaryKey
    val uuid: String,
    val title: String
)

@Entity(tableName = "price_list", indices = [
    Index("uuid")
])
data class PriceListEntity(
    @PrimaryKey
    val uuid: String,
    val title: String
)

@Entity(
    tableName = "item_price_list", primaryKeys = ["id", "parent", "uuid_price_list", "type"],
    foreignKeys = [
        ForeignKey(
            entity = PriceListEntity::class,
            parentColumns = ["uuid"],
            childColumns = ["uuid_price_list"],
            onDelete = ForeignKey.CASCADE
            ,
            deferred = true
        )
    ]
    ,
    indices = [
        Index("uuid_price_list"),
        Index("id")
        ,
        Index("parent"),
        Index("type"),
        Index("id", "parent", "type", unique = true)
    ]
)
data class ItemPriceListEntity(

    val id: String,

    val parent: String = "",

    @ColumnInfo(name = "uuid_price_list")
    val uuidPriceList: String,

    val type: Int = 0
)