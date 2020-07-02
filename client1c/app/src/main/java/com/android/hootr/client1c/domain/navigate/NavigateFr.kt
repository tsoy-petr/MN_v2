package com.android.hootr.client1c.domain.navigate

sealed class NavigateFr {
    object NavSettings: NavigateFr()
    object NavLogin: NavigateFr()
    object NavMainMenu: NavigateFr()
}