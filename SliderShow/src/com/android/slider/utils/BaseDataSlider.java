package com.android.slider.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;

public abstract class BaseDataSlider implements Runnable{
	
	protected ImageView imageView1;
	
	protected ImageView imageView2;
	
	protected boolean isPortraitMode = false;
	
	protected Handler handler;

	protected Context context;
	
	public BaseDataSlider(Context context, Handler handler, ImageView ... imageViews){
		this.handler = handler;
		this.context = context;
		if (imageViews.length == 1) {
			isPortraitMode = false;
			imageView1 = imageViews[0];
		} else {
			isPortraitMode = true;
			imageView1 = imageViews[0];
			imageView2 = imageViews[1];
		}
	}
}
