package com.android.hootr.navigationfragments.type

open class HandleOnce<out T>(private val content: T) {

    private var hasBeenHandled = false

    fun getContentIfNotHandled() = if (hasBeenHandled) null else {
        hasBeenHandled = true
        content
    }

}