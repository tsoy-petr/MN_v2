package hootor.com.loftcoint19.screens.welcome;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;

import hootor.com.loftcoint19.R;

public class WelcomePage implements Parcelable {

    @DrawableRes
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

    public static List<WelcomePage> createList() {
        List<WelcomePage> pages = new ArrayList<>();
        pages.add(
                new WelcomePage(R.drawable.image_welcome_page_1, R.string.welcome_title_1, R.string.welcome_subtitle_1)
        );
        pages.add(
                new WelcomePage(R.drawable.image_welcome_page_2, R.string.welcome_title_2, R.string.welcome_subtitle_2)
        );
        pages.add(
                new WelcomePage(R.drawable.image_welcome_page_3, R.string.welcome_title_3, R.string.welcome_subtitle_3)
        );

        return pages;
    }


    public int getIcon() {
        return icon;
    }

    public int getTitle() {
        return title;
    }

    public int getSubTitle() {
        return subTitle;
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
