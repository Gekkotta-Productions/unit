package com.gekkotta.productions.unit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.util.Log;

public class ServerData {
	
	public static String readContents(String url) {
		HttpURLConnection httpcon;
		httpcon = getConnection(url);
		
		if(httpcon == null){
			Log.d("Andrew", "Null connection");
			return null;
		}
		
		try {
			StringBuffer sb = new StringBuffer(8192);
			String in = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream()));
			while((in = br.readLine()) != null) {
				sb.append(in).append("\n");
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static HttpURLConnection getConnection(String url) {
		HttpURLConnection httpcon = null;
		
		try {
			Log.d("Andrew", url);
			httpcon = (HttpURLConnection) new URL(url).openConnection();
			httpcon.setReadTimeout(30000);
		} catch (MalformedURLException e) {
			Log.e("getConnection()", "Invalid URL: " + e.toString());
		} catch (IOException e) {
			Log.e("getConnection()", "Could not connect: " + e.toString());
		}
		
		return httpcon;
	}
	
}
