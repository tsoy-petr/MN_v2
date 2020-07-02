package com.android.hootr.tsd.useCase

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun Activity.showKeyboardEditTextEx(editText: EditText) {
    val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    mgr.showSoftInput(editText, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
}