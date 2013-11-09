package com.gekkotta.productions.unit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
Button bump;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bump = (Button)findViewById(R.id.b_BUMP);
		bump.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i;
				SharedPreferences settings = getSharedPreferences("SaveFile", Context.MODE_PRIVATE);
				if(!settings.getBoolean("SAVED", false)){
					i = new Intent("android.intent.action.REGISTRATION");
				} else {
					i = new Intent("android.intent.action.CLICKING");
				}
				startActivity(i);
			}
		});
	}
}
