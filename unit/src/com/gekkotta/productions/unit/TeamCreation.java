package com.gekkotta.productions.unit;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gekkotta.productions.unit.server.ServerData;

public class TeamCreation extends Activity {

	TextView tv, tv_hidden;
	EditText et;
	Button solo, confirm;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_creation);
		
		tv = (TextView)findViewById(R.id.instructions);
		tv_hidden = (TextView)findViewById(R.id.name_warning);
		et = (EditText)findViewById(R.id.et_team_name);
		Typeface tf = Typeface.createFromAsset(getAssets(), "roboto.ttf");
		tv.setTypeface(tf);
		
		solo = (Button)findViewById(R.id.solo);
		confirm = (Button)findViewById(R.id.confirm);
		
		solo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences settings = getSharedPreferences("SaveFile", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("TeamName", "");
				editor.putString("TeamId", "0");
				editor.commit();
				Intent i = new Intent("android.intent.action.CLICKING");
				startActivity(i);
			}
		});
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv_hidden.setVisibility(TextView.GONE);
				if(isTeamNameValid(et.getText().toString())){
					SharedPreferences settings = getSharedPreferences("SaveFile", Context.MODE_PRIVATE);
					CallServer cs = new CallServer();
					String url = ("http://172.26.13.13/unit/newTeam.php?teamname=" + et.getText().toString() + "&IGname=" + settings.getString("IGN", "-1"));
					String test = "";
					try {
						test = cs.execute(url).get();
						Log.d("Trial Run: ", test);
					} catch (InterruptedException e) {
					} catch (ExecutionException e) {
					}
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("TeamName", et.getText().toString());
					editor.putString("TeamId", test);
					editor.commit();
					Intent i = new Intent("android.intent.action.CLICKING");
					startActivity(i);
				} else {
					tv_hidden.setVisibility(TextView.VISIBLE);
				}
			}
		});
	}

	public boolean isTeamNameValid(String name){
		if (name.isEmpty()) {
			return false;
		} else {
			CallServer cs = new CallServer();
			String url = ("http://172.26.13.13/unit/unique.php?q=" + name + "&f=name&table=Teams");
			String raw = "";
			try {
				raw = cs.execute(url).get();
			} catch (InterruptedException e) {
			} catch (ExecutionException e) {
			}
			Log.d("", raw);
			if (raw.contains("false")) {
				return false;
			}
		}
		return true;
	}
	class CallServer extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String val = ServerData.readContents(params[0]);
			Log.d("Team Val: ", val);
			return val;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
		}

	}
	
}
