package com.android.hootr.tsd.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.hootr.tsd.App
import com.android.hootr.tsd.prefs.Prefs
import com.android.hootr.tsd.screens.login.LoginActivity
import javax.inject.Inject

class LaunchActivity : AppCompatActivity() {

    @Inject
    lateinit var prefs: Prefs

    companion object {

        fun startActivity(context: Context) {
            val starter = Intent(context, LaunchActivity::class.java)
            starter.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as App).applicationComponent.inject(this)
//        val prefs = (application as App).getPrefs()

        val url1C = prefs?.getURL1CServer() ?: ""
        val userCode = prefs?.getCodeUser() ?: ""
        val nodeCode = prefs?.getNodeCode() ?: ""

        if (url1C.isEmpty()) {
            SettingsActivity.startActivity(this)
        }
        else if(userCode.isEmpty() || nodeCode.isEmpty()) {
            LoginActivity.startActivity(this)
        }
        else {
            MainActivity.startActivity(this)
        }

        finish()
    }

}
