package com.android.hootr.test.utils;

import android.icu.text.DateFormat;
import android.os.Build;
import android.provider.Settings;

import com.android.hootr.test.App;

public class Util {

    public static String ID_gadget() {
        String androidID = Settings.Secure.getString(App.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidID;
    }

    public static String formateDate(long date) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return DateFormat.getDateInstance().format(date);
        }

        return android.text.format.DateFormat.format("", date).toString();
    }

}
