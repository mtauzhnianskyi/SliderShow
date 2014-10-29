package com.android.slider.utils;

import java.util.Random;

public class HttpURLGeneratorUtil {
	
	// http resource is hardcoded 
	// TODO: implement feature to make user possible to set URL
	public static final String WEB_SOURCE_SITE = "http://tn.new.fishki.net/26/upload/post/201410/27/1320795/";
	
	public static final int MAX_RANBOM_NUM = 40;
	
	public static final int MIN_RANBOM_NUM = 20;

	private String webSourceSite = WEB_SOURCE_SITE;
	
	public HttpURLGeneratorUtil(){
		super();
	}

	public String getWebSourceSite() {
		return webSourceSite;
	}

	public void setWebSourceSite(String webSourceSite) {
		this.webSourceSite = webSourceSite;
	}
	
	public String generateRandomNumberOfURIImage() {
		Random r = new Random();
		int randomImageNum = r.nextInt(MAX_RANBOM_NUM - MIN_RANBOM_NUM) + MIN_RANBOM_NUM;
		StringBuilder sb = new StringBuilder();
		sb.append(webSourceSite);
		sb.append(randomImageNum);
		sb.append(".jpg");
		return sb.toString();
	}

}
