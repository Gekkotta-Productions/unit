package com.gekkotta.productions.unit.clicking;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.gekkotta.productions.unit.R;

public class ClickingActivity extends Activity{

	int score = 0;
	Button bumpButton, clickButton, achievementButton, upgradeButton;
	TextView teamName, playerScore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clicking);
		SharedPreferences setting = getSharedPreferences("SaveFile", 0);
		
		bumpButton = (Button) findViewById(R.id.b_bump);
		clickButton = (Button) findViewById(R.id.b_click);
		achievementButton = (Button) findViewById(R.id.b_achievement);
		upgradeButton = (Button) findViewById(R.id.b_upgrade);
		teamName = (TextView) findViewById(R.id.tv_team_name);
		playerScore = (TextView) findViewById(R.id.tv_score);
		//Get the team name from a cache or something, this way the user does not need to be online
		String name = setting.getString("TeamName", "unit.");
		teamName.setText(name);
		
		//Get the score from cache of where they left off last time
		score = setting.getInt("Score", 0);
		playerScore.setText(""+score);		
		
		bumpButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("android.intent.action.BUMP");
				startActivity(i);
			}
		});
		
		clickButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				score++;
				playerScore.setText(""+score);
			}
		});
		
		achievementButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("android.intent.action.ACHIEVEMENT");
				startActivity(i);
			}
		});
		
		upgradeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent("android.intent.action.UPGRADE");
				startActivity(i);
			}
		});
		
	}
	
	/*
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	*/

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		SharedPreferences setting = getSharedPreferences("SaveFile", 0);
		SharedPreferences.Editor editor = setting.edit();
		editor.putInt("score", score);
		editor.commit();
	}
}