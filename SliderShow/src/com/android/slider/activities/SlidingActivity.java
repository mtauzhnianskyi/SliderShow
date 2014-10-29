package com.android.slider.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.slider.R;
import com.android.slider.utils.ExternalDataSlider;
import com.android.slider.utils.HTTPDataSlider;
import com.android.slider.utils.InternalDataSlider;
import com.android.slider.utils.InternetConnectionUtil;
import com.android.slider.utils.MyThreeClickListener;
import com.android.slider.utils.MyThreeClickListener.OnTripleClickListener;
import com.android.slider.utils.Prefs;

public class SlidingActivity extends Activity implements OnTripleClickListener{

	static boolean active = false;
	
	private ImageView imageView;
	
	private ImageView imageView2;
	
	private int orientation;
	
	private ExternalDataSlider externaltimerTask;
	
	private InternalDataSlider internaltimerTask;
	
	private HTTPDataSlider httpTimerTask;
	
	final Handler handler = new Handler();

	private MyThreeClickListener listener;
	
	private PowerManager powerManager;  
	
	private WakeLock wakeLock;
	
	private OnTouchListener touchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (listener != null){
				listener.onTouch(v, event);
			}
			return false;
		}
	};
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		active = true;
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		active = false;
	}
	
	public static boolean slidingActivityIsOpen() {
		return active;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.slide_show_layout);
		//Get the window from the context
		orientation = getResources().getConfiguration().orientation;
		listener = new MyThreeClickListener();
		listener.setOnTripleClickListener(this);
		imageView = (ImageView) findViewById(R.id.imageView1);
		imageView.setOnTouchListener(touchListener);

		if (orientation == 1){
			imageView2 = (ImageView) findViewById(R.id.imageView2);
			imageView2.setOnTouchListener(touchListener);
		}
		switch (Prefs.getInstance(this).retrieveResourcePref()) {
		case 3:
			if (InternetConnectionUtil.isInternetConnection(this)) {
				runHTTPTimer();
			} else {
				Toast.makeText(
						this,
						getResources().getString(
								R.string.no_internet_connect_msg),
						Toast.LENGTH_LONG).show();
			}
			break;
		case 1:
			runInternalTimer();
			break;
		case 2:
			runExternalTimer();
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		lockScreen();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unlockScrren();
	}
	
	public void runExternalTimer(){
		initializeExternalTimerTask();
		handler.postDelayed(externaltimerTask, 0);
	}
	
	public void runInternalTimer(){
		initializeInternalTimerTask();
		handler.postDelayed(internaltimerTask, 0);
	}
	
	public void runHTTPTimer(){
		initializeHTTPTimerTask();
		handler.postDelayed(httpTimerTask, 0);
	}
	
	public void initializeExternalTimerTask(){
		if (orientation == 1){
			externaltimerTask = new ExternalDataSlider(this, handler, imageView, imageView2);
		}else{
			externaltimerTask = new ExternalDataSlider(this, handler, imageView);
		}
	}
	
	public void initializeInternalTimerTask(){
		if (orientation == 1){
			internaltimerTask = new InternalDataSlider(this, handler, imageView, imageView2);
		}else{
			internaltimerTask = new InternalDataSlider(this, handler, imageView);
		}
	}
	
	public void initializeHTTPTimerTask(){
		if (orientation == 1){
			httpTimerTask = new HTTPDataSlider(this, handler, imageView, imageView2);
		}else{
			httpTimerTask = new HTTPDataSlider(this, handler, imageView);
		}
	}

	@Override
	public void onThreeClick(View view) {
		unlockScrren();
		Toast.makeText(this, "Screen lock released", Toast.LENGTH_LONG).show();
	}
	
	private void lockScreen(){
		powerManager=  ((PowerManager)getSystemService(Context.POWER_SERVICE));
        wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK 
        		| PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
        wakeLock.acquire();
	}
	
	private void unlockScrren(){
		if(wakeLock.isHeld())
        {
              wakeLock.release();
        }
	}
}
