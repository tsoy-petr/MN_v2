package com.android.hootr.myloftcoint.screens.welcom;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DimenRes;
import androidx.annotation.StringRes;

public class WelcomePage implements Parcelable {

    @DimenRes
    private int icon;

    @StringRes
    private int title;

    @StringRes
    private int subTitle;

    public WelcomePage(int icon, int title, int subTitle) {
        this.icon = icon;
        this.title = title;
        this.subTitle = subTitle;
    }

    protected WelcomePage(Parcel in) {
        icon = in.readInt();
        title = in.readInt();
        subTitle = in.readInt();
    }

    public static final Creator<WelcomePage> CREATOR = new Creator<WelcomePage>() {
        @Override
        public WelcomePage createFromParcel(Parcel in) {
            return new WelcomePage(in);
        }

        @Override
        public WelcomePage[] newArray(int size) {
            return new WelcomePage[size];
        }
    };

    public int getIcon() {
        return icon;
    }

    public int getTitle() {
        return title;
    }

    public int getSubTitle() {
        return subTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeInt(title);
        dest.writeInt(subTitle);
    }
}
