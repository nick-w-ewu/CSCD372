package com.witmer.nicholas.npwitmerlab6;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        onSharedPreferenceChanged(prefs, "name");
        onSharedPreferenceChanged(prefs, "years_remaining");
        onSharedPreferenceChanged(prefs, "home_world");
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        Preference pref = findPreference(key);;
        if(key.equalsIgnoreCase("name"))
        {
            String name = sharedPreferences.getString("name", null);
            pref.setSummary(name);
        }
        else if(key.equalsIgnoreCase("years_remaining"))
        {
            String years = sharedPreferences.getString("years_remaining", null);
            pref.setSummary(years);
        }
        else if(key.equalsIgnoreCase("home_world"))
        {
            String[] worlds = getResources().getStringArray(R.array.home_worlds);
            int ix = Integer.parseInt(sharedPreferences.getString("home_world", null));
            pref.setSummary(worlds[ix]);
        }
    }

    @Override
    public void onResume() {
        super.onResume() ;
        getPreferenceScreen().getSharedPreferences().
                registerOnSharedPreferenceChangeListener(this) ;
    }

    @Override
    public void onPause() {
        super.onPause() ;
        getPreferenceScreen().getSharedPreferences().
                unregisterOnSharedPreferenceChangeListener(this) ;
    }
}
