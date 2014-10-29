package com.android.slider.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyThreeClickListener implements OnTouchListener{
	
	private long lastTouchTime = -1;
	
	private short counter = 0;
	
	private OnTripleClickListener mListener;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if (event.getAction() == MotionEvent.ACTION_DOWN){
			long thisTime = System.currentTimeMillis();
			if (thisTime - lastTouchTime < 250) {

				if (counter == 2){
					mListener.onThreeClick(v);
					lastTouchTime = -1;
					counter = 0;
				}
		         // Double click
		         lastTouchTime = -1;
		         ++counter;

		      } else {
		         // too slow
		         lastTouchTime = thisTime;
		      }
		}
		return false;
	}
	
	public void setOnTripleClickListener(OnTripleClickListener listener){
		mListener = listener;
	}
	
	public interface OnTripleClickListener{
		void onThreeClick(View v);
	}

}
