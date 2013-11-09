package com.gekkotta.productions.unit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registration extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);
		
		TextView name, email, ign;
		final EditText ename, eemail, eign;
		Button submit;
		
		name = (TextView)findViewById(R.id.form_name);
		name.setText("Name:");
		email = (TextView)findViewById(R.id.form_email);
		email.setText("Email:");
		ign = (TextView)findViewById(R.id.form_gamename);
		ign.setText("IGN:");
		
		ename = (EditText)findViewById(R.id.form_input_name);
		eemail = (EditText)findViewById(R.id.form_input_email);
		eign = (EditText)findViewById(R.id.form_input_gamename);
		
		submit = (Button)findViewById(R.id.b_submit);
		
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//check server for ign
				//if not taken, this
				//else, give warning and leave
				SharedPreferences settings = getSharedPreferences("SaveFile", 0);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString("Name", ename.getText().toString());
				editor.putString("Email", eemail.getText().toString());
				editor.putString("IGN", eign.getText().toString());
				editor.putString("TeamName", eign.getText().toString());
				editor.commit();
				startActivity(new Intent("android.intent.action.CLICKING"));
			}
		});
	}
	
}
