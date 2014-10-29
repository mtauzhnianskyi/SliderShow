package com.android.slider.utils;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class ImageUtil {
	
	private int widthScreen;
	
	private int heightScreen;
	
	public ImageUtil(Context context){
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	    Display display = wm.getDefaultDisplay();
	    DisplayMetrics metrics = new DisplayMetrics();
	    display.getMetrics(metrics);
	    widthScreen = metrics.widthPixels;
	    heightScreen = metrics.heightPixels;
	}
	
	public Bitmap getBitmap(String imagePath) {

	    Bitmap resizedBitmap = BitmapFactory.decodeFile(imagePath);
	    return resizedBitmap;
	}
	
	public Bitmap getResizedBitmapFromPath(String imagePath, boolean isPortrait) {
		Bitmap resizedBitmap = getBitmap(imagePath);
		if (isPortrait)
			resizedBitmap = getResizedBitmap(resizedBitmap, heightScreen/2, widthScreen);
		else
			resizedBitmap = getResizedBitmap(resizedBitmap, heightScreen, widthScreen);
	    return resizedBitmap;
	}
	
	public Bitmap getResizedBitmapFromBitmap(Bitmap imagePath, boolean isPortrait) {
		//Bitmap resizedBitmap = getBitmap(imagePath);
		if (isPortrait)
			imagePath = getResizedBitmap(imagePath, heightScreen/2, widthScreen);
		else
			imagePath = getResizedBitmap(imagePath, heightScreen, widthScreen);
	    return imagePath;
	}
	
	public Bitmap getResizedBitmap(Bitmap bm) {
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) widthScreen) / width;
	    float scaleHeight = ((float) heightScreen) / height;
	    // CREATE A MATRIX FOR THE MANIPULATION
	    Matrix matrix = new Matrix();
	    // RESIZE THE BIT MAP
	    matrix.postScale(scaleWidth, scaleHeight);

	    // "RECREATE" THE NEW BITMAP
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
	    return resizedBitmap;
	}
	
	public Bitmap getResizedBitmap(Bitmap bm, int heightScreen, int widthScreen) {
		if (bm != null){
	    int width = bm.getWidth();
	    int height = bm.getHeight();
	    float scaleWidth = ((float) widthScreen) / width;
	    float scaleHeight = ((float) heightScreen) / height;
	    // CREATE A MATRIX FOR THE MANIPULATION
	    Matrix matrix = new Matrix();
	    // RESIZE THE BIT MAP
	    matrix.postScale(scaleWidth, scaleHeight);

	    // "RECREATE" THE NEW BITMAP
	    Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		
	    return resizedBitmap;
		}else{return null;}
	}
	
	public static void checkFileAndSet(File f, ImageView im){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(
				f.getAbsolutePath(), options);
		if (options.outWidth != -1 && options.outHeight != -1) {
			im.setVisibility(View.VISIBLE);
			im.setImageBitmap(BitmapFactory.decodeFile(f
					.getAbsolutePath()));
		}
	}
}
