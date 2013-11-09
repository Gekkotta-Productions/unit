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

import com.gekkotta.productions.unit.player.Player;

public class Registration extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);
		
		TextView name, email, ign;
		final TextView warning;
		final EditText ename, eemail, eign;
		Button submit;
		
		warning = (TextView)findViewById(R.id.form_gamename_warning);
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
				String a, b, c;
				a = ename.getText().toString();
				b = eemail.getText().toString();
				c = eign.getText().toString();
				//Player p = new Player(a, b, c);
				/* wait for server information
				if(!p.isUnique(c)){
					warning.setVisibility(TextView.VISIBLE);
				} else {
				*/
					SharedPreferences settings = getSharedPreferences("SaveFile", 0);
					SharedPreferences.Editor editor = settings.edit();
					editor.putString("Name", a);
					editor.putString("Email", b);
					editor.putString("IGN", c);
					editor.putString("TeamName", c);
					editor.commit();
					//p.sendToServer(a, b, c);
					startActivity(new Intent("android.intent.action.CLICKING"));
				//}
			}
		});
	}
	
}
