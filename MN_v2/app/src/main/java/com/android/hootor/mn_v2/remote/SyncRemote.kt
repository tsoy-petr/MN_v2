package com.android.hootor.mn_v2.remote

import com.android.hootor.mn_v2.domain.sync.model.*
import io.reactivex.Flowable
import javax.inject.Inject

interface SyncRemote {

    fun getArrayPackage(): Flowable<ArrayPackageItem>

    fun getPackage(uuidPackage: String): PackageItem

    suspend fun getPacageArraySus(): ArrayPackageItem

    fun getPackageSus(uuidPackage: String): PackageItem
}

class SyncRemoteImpl @Inject constructor() : SyncRemote {

    override fun getArrayPackage(): Flowable<ArrayPackageItem> {

        return Flowable.just(
            ArrayPackageItem(
                listOf(
                    PackageItemInfo(
                        uuid = "2a1fd2d0-b6d4-11ea-b3de-0242ac130004",
                        type = TYPE_OF_PRICE,
                        order = 1
                    ),

                    PackageItemInfo(
                        uuid = "2a1fd9a6-b6d4-11ea-b3de-0242ac130004",
                        type = TYPE_CATEGORY,
                        order = 2
                    ),

                    PackageItemInfo(
                        uuid = "2a1fda82-b6d4-11ea-b3de-0242ac130004",
                        type = TYPE_PRICE_LIST,
                        order = 3
                    ),

                    PackageItemInfo(
                        uuid = "2a1fdb54-b6d4-11ea-b3de-0242ac130004",
                        type = TYPE_GOODS,
                        order = 4
                    ),
                    PackageItemInfo(
                        uuid = "2a1fdde8-b6d4-11ea-b3de-0242ac130004",
                        type = TYPE_GOODS,
                        order = 5
                    ),

                    PackageItemInfo(
                        uuid = "2a1fded8-b6d4-11ea-b3de-0242ac130004",
                        type = TYPE_ITEM_PRICE_LIST,
                        order = 6
                    )
                )
            )
        )

    }

    override fun getPackage(uuidPackage: String): PackageItem {

        return when (uuidPackage) {
            "2a1fd2d0-b6d4-11ea-b3de-0242ac130004" -> {
                mocTypeOfPrice(uuidPackage)
            }
            "2a1fd9a6-b6d4-11ea-b3de-0242ac130004" -> {
                mocCategory(uuidPackage)
            }
            "2a1fda82-b6d4-11ea-b3de-0242ac130004" -> {
                mocPriceList(uuidPackage)
            }
            "2a1fdb54-b6d4-11ea-b3de-0242ac130004" -> {
                mocGoods_1(uuidPackage)
            }
            "2a1fdde8-b6d4-11ea-b3de-0242ac130004" -> {
                mocGoods_2(uuidPackage)
            }
            "2a1fded8-b6d4-11ea-b3de-0242ac130004" -> {
                mocItemsPriceList(uuidPackage)
            }
            else -> {
                PackageItem(uuid = uuidPackage)
            }
        }
    }

