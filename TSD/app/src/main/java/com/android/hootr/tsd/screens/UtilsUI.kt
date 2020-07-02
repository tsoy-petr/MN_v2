package com.android.hootr.tsd.screens

import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService


//fun showKeyboard(activity: AppCompatActivity) {
//    if (activity.isKisKeyboardVisible()) return
//    val inputMethodManager: InputMethodManager? =
//        getSystemService<Any>(activity.getCon.INPUT_METHOD_SERVICE) as InputMethodManager?
//    if (getCurrentFocus() == null) {
//        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
//    } else {
//        val view: View = getCurrentFocus()
//        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_FORCED)
//    }
//}
//
//fun hideKeyboard() {
//    if (!isKeyboardVisible()) return
//    val inputMethodManager: InputMethodManager? =
//        getSystemService<Any>(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
//    val view: View = getCurrentFocus()
//    if (view == null) {
//        if (inputMethodManager.isAcceptingText()) inputMethodManager.toggleSoftInput(
//            InputMethodManager.HIDE_NOT_ALWAYS,
//            0
//        )
//    } else {
//        if (view is EditText) (view as EditText).setText((view as EditText).text.toString()) // reset edit text bug on some keyboards bug
//        inputMethodManager.hideSoftInputFromInputMethod(
//            view.getWindowToken(),
//            InputMethodManager.HIDE_NOT_ALWAYS
//        )
//    }
//}