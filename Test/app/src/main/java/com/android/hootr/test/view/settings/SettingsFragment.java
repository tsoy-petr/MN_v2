package com.android.hootr.test.view.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import com.android.hootr.test.R;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.main_preferences);


        PreferenceScreen preferenceScreen = getPreferenceScreen();

        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();

        int count = preferenceScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {
            Preference preference = preferenceScreen.getPreference(i);

            if (preference instanceof ListPreference) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceLabel(preference, value);
            }

        }

    }

    private void setPreferenceLabel(Preference preference, String value) {

        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int index = listPreference.findIndexOfValue(value);
            if (index >= 0) {
                listPreference.setSummary(listPreference.getEntries()[index]);
            }
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);

        if (preference instanceof ListPreference) {
            String value = sharedPreferences.getString(preference.getKey(), "");
            setPreferenceLabel(preference, value);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroyView();
    }

}
