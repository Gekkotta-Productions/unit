package com.gekkotta.productions.unit;

import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gekkotta.productions.unit.server.ServerData;

public class Registration extends Activity {
	
	static String url = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);

		TextView name, email, ign;
		final TextView wname, wemail, wign;
		final EditText ename, eemail, eign;
		Button submit;

		wname = (TextView) findViewById(R.id.form_name_warning);
		wemail = (TextView) findViewById(R.id.form_email_warning);
		wign = (TextView) findViewById(R.id.form_gamename_warning);

		name = (TextView) findViewById(R.id.form_name);
		name.setText("Name:");
		email = (TextView) findViewById(R.id.form_email);
		email.setText("Email:");
		ign = (TextView) findViewById(R.id.form_gamename);
		ign.setText("IGN:");

		ename = (EditText) findViewById(R.id.form_input_name);
		eemail = (EditText) findViewById(R.id.form_input_email);
		eign = (EditText) findViewById(R.id.form_input_gamename);

		submit = (Button) findViewById(R.id.b_submit);

		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String a, b, c;
				a = ename.getText().toString();
				b = eemail.getText().toString();
				c = eign.getText().toString();
				
				wname.setVisibility(TextView.INVISIBLE);
				wemail.setVisibility(TextView.INVISIBLE);
				wign.setVisibility(TextView.INVISIBLE);

				if (!isNameValid(a)) {
					wname.setVisibility(TextView.VISIBLE);
				}
				if (!isEmailValid(b)) {
					wemail.setVisibility(TextView.VISIBLE);
				}
				if (!isIGNValid(c)) {
					wign.setVisibility(TextView.VISIBLE);
				}
				if (isNameValid(a) && isEmailValid(b) && isIGNValid(c)) {

					String url = ("http://172.26.13.13/unit/newuser.php?");
					url = url + "IGname=" + c + "&name=" + a + "&email=" + b;
					
					CallServer cs = new CallServer();
					String raw = "";
					try {
						raw = cs.execute(url).get();
					} catch (InterruptedException e) {
					} catch (ExecutionException e) {
					}
					SharedPreferences settings = getSharedPreferences(
							"SaveFile", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = settings.edit();
					editor.putBoolean("SAVED", true);
					editor.putString("Name", a);
					editor.putString("Email", b);
					editor.putString("IGN", c);
					editor.putString("TeamName", c);
					editor.putString("TeamId", "0");
					editor.commit();
					startActivity(new Intent("android.intent.action.CLICKING"));
				}
			}
		});
	}

	public static boolean isEmailValid(String email) {
		if(email.isEmpty()){
			return false;
		} else {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			CharSequence inputStr = email;
	
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(inputStr);
			if (!matcher.matches()) {
				return false;
			} else {
				CallServer cs = new CallServer();
				url = ("http://172.26.13.13/unit/unique.php?q=" + email + "&f=email&table=Players");
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
		}
		return true;
	}

	public static boolean isNameValid(String name) {
		if (name.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean isIGNValid(String IGN) {
		if(IGN.isEmpty()){
			return false;
		} else {
			CallServer cs = new CallServer();
			url = ("http://172.26.13.13/unit/unique.php?q=" + IGN + "&f=IGname&table=Players");
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

class CallServer extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		String strang = ServerData.readContents(params[0]);
		Log.d("Andrew", strang);
		return strang;
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}
}
