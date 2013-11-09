package com.gekkotta.productions.unit;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class TeamScore extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_score);
		ListView lv = (ListView) findViewById(R.id.lv_ts_history);
		lv.setAdapter(new TeamScoreListAdapter(this));
	}
}
