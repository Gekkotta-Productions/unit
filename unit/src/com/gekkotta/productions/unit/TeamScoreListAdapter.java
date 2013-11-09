package com.gekkotta.productions.unit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TeamScoreListAdapter extends BaseAdapter {
    private Activity c;
	private SharedPreferences prefs;
	public int numItems;
	private File file;
	ArrayList projItems;
	
	public TeamScoreListAdapter(Activity a) {
		c = a;
		prefs = PreferenceManager.getDefaultSharedPreferences(c);
		file = new File(c.getDir("data", c.MODE_PRIVATE), "projItemList");
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
			projItems = (ArrayList) inputStream.readObject();
			numItems = projItems.size();
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return numItems;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if(convertView==null){
			vi = c.getLayoutInflater().inflate(R.layout.rank_item, null);
			
		}
		TextView date = (TextView)vi.findViewById(R.id.tv_ts_date);
		date.setText("04/09/2013");
		TextView score = (TextView)vi.findViewById(R.id.tv_score);
		score.setText("9011");
		return vi;
	}

}
