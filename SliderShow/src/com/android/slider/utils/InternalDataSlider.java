package com.android.slider.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.slider.R;

public class InternalDataSlider extends BaseDataSlider{
	
	private String dir;
	
	private ArrayList<File> allFilesInLocalDir;
	
	private Iterator<File> iterator;
	
	public InternalDataSlider(Context context, Handler handler, ImageView ... imageViews ){
		super(context, handler, imageViews);
		
		dir = context.getFilesDir().getAbsolutePath();
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		allFilesInLocalDir = new ArrayList<File>(Arrays.asList(listOfFiles));
		iterator = allFilesInLocalDir.listIterator();
	}
	
	@Override
	public void run() {

		if (iterator.hasNext()) {
			if (isPortraitMode) {
				if (iterator.hasNext()) {
					ImageUtil.checkFileAndSet(iterator.next(), imageView1);
				} else {
					iterator = allFilesInLocalDir.listIterator();
					ImageUtil.checkFileAndSet(iterator.next(), imageView1);
				}
			} else {
				if (iterator.hasNext()) {
					File file1 = iterator.next();
					if (file1.isFile()) {
						ImageUtil.checkFileAndSet(file1, imageView1);
					}
				} else {
					iterator = allFilesInLocalDir.listIterator();
					ImageUtil.checkFileAndSet(iterator.next(), imageView1);
				}
				if (iterator.hasNext()) {
					File file2 = iterator.next();
					if (file2.isFile()) {
						ImageUtil.checkFileAndSet(file2, imageView2);
					}
				} else {
					iterator = allFilesInLocalDir.listIterator();
					ImageUtil.checkFileAndSet(iterator.next(), imageView2);
				}

			}
			handler.postDelayed(this, Prefs.getInstance(context).retrieveDurationlidingPref());
		}
		else{
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					Toast.makeText(context, context.getResources().getString(R.string.no_local_photos), Toast.LENGTH_LONG).show();
				}
			});
		}
	}
}
