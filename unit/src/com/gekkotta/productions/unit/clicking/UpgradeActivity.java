package com.gekkotta.productions.unit.clicking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gekkotta.productions.unit.R;

public class UpgradeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upgrade);
		
		ListView lv = (ListView)findViewById(R.id.upgrade_list);
		
		Integer[] pics = { R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher };
		String[] names = { "Upgrade 1", "Upgrade 2", "Upgrade 3" };
		int[] costs = { 10, 10, 10 };
		
		ArrayList<Upgrade> list = new ArrayList<Upgrade>();
		for(int i = 0; i < names.length; i++){
			list.add(new Upgrade(pics[i], names[i], costs[i]));
		}
		
		CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.upgrade_list_item, list);
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
				// TODO Auto-generated method stub
				//String item = (String) parent.getItemAtPosition(pos);
				Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_LONG).show();
			}
		});
	}
}

class CustomArrayAdapter extends ArrayAdapter<Upgrade> {

	HashMap<Upgrade, Integer> map = new HashMap<Upgrade, Integer>();

	Context context;
	List<Upgrade> upgrades;
	
	public CustomArrayAdapter(Context context, int position, List<Upgrade> upgrades) {
		super(context, position, upgrades);
		for (int i = 0; i < upgrades.size(); i++) {
			map.put(upgrades.get(i), i);
		}
		this.context = context;
		this.upgrades = upgrades;
	}

	public long getItemId(int pos) {
		Upgrade item = getItem(pos);
		return map.get(item);
	}
	
	public View getView(int pos, View cv, ViewGroup p){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rv = inflater.inflate(R.layout.upgrade_list_item, p, false);
		TextView name = (TextView)rv.findViewById(R.id.upgrade_list_name);
		TextView cost = (TextView)rv.findViewById(R.id.upgrade_list_cost);
		ImageView image = (ImageView)rv.findViewById(R.id.upgrade_list_image);
		name.setText(upgrades.get(pos).getName());
		cost.setText(""+upgrades.get(pos).getCost());
		image.setImageResource(upgrades.get(pos).getPic());
		return rv;
	}

}

class Upgrade {

	Integer upgradePic;
	String upgradeName;
	int upgradeCost;

	public Upgrade(Integer pic, String name, int cost) {
		upgradePic = pic;
		upgradeName = name;
		upgradeCost = cost;
	}

	public Integer getPic() {
		return upgradePic;
	}

	public String getName() {
		return upgradeName;
	}

	public int getCost() {
		return upgradeCost;
	}

}