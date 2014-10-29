package com.android.slider.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class DownloadImagesUtil extends AsyncTask<ImageView, Void, Bitmap>{
	
	private ImageView imageView = null;
	
	private Context context;
	
	boolean isPortrait;
	
	public DownloadImagesUtil(Context context, boolean isPortrait){
		this.context = context;
		this.isPortrait = isPortrait;
	}
	
	@Override
	protected Bitmap doInBackground(ImageView... params) {
		imageView = params[0];
		Bitmap bmp = null;
		ByteArrayBuffer baf = new ByteArrayBuffer(1024);
		String IMAGE_URL = (String)imageView.getTag();
        //where we want to download it from
        URL url;
        try {
            url = new URL(IMAGE_URL);

            //open the connection
            URLConnection ucon = null;
			try {
				ucon = url.openConnection();
			} catch (IOException e2) {
				e2.printStackTrace();
			}

            //buffer the download
            InputStream is = null;
			try {
				is = ucon.getInputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            BufferedInputStream bis = new BufferedInputStream(is,1024);

            //get the bytes one by one
            int current = 0;

            try {
				while ((current = bis.read()) != -1) {
				    baf.append((byte) current);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
            //convert it back to an image
            ByteArrayInputStream imageStream = new ByteArrayInputStream(baf.toByteArray());
            bmp = BitmapFactory.decodeStream(imageStream);
		
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		return bmp;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		result = new ImageUtil(context).getResizedBitmapFromBitmap(result, isPortrait);
		
		new AnimationUtil(context, imageView, result).doAnimation();
	}
}
