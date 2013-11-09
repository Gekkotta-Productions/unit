package com.gekkotta.productions.unit.bump;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bump.api.BumpAPIIntents;
import com.bump.api.IBumpAPI;
import com.gekkotta.productions.unit.R;

public class BumpActivity extends Activity {
	TextView status;
	CheckBox cb;
	boolean bumping = false;
	private IBumpAPI api;
	private String PLAYERID;
	private int TEAMID;
	private final String key = "98883206c8c647a0bc9c4190a668a40b";
	private IntentFilter filter;

	private final ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName className, IBinder binder) {

			Log.i("BumpTest", "onServiceConnected");
			api = IBumpAPI.Stub.asInterface(binder);
			new Thread() {
				public void run() {
					try {
						api.configure(key, "Andy");
					} catch (RemoteException e) {
						Log.w("BumpTest", e);
					}

				}
			}.start();

			Log.d("Bump Test", "Service connected");
		}

		@Override
		public void onServiceDisconnected(ComponentName className) {
			Log.d("Bump Test", "Service disconnected");
		}
	};

	private final BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			final String action = intent.getAction();
			try {
				if (action.equals(BumpAPIIntents.DATA_RECEIVED)) {
					int otherID = Integer.parseInt(new String(intent
							.getByteArrayExtra("data")));
					Log.i("Bump Test",
							"Received data from: "
									+ api.userIDForChannelID(intent
											.getLongExtra("channelID", 0)));
					Log.i("Bump Test",
							"Data: "
									+ new String(intent
											.getByteArrayExtra("data")));
					//No Team - No Team: Lower ID goes first, ask to make team
					//TODO implement getTeamID 
					if(getTeamID(otherID)==0 && TEAMID==0 && otherID > Integer.parseInt(PLAYERID)){
						// custom dialog
						LayoutInflater inflater = (LayoutInflater)context.getSystemService
							      (Context.LAYOUT_INFLATER_SERVICE);
					    final View deleteDialogView = inflater.inflate(
					            R.layout.dialog_new_team, null);
					    final AlertDialog deleteDialog = new AlertDialog.Builder(context).create();
					    deleteDialog.setView(deleteDialogView);
					    deleteDialogView.findViewById(R.id.b_d_ok).setOnClickListener(new OnClickListener() {

					        @Override
					        public void onClick(View v) {
					            EditText et = (EditText) deleteDialogView.findViewById(R.id.et_d_team_name);
					            String teamName = et.getText().toString(); 
					            if(isEmailValid(teamName)){
					            	
					            	//if(nameUnique){
					            	//TODO Upload to Server and store
					            	//}
					            	deleteDialog.dismiss();
					            }
					            else{
					            	Toast.makeText(v.getContext(),"Invalid Name", Toast.LENGTH_LONG).show();
					            }
					            
					        }
					    });
					    deleteDialogView.findViewById(R.id.b_d_cancel).setOnClickListener(new OnClickListener() {

					        @Override
					        public void onClick(View v) {
					            deleteDialog.dismiss();
					        }
					    });
					    deleteDialog.show();
					}
					
					//Team - No Team: NoTeam is asked if they want to join Team's team
					else if(TEAMID==0 && getTeamID(otherID)!=0){
						
					}
					//Same Team - Same Team: + Points
					else if(TEAMID==getTeamID(otherID)){
						
					}
					//Different Team but Same Faction: + MORE Points
					
					//Different teams completely: Display Name and error message 
					else{
						
					}
				} 
				/*
				else if (action.equals(BumpAPIIntents.MATCHED)) {
					long channelID = intent
							.getLongExtra("proposedChannelID", 0);
					Log.i("Bump Test",
							"Matched with: "
									+ api.userIDForChannelID(channelID));
					api.confirm(channelID, true);
					Log.i("Bump Test", "Confirm sent");
					}
					*/
				else if (action.equals(BumpAPIIntents.CHANNEL_CONFIRMED)) {
					// SENDING DATA
					long channelID = intent.getLongExtra("channelID", 0);
					Log.i("Bump Test",
							"Channel confirmed with "
									+ api.userIDForChannelID(channelID));
					status.setText("Success!");
					api.send(channelID, PLAYERID.getBytes());

				} 
				
				else if (action.equals(BumpAPIIntents.NOT_MATCHED)) {
					Log.i("Bump Test", "Not matched.");
					status.setText("Please try again");
				} 
				/*else if (action.equals(BumpAPIIntents.CONNECTED)) {
					Log.i("Bump Test", "Connected to Bump...");
					api.enableBumping();
				}*/
			} catch (RemoteException e) {
			}
		}
	};
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bump);

		status = (TextView) findViewById(R.id.tv_result);
		cb = (CheckBox) findViewById(R.id.cb_status);
		cb.setClickable(false);
		cb.setFocusable(false);

		Log.i("BumpTest", "boot");
		PLAYERID = "1"; //Get ID from Shared Pref or Server
		filter = new IntentFilter();
		filter.addAction(BumpAPIIntents.CHANNEL_CONFIRMED);
		filter.addAction(BumpAPIIntents.DATA_RECEIVED);
		filter.addAction(BumpAPIIntents.NOT_MATCHED);
		setConnections();
		bumping = true;
		cb.setChecked(true);
		cb.setText("Ready To Bump");
		status.setText("Awaiting Bump");
	}

	protected int getTeamID(int otherID) {
		// TODO Auto-generated method stub
		return 0;
	}


	public void onResume() {
		Log.i("BumpTest", "onResume");
		super.onResume();
		if (!bumping) {
			try {
				api.enableBumping();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bumping = true;
		}

	}

	public void onPause() {
		Log.i("BumpTest", "onPause");
		if (bumping) {
			try {
				api.disableBumping();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bumping = false;
		}
		super.onPause();

	}

	public void onStop() {
		Log.i("BumpTest", "onStop");
		if (bumping) {
			try {
				api.disableBumping();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bumping = false;
		}
		super.onStop();
	}

	public void onDestroy() {
		Log.i("BumpTest", "onDestroy");
		unbindService(connection);
		unregisterReceiver(receiver);
		// super.onDestroy();
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		onDestroy();
	}

	public void setConnections() {
		bindService(new Intent(IBumpAPI.class.getName()), connection,
				Context.BIND_AUTO_CREATE);
		registerReceiver(receiver, filter);

	}
	
	public static boolean isEmailValid(String email) {
	    boolean isValid = false;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
	}

}
