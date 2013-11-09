package com.gekkotta.productions.unit.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ServerData {
	
	public static String readContents(String url) {
		HttpURLConnection httpcon;
		httpcon = getConnection(url);
		
		if(httpcon == null){
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
			httpcon = (HttpURLConnection) new URL(url).openConnection();
			httpcon.setReadTimeout(30000);
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		
		return httpcon;
	}
	
}
