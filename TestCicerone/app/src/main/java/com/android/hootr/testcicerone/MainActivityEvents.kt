package com.android.hootr.testcicerone

sealed class MainActivityEvents {

    class ShowFragment_1() : MainActivityEvents()
    class ShowFragment_2() : MainActivityEvents()
    class ShowFragment_3() : MainActivityEvents()
    class BackFragment_2() : MainActivityEvents()
    class BackFragment_1() : MainActivityEvents()
    class Exit() : MainActivityEvents()

}