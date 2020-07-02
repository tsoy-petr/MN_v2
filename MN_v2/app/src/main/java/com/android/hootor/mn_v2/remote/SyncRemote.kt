package com.android.hootor.mn_v2.remote

import com.android.hootor.mn_v2.domain.sync.model.*
import io.reactivex.Flowable
import javax.inject.Inject

interface SyncRemote {

    fun getArrayPackage(): Flowable<ArrayPackageItem>

    fun getPackage(uuidPackage: String): PackageItem
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
                    uuidPriceList = "2a1fda82-b6d4-11ea-b3de-0242ac130004",
                    id = "16d45aa6-bab3-11ea-b3de-0242ac130004"
                ),
                //Столы
                ItemPriceListEntity(
                    uuidPriceList = "2a1fda82-b6d4-11ea-b3de-0242ac130004",
                    id = "16d45baa-bab3-11ea-b3de-0242ac130004",
                    parent = "2a1fda82-b6d4-11ea-b3de-0242ac130004"
                ),
                //Стекляный стол
                ItemPriceListEntity(
                    uuidPriceList = "2a1fda82-b6d4-11ea-b3de-0242ac130004",
                    id = "16d46136-bab3-11ea-b3de-0242ac130004",
                    parent = "16d45baa-bab3-11ea-b3de-0242ac130004"
                ),

                //Стулья
                ItemPriceListEntity(
                    uuidPriceList = "2a1fda82-b6d4-11ea-b3de-0242ac130004",
                    id = "16d45dd0-bab3-11ea-b3de-0242ac130004",
                    parent = "2a1fda82-b6d4-11ea-b3de-0242ac130004"
                )
            )
        )

    private fun mocPriceList(uuid: String) =
        PackageItem(
            uuid = uuid,
            priceLists = listOf(
                PriceListEntity(
                    uuid = "16d46df2-bab3-11ea-b3de-0242ac130004",
                    title = "Основонй прайс-лист"
                )
            )
        )

    private fun mocTypeOfPrice(uuid: String) =
        PackageItem(
            uuid = uuid,
            typesOfPrice = listOf(
                TypeOfPriceEntity(
                    uuid = "16d45862-bab3-11ea-b3de-0242ac130004",
                    title = "Основной"
                )
            )
        )

    private fun mocCategory(uuid: String) =
        PackageItem(
            uuid = uuid,
            categories = listOf(
                CategoryEntity(
                    uuid = "16d45aa6-bab3-11ea-b3de-0242ac130004",
                    title = "Мебель"
                ),
                CategoryEntity(
                    uuid = "16d45baa-bab3-11ea-b3de-0242ac130004",
                    title = "Столы"
                ),
                CategoryEntity(
                    uuid = "16d45dd0-bab3-11ea-b3de-0242ac130004",
                    title = "Стулья"
                ),
                CategoryEntity(
                    uuid = "16d45eb6-bab3-11ea-b3de-0242ac130004",
                    title = "Одежда"
                ),
                CategoryEntity(
                    uuid = "16d45f88-bab3-11ea-b3de-0242ac130004",
                    title = "Рубашка"
                ),
                CategoryEntity(
                    uuid = "16d4605a-bab3-11ea-b3de-0242ac130004",
                    title = "Брюки"
                )
            )
        )

    private fun mocGoods_1(uuid: String) =
        PackageItem(
            uuid = uuid,
            goods = listOf(
                GoodsItemEntity(
                    uuid = "16d46136-bab3-11ea-b3de-0242ac130004",
                    title = "Стекляный стол"
                ),
                GoodsItemEntity(
                    uuid = "16d461fe-bab3-11ea-b3de-0242ac130004",
                    title = "Деревянный стол"
                ),
                GoodsItemEntity(
                    uuid = "16d463b6-bab3-11ea-b3de-0242ac130004",
                    title = "Деревянный стул"
                ),
                GoodsItemEntity(
                    uuid = "16d464a6-bab3-11ea-b3de-0242ac130004",
                    title = "Стекляный стул"
                ),
                GoodsItemEntity(
                    uuid = "16d466cc-bab3-11ea-b3de-0242ac130004",
                    title = "Рубашка с коротким рукавом"
                ),
                GoodsItemEntity(
                    uuid = "16d4679e-bab3-11ea-b3de-0242ac130004",
                    title = "Рубашка с длинным рукавом"
                ),
                GoodsItemEntity(
                    uuid = "16d46866-bab3-11ea-b3de-0242ac130004",
                    title = "Брюки белые"
                ),
                GoodsItemEntity(
                    uuid = "16d4692e-bab3-11ea-b3de-0242ac130004",
                    title = "Брюки черные"
                )
            )
        )

    private fun mocGoods_2(uuid: String) =
        PackageItem(
            uuid = uuid,
            goods = listOf(
                GoodsItemEntity(
                    uuid = "16d469ec-bab3-11ea-b3de-0242ac130004",
                    title = "Стол кухонный"
                ),
                GoodsItemEntity(
                    uuid = "16d46ab4-bab3-11ea-b3de-0242ac130004",
                    title = "Кухонный стул"
                ),
                GoodsItemEntity(
                    uuid = "16d46c62-bab3-11ea-b3de-0242ac130004",
                    title = "Рубашка стильная"
                ),
                GoodsItemEntity(
                    uuid = "16d46d2a-bab3-11ea-b3de-0242ac130004",
                    title = "Брюки модные"
                )
            )
        )

}