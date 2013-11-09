package com.gekkotta.productions.unit.clicking;

import com.gekkotta.productions.unit.R;

import android.app.Activity;
import android.os.Bundle;

public class UpgradeActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upgrade);
	}

}

class Upgrade{
	
	Integer upgradePic;
	String upgradeName;
	int upgradeCost;
	
	public Upgrade(Integer pic, String name, int cost){
		upgradePic = pic;
		upgradeName = name;
		upgradeCost = cost;
	}
	
	public Integer getPic(){
		return upgradePic;
	}
	
	public String getName(){
		return upgradeName;
	}
	
	public int getCost(){
		return upgradeCost;
	}
	
}