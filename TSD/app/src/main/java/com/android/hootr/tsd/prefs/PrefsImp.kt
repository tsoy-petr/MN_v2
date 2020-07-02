package com.android.hootr.tsd.prefs

import android.content.Context

class PrefsImp(val context: Context): Prefs {

    companion object {
        const val PREFS_NAME = "prefs"
        const val KEY_URL1CSERVER = "url1CServer"
        const val KEY_CODEUSER = "codeUser"
        const val KEY_NODECODE = "nodeCode"
        const val KEY_BTADRESS = "btAdress"
    }

    private fun getPrefs() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun getURL1CServer() = getPrefs().getString(KEY_URL1CSERVER, "") ?: ""
    override fun setURL1CServer(url1C: String) = getPrefs().edit().putString(KEY_URL1CSERVER, url1C).apply()


    override fun getCodeUser() = getPrefs().getString(KEY_CODEUSER, "") ?: ""
    override fun setCodeUser(codeUser: String) = getPrefs().edit().putString(KEY_CODEUSER, codeUser).apply()


    override fun getNodeCode() = getPrefs().getString(KEY_NODECODE, "") ?: ""
    override fun setNodeUser(nodeCode: String) = getPrefs().edit().putString(KEY_NODECODE, nodeCode).apply()

    override fun getBTAdress():String = getPrefs().getString(KEY_BTADRESS, "") ?: ""
    override fun setBTAdress(btAdress: String) = getPrefs().edit().putString(KEY_BTADRESS, btAdress).apply()

}