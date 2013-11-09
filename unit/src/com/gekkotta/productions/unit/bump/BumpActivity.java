package com.gekkotta.productions.unit.bump;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bump.api.BumpAPIIntents;
import com.bump.api.IBumpAPI;
import com.gekkotta.productions.unit.R;

public class BumpActivity extends Activity {
	TextView status;
	CheckBox cb;
	private IBumpAPI api;
	private String PLAYERID;
	private final String key = "98883206c8c647a0bc9c4190a668a40b";
	private final ServiceConnection connection = new ServiceConnection() {
	    @Override
	    public void onServiceConnected(ComponentName className, IBinder binder) {

	        Log.i("BumpTest", "onServiceConnected");
	        api = IBumpAPI.Stub.asInterface(binder);
	        new Thread() {
	            public void run() {
	                try {
	                    api.configure(key,
	                            "Andy");
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
					Log.i("Bump Test",
							"Received data from: "
									+ api.userIDForChannelID(intent
											.getLongExtra("channelID", 0)));
					Log.i("Bump Test",
							"Data: "
									+ new String(intent
											.getByteArrayExtra("data")));
				} else if (action.equals(BumpAPIIntents.MATCHED)) {
					long channelID = intent
							.getLongExtra("proposedChannelID", 0);
					Log.i("Bump Test",
							"Matched with: "
									+ api.userIDForChannelID(channelID));
					api.confirm(channelID, true);
					Log.i("Bump Test", "Confirm sent");
				} else if (action.equals(BumpAPIIntents.CHANNEL_CONFIRMED)) {
					// SENDING DATA
					long channelID = intent.getLongExtra("channelID", 0);
					Log.i("Bump Test",
							"Channel confirmed with "
									+ api.userIDForChannelID(channelID));
					api.send(channelID, PLAYERID.getBytes());
				} else if (action.equals(BumpAPIIntents.NOT_MATCHED)) {
					Log.i("Bump Test", "Not matched.");
				} else if (action.equals(BumpAPIIntents.CONNECTED)) {
					Log.i("Bump Test", "Connected to Bump...");
					api.enableBumping();
				}
			} catch (RemoteException e) {
			}
		}
	};

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bump);
		
		status = (TextView) findViewById(R.id.tv_result);
		cb = (CheckBox) findViewById(R.id.cb_status);
		cb.setClickable(false);
		cb.setFocusable(false);
		bindService(new Intent(IBumpAPI.class.getName()), connection,
				Context.BIND_AUTO_CREATE);
	
		Log.i("BumpTest", "boot");
		PLAYERID = "1"; //
		IntentFilter filter = new IntentFilter();
		filter.addAction(BumpAPIIntents.CHANNEL_CONFIRMED);
		filter.addAction(BumpAPIIntents.DATA_RECEIVED);
		filter.addAction(BumpAPIIntents.NOT_MATCHED);
		filter.addAction(BumpAPIIntents.MATCHED);
		filter.addAction(BumpAPIIntents.CONNECTED);
		registerReceiver(receiver, filter);
		cb.setChecked(true);
		cb.setText("Ready To Bump");
		status.setText("Awaiting Bump");
	}

	public void onStart() {
		Log.i("BumpTest", "onStart");
		super.onStart();
	}

	public void onRestart() {
		Log.i("BumpTest", "onRestart");
		super.onRestart();
	}

	public void onResume() {
		Log.i("BumpTest", "onResume");
		super.onResume();
	}

	public void onPause() {
		Log.i("BumpTest", "onPause");
		super.onPause();
	}

	public void onStop() {
		Log.i("BumpTest", "onStop");
		super.onStop();
	}

	public void onDestroy() {
		Log.i("BumpTest", "onDestroy");
		unbindService(connection);
		unregisterReceiver(receiver);
		super.onDestroy();
	}

}
