package com.android.hootr.tsd.screens.testFragments

sealed class VMEventViewNewArrive {
    class MenyNewArrive():VMEventViewNewArrive()
    class StartScaning():VMEventViewNewArrive()
    class DiscrepanciesGoods():VMEventViewNewArrive()
    class CtemporarilyExit():VMEventViewNewArrive()
    class Complete():VMEventViewNewArrive()
    class Goods():VMEventViewNewArrive()
}