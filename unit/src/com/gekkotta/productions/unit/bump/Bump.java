package com.gekkotta.productions.unit.bump;

import android.app.Activity;
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
import android.widget.CheckBox;
import android.widget.TextView;

import com.bump.api.BumpAPIIntents;
import com.bump.api.IBumpAPI;
import com.gekkotta.productions.unit.R;

public class Bump extends Activity {
	TextView status;
	CheckBox cb;
	private IBumpAPI api;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bump);
		status = (TextView) (findViewById(R.id.tv_status));
		cb = (CheckBox) findViewById(R.id.cb_status);
		bindService(new Intent(IBumpAPI.class.getName()), connection,
				Context.BIND_AUTO_CREATE);

		IntentFilter filter = new IntentFilter();
		filter.addAction(BumpAPIIntents.CHANNEL_CONFIRMED);
		filter.addAction(BumpAPIIntents.DATA_RECEIVED);
		filter.addAction(BumpAPIIntents.NOT_MATCHED);
		filter.addAction(BumpAPIIntents.MATCHED);
		filter.addAction(BumpAPIIntents.CONNECTED);
		registerReceiver(receiver, filter);
	}

	private final ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName className, IBinder binder) {
			api = IBumpAPI.Stub.asInterface(binder);

			try {
				api.configure("98883206c8c647a0bc9c4190a668a40b", "Bump User");

			} catch (RemoteException e) {
			}
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

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
					api.confirm(intent.getLongExtra("proposedChannelID", 0),
							true);
				} else if (action.equals(BumpAPIIntents.CHANNEL_CONFIRMED)) {
					api.send(intent.getLongExtra("channelID", 0),
							"Hello, world!".getBytes());
				} else if (action.equals(BumpAPIIntents.CONNECTED)) {
					api.enableBumping();
				}
			} catch (RemoteException e) {
			}
		}
	};

}
