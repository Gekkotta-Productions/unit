package com.gekkotta.productions.unit.clicking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.gekkotta.productions.unit.R;

public class ClickingActivity extends Activity{

	int score = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clicking);
		
		Button bumpButton = (Button) findViewById(R.id.b_bump);
		Button clickButton = (Button) findViewById(R.id.b_click);
		TextView teamName = (TextView) findViewById(R.id.tv_team_name);
		TextView playerScore = (TextView) findViewById(R.id.tv_score);
		//Get the team name from a cache or something, this way the user does not need to be online
		//String name = cache.get();
		//teamName.setText(name);
		teamName.setText("unit.");
		
		//Get the score from cache of where they left off last time
		//int score = cache.get();
		//playerScore.setText(score);
		playerScore.setText(score);
		
		bumpButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				startActivity(i);
			}
		});
		
		clickButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				score++;
			}
		});
	}
	
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

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

}