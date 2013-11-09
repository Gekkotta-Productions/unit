package com.gekkotta.productions.unit.bump;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bump.api.IBumpAPI;
import com.bump.api.BumpAPIIntents;
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
		status = (TextView)(findViewById(R.id.tv_status));
		cb = (CheckBox)findViewById(R.id.cb_status);
		bindService(new Intent(IBumpAPI.class.getName()),
	            connection, 
	            Context.BIND_AUTO_CREATE);
	}
	private final ServiceConnection connection = new ServiceConnection() {
	    @Override
	    public void onServiceConnected(ComponentName className, IBinder binder) {
	        api = IBumpAPI.Stub.asInterface(binder);
	  
	        try {
	            api.configure("98883206c8c647a0bc9c4190a668a40b", "Bump User");
	         
	        } catch (RemoteException e) {}
	    }

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
	};
}
