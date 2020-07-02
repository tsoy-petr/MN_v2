package com.android.hootr.navigationfragments.type

sealed class MyEvent {

    class ShowQuantitentry() : MyEvent()
    class ShowMenu(): MyEvent()
    class ShowSeriesSelection: MyEvent()
    class ShowSeriesCreation: MyEvent()

}