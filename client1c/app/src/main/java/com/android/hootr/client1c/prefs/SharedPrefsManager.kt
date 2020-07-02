package com.android.hootr.client1c.prefs

import android.content.SharedPreferences
import com.android.hootr.client1c.domain.account.AccountEntity
import javax.inject.Inject

class SharedPrefsManager @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val ACCOUNT_NAME = "account_name"
        const val ACCOUNT_PASSWORD = "account_password"
        const val ACCOUNT_UUID = "account_uuid"
        const val SERVER_URL = "server_url"
    }

    fun isLogin(): Boolean {
        val uuid = prefs.getString(ACCOUNT_UUID, "")

        if (uuid == null) {
            return false
        } else {
            return !uuid.isEmpty()
        }

    }

    fun getAccount(): AccountEntity? = if (isLogin()) AccountEntity(
        prefs.getString(ACCOUNT_UUID, ""),
        prefs.getString(ACCOUNT_NAME, ""),
        prefs.getString(ACCOUNT_PASSWORD, "")
    ) else null

    fun setAccount(accountEntity: AccountEntity) {
        prefs.edit().apply {
            putSafely(ACCOUNT_UUID, accountEntity.uuid)
            putSafely(ACCOUNT_NAME, accountEntity.name)
            putSafely(ACCOUNT_PASSWORD, accountEntity.password)
        }.apply()
    }

    fun removeAccount() {
        prefs.edit().apply{
            remove(ACCOUNT_UUID)
            remove(ACCOUNT_NAME)
            remove(ACCOUNT_PASSWORD)
        }.apply()
    }

    fun setURLServer(url: String) {
        prefs.edit().apply{
            putSafely(SERVER_URL, url)
        }.apply()
    }

    fun getURLServer(): String? = prefs.getString(SERVER_URL, "")

}

fun SharedPreferences.Editor.putSafely(key: String, value: Long?) {
    if (value != null && value != 0L) {
        putLong(key, value)
    }
}

fun SharedPreferences.Editor.putSafely(key: String, value: String?) {
    if (value != null && value.isNotEmpty()) {
        putString(key, value)
    }
}