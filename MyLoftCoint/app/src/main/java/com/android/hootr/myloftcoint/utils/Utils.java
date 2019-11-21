package com.android.hootr.myloftcoint.utils;

import android.util.Base64;

public class Utils {

    public static String getAuth() {
        return "Based" + Base64.encodeToString(("test:test").getBytes(), Base64.NO_WRAP);
    }
}
