package com.gekkotta.productions.unit;

import java.util.concurrent.ExecutionException;

import com.gekkotta.productions.unit.bump.CallServer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
		et = (EditText)findViewById(R.id.tv_team_name);
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
				editor.commit();
			}
		});
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv_hidden.setVisibility(TextView.GONE);
				if(isTeamNameValid(et.getText().toString())){
					SharedPreferences settings = getSharedPreferences("SaveFile", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("TeamName", et.getText().toString());
					editor.commit();
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
			Log.d("Andrew", raw);
			if (raw.contains("false")) {
				return false;
			}
		}
		return true;
	}
	
}