package com.android.hootr.tsd.screens.login

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.hootr.tsd.App
import com.android.hootr.tsd.R
import com.android.hootr.tsd.api.login.LoginApi
import com.android.hootr.tsd.screens.LaunchActivity
import com.android.hootr.tsd.screens.event.login.EventLogin
import com.android.hootr.tsd.useCase.showKeyboardEditTextEx
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var viewModel: LoginViewModel? = null
    lateinit var loginApi: LoginApi

    companion object {
        fun startActivity(context: Context) {
            val starter = Intent(context, LoginActivity::class.java)
//        starter.flags = (Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        (application as App).applicationComponent.inject(this)

        initViewModel()

        editTextCodeUser.requestFocus()

        showKeyboardEditTextEx(editTextCodeUser)

        editTextCodeUser.setOnKeyListener { view, i, keyEvent ->

            if (keyEvent.action == KeyEvent.ACTION_DOWN
                && i == KeyEvent.KEYCODE_ENTER
            ) {
                btnLogin.requestFocus()
                editTextCodeUser.isCursorVisible = false
                true
            } else false

        }

        viewModel?.event?.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is EventLogin.Success -> success()
                is EventLogin.Loading -> showLoading()
                is EventLogin.ShowError -> showError(it.messeg)
            }

        })
    }

    fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    private fun showError(messeg: String) {
        progress.visibility = View.INVISIBLE
        btnLogin.isEnabled = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            editTextCodeUser.focusable = View.FOCUSABLE
        }
        editTextCodeUser.isLongClickable = true
        editTextCodeUser.isCursorVisible = true

        Toast.makeText(this, messeg, Toast.LENGTH_LONG).show()
    }

    fun success() {
        progress.visibility = View.INVISIBLE
        LaunchActivity.startActivity(this)
    }

    fun showLoading() {
        progress.visibility = View.GONE
        btnLogin.isEnabled = false
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            editTextCodeUser.focusable = View.NOT_FOCUSABLE
        }
        editTextCodeUser.isLongClickable = false
        editTextCodeUser.isCursorVisible = false

    }


}
