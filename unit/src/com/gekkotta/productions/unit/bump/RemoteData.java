package com.gekkotta.productions.unit.bump;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class RemoteData {

	//Opens up the url connection and applies timeout/invalid url error management
    public static HttpURLConnection getConnection (String url) {
        Log.d("Andrew", "URL: " + url);
        HttpURLConnection hcon = null;
        try {
            hcon = (HttpURLConnection) new URL(url).openConnection();
            hcon.setReadTimeout(30000);
            Log.d("Andrew", "Got connections");
        } catch (MalformedURLException e) {
            Log.e("getConnection()", "Invalid URL: " + e.toString());
        } catch (IOException e) {
            Log.e("getConnection()", "Could not connect: " + e.toString());
        }
        return hcon;
    }

    //Creates a string with all the data extracted from the .json
    public static String readContents(String url) {
    	
    	HttpURLConnection hcon;
    	hcon = getConnection(url);
    	
    	if(hcon == null) {
            return null;
        }
    	try {            
        	StringBuffer sb = new StringBuffer(8192);
            String tmp = "";
            BufferedReader br = new BufferedReader( new InputStreamReader(hcon.getInputStream()));
            while((tmp = br.readLine()) != null) {
                sb.append(tmp).append("\n");
            }
            br.close();
            Log.d("Andrew", sb.toString());
            return sb.toString();
        } catch (IOException e) {
        	Log.d("Andrew", "SWAGGER");
            Log.d("READ FAILED", e.toString());
            return null;
        }
    }
	
}
