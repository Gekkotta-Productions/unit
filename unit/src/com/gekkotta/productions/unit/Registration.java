package com.gekkotta.productions.unit;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Registration extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);
		
		ListView listView = (ListView) findViewById(R.layout.registration_screen);
		String[] values = { "Name", "Email", "GamerID" };
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0; i < values.length; i++){
			list.add(values[i]);
		}
		
		ArrayAdapter adapter = new ArrayAdapter(this, R.layout.registration_rows, list);
		listView.setAdapter(adapter);
	}
	
}