    private fun mocItemsPriceList(uuid: String) =
        PackageItem(
            uuid = uuid,
            itemsPriceList = listOf(
                //Мебель
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "c1"
                ),
                //Столы
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "c2",
                    parent = "c1"
                ),
                //Стекляный стол
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "g1",
                    parent = "c2"
                ),
                //Деревянный стол
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "g3",
                    parent = "c2"
                ),


                //Стулья
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "c3",
                    parent = "c1"
                ),
                //Стекляный стул
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "g3",
                    parent = "c3"
                ),
                //Деревянный стул
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "g4",
                    parent = "c3"
                ),


                //Одежда
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "c4"
                ),

                //Рубашки
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "c5", parent = "c4"
                ),
                //Рубашка с коротким рукавом
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "g5", parent = "c5"
                ),
                //Рубашка с длинным рукавом
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "g6", parent = "c5"
                ),


                //Брюки
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "c6", parent = "c4"
                ),
                //Брюки белые
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "g7", parent = "c6"
                ),
                //Брюки черные
                ItemPriceListEntity(
                    uuidPriceList = "pl1",
                    id = "g8", parent = "c6"
                )
            )
        )

    private fun mocPriceList(uuid: String) =
        PackageItem(
            uuid = uuid,
            priceLists = listOf(
                PriceListEntity(
                    uuid = "pl1",
                    title = "Основонй прайс-лист"
                )
            )
        )

    private fun mocTypeOfPrice(uuid: String) =
        PackageItem(
            uuid = uuid,
            typesOfPrice = listOf(
                TypeOfPriceEntity(
                    uuid = "tp1",
                    title = "Основной"
                )
            )
        )

    private fun mocCategory(uuid: String) =
        PackageItem(
            uuid = uuid,
            categories = listOf(
                CategoryEntity(
                    uuid = "c1",
                    title = "Мебель"
                ),
                CategoryEntity(
                    uuid = "c2",
                    title = "Столы"
                ),
                CategoryEntity(
                    uuid = "c3",
                    title = "Стулья"
                ),
                CategoryEntity(
                    uuid = "c4",
                    title = "Одежда"
                ),
                CategoryEntity(
                    uuid = "c5",
                    title = "Рубашка"
                ),
                CategoryEntity(
                    uuid = "c6",
                    title = "Брюки"
                )
            )
        )

    private fun mocGoods_1(uuid: String) =
        PackageItem(
            uuid = uuid,
            goods = listOf(
                GoodsItemEntity(
                    uuid = "g1",
                    title = "Стекляный стол"
                ),
                GoodsItemEntity(
                    uuid = "g2",
                    title = "Деревянный стол"
                ),
                GoodsItemEntity(
                    uuid = "g3",
                    title = "Деревянный стул"
                ),
                GoodsItemEntity(
                    uuid = "g4",
                    title = "Стекляный стул"
                ),
                GoodsItemEntity(
                    uuid = "g5",
                    title = "Рубашка с коротким рукавом"
                ),
                GoodsItemEntity(
                    uuid = "g6",
                    title = "Рубашка с длинным рукавом"
                ),
                GoodsItemEntity(
                    uuid = "g7",
                    title = "Брюки белые"
                ),
                GoodsItemEntity(
                    uuid = "g8",
                    title = "Брюки черные"
                )
            )
        )

    private fun mocGoods_2(uuid: String) =
        PackageItem(
            uuid = uuid,
            goods = listOf(
                GoodsItemEntity(
                    uuid = "g9",
                    title = "Стол кухонный"
                ),
                GoodsItemEntity(
                    uuid = "g10",
                    title = "Кухонный стул"
                ),
                GoodsItemEntity(
                    uuid = "g11",
                    title = "Рубашка стильная"
                ),
                GoodsItemEntity(
                    uuid = "g12",
                    title = "Брюки модные"
                )
            )
        )

    override suspend fun getPacageArraySus() = ArrayPackageItem(
        listOf(
            PackageItemInfo(
                uuid = "2a1fd2d0-b6d4-11ea-b3de-0242ac130004",
                type = TYPE_OF_PRICE,
                order = 1
            ),

            PackageItemInfo(
                uuid = "2a1fd9a6-b6d4-11ea-b3de-0242ac130004",
                type = TYPE_CATEGORY,
                order = 2
            ),

            PackageItemInfo(
                uuid = "2a1fda82-b6d4-11ea-b3de-0242ac130004",
                type = TYPE_PRICE_LIST,
                order = 3
            ),

            PackageItemInfo(
                uuid = "2a1fdb54-b6d4-11ea-b3de-0242ac130004",
                type = TYPE_GOODS,
                order = 4
            ),
            PackageItemInfo(
                uuid = "2a1fdde8-b6d4-11ea-b3de-0242ac130004",
                type = TYPE_GOODS,
                order = 5
            ),

            PackageItemInfo(
                uuid = "2a1fded8-b6d4-11ea-b3de-0242ac130004",
                type = TYPE_ITEM_PRICE_LIST,
                order = 6
            )
        )
    )

    override fun getPackageSus(uuidPackage: String) = when (uuidPackage) {
        "2a1fd2d0-b6d4-11ea-b3de-0242ac130004" -> {
            mocTypeOfPrice(uuidPackage)
        }
        "2a1fd9a6-b6d4-11ea-b3de-0242ac130004" -> {
            mocCategory(uuidPackage)
        }
        "2a1fda82-b6d4-11ea-b3de-0242ac130004" -> {
            mocPriceList(uuidPackage)
        }
        "2a1fdb54-b6d4-11ea-b3de-0242ac130004" -> {
            mocGoods_1(uuidPackage)
        }
        "2a1fdde8-b6d4-11ea-b3de-0242ac130004" -> {
            mocGoods_2(uuidPackage)
        }
        "2a1fded8-b6d4-11ea-b3de-0242ac130004" -> {
            mocItemsPriceList(uuidPackage)
        }
        else -> {
            PackageItem(uuid = uuidPackage)
        }
    }

}