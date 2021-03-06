package com.android.slider.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;

public class HTTPDataSlider extends BaseDataSlider {

	private HttpURLGeneratorUtil urlGenerator;

	public HTTPDataSlider(Context context, Handler handler,
			ImageView... imageViews) {
		super(context, handler, imageViews);
		urlGenerator = new HttpURLGeneratorUtil();
	}

	@Override
	public void run() {
		if (!isPortraitMode) {
			imageView1.setTag(urlGenerator.generateRandomNumberOfURIImage());
			new DownloadImagesUtil(context, false).execute(imageView1);

		} else {
			imageView1.setTag(urlGenerator.generateRandomNumberOfURIImage());
			new DownloadImagesUtil(context, true).execute(imageView1);
			imageView2.setTag(urlGenerator.generateRandomNumberOfURIImage());
			new DownloadImagesUtil(context, true).execute(imageView2);

		}
		handler.postDelayed(this, Prefs.getInstance(context)
				.retrieveDurationlidingPref());
	}
}
