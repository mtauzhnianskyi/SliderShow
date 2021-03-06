package com.android.slider.fragments;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.android.slider.R;
import com.android.slider.utils.AlarmUtil;
import com.android.slider.utils.Prefs;



public class PrefsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener{

	private Prefs prefs;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		
        if (key.equals(getString(R.string.reboot_checkbox_preference)))
        {
        	Boolean isFlagRebootStart = sharedPreferences.getBoolean(key, true);
        	Prefs prefs = new Prefs(getActivity());
        	prefs.saveRebootSlidingPref(isFlagRebootStart);
        }
        if (key.equals(getString(R.string.charging_checkbox_preference)))
        {
        	Boolean isFlagChargingStart = sharedPreferences.getBoolean(key, true);
        	Prefs prefs = new Prefs(getActivity());
        	prefs.saveChargingSlidingPref(isFlagChargingStart);
        }
        if (key.equals(getString(R.string.source_list_preference)))
        {
        	ListPreference lp = (ListPreference) findPreference(key);
        	Prefs prefs = new Prefs(getActivity());
        	prefs.saveResourcePref(Integer.parseInt((String) lp.getValue()));
        }
        if (key.equals(getString(R.string.animation_list_preference)))
        {
        	ListPreference lp = (ListPreference) findPreference(key);
        	Prefs prefs = new Prefs(getActivity());
        	prefs.saveAnimationPref(Integer.parseInt((String) lp.getValue()));
        }
        if (key.equals(getString(R.string.sliding_interval_edittext_preference)))
        {
        	Prefs prefs = new Prefs(getActivity());
        	prefs.saveDurationSlidingPref(Integer.parseInt(sharedPreferences.getString(key, "5")+"000"));
        }
        if (key.equals(getString(R.string.start_time_edittext_preference)))
        {
        	Preference pref = findPreference(key);
        	String timeIncome = sharedPreferences.getString(key, "00:00:00");
        	pref.setSummary(timeIncome);
        	AlarmUtil alarm = new AlarmUtil(getActivity());
        	alarm.setupLaunchSliding(timeIncome);
        }
        if (key.equals(getString(R.string.end_time_edittext_preference)))
        {
        	Preference pref = findPreference(key);
        	String timeIncome = sharedPreferences.getString(key, "00:00:00");
        	pref.setSummary(timeIncome);
        	AlarmUtil alarm = new AlarmUtil(getActivity());
        	alarm.setupStopSliding(timeIncome);
        }
        
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

	}

	@Override
	public void onPause() {
	    getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	    super.onPause();
	}
}
