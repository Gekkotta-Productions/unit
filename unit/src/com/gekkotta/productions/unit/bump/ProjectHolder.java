package com.gekkotta.productions.unit.bump;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class ProjectHolder {
	private String raw;
	  private static String URL;
	List<Project> list = null;
	
	public ProjectHolder(String url){
		URL=url;
	}
	public void updateURL(String url){
		URL=url;
	}
	List<Project> fetchProjects(){
		
		readServerContents r = new readServerContents();
		list = new ArrayList<Project>();
		String swag = "";
		
		try {
			swag = r.execute(URL).get();
			Log.d("Andrew", "THIS BITCH GOT WHEELZ: " + swag);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("Andrew", "executed");
		
		try {
			JSONObject data = new JSONObject(swag);
			Log.d("Andrew", "YOLOSWAG THIS BETTER SHOW THE FUCK UP: " + swag);
			JSONArray children = data.getJSONArray("knittens");
			Log.d("Andrew", "LETS HIRE SOME STRIPPERS: " + swag);
			Log.d("Andrew", "made array of length " + children.length());
			for(int i = 0; i < children.length(); i++) {
                JSONObject cur = children.getJSONObject(i);
                Log.d("Andrew", "TIMMY WAS CURED OF CANCER");
                Project p = new Project();
                p.name = cur.optString("name");
                p.description = cur.optString("description");
                p.author = cur.optString("author");
                p.needleType = cur.getString("needleType");
                p.id = cur.getInt("projID");
                p.plength = cur.getInt("length");
                p.needleSize = cur.getInt("needleSize");
                if(p.name != null) {
                    list.add(p);
                    Log.d("Andrew", "added p");
                }
            }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public class readServerContents extends AsyncTask<String, Void ,String>{

		@Override
		protected String doInBackground(String... params) {
			Log.d("Andrew", RemoteData.readContents(URL));
			raw = RemoteData.readContents(URL);
			return RemoteData.readContents(URL);
		}
		
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}
	
	}
}
