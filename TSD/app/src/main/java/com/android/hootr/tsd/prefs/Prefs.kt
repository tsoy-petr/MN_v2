package com.android.hootr.tsd.prefs

interface Prefs {

    fun getURL1CServer():String
    fun setURL1CServer(url1C: String): Any?

    fun getCodeUser(): String
    fun setCodeUser(codeUser: String): Any?

    fun getNodeCode(): String
    fun setNodeUser(nodeCode: String): Any?

    fun getBTAdress(): String
    fun setBTAdress(btAdress: String): Any?
}