package com.android.hootor.mn_v2.utils

import android.os.Bundle

internal inline fun <reified T : Any> Bundle.requireImpl(key: String): T {
    if (!containsKey(key)) {
        throw IllegalArgumentException("Bundle has no key $key")
    }
    return get(key) as? T ?: throw IllegalStateException("Wrong type found in Bundle for key $key")
}