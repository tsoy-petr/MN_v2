package com.android.hootor.mn_v2.domain.sync.model

data class ArrayPackageItem(
    val items: List<PackageItemInfo>
)

data class PackageItemInfo(
    val uuid: String,
    val type: String,
    val order: Int
)

