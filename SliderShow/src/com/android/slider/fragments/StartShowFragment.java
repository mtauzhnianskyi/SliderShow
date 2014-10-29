package com.android.slider.fragments;

import com.android.slider.R;
import com.android.slider.activities.MainActivity;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A fragment containing a main view to start slide show.
 */
public class StartShowFragment extends Fragment{

	private static final String ARG_SECTION_NUMBER = "section_number";
	
	public StartShowFragment() {

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
	}
	
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        ((MainActivity) activity).onSectionAttached(
//                getArguments().getInt(ARG_SECTION_NUMBER));
    }
    

}

