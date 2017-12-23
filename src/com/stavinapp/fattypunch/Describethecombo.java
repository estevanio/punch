package com.stavinapp.fattypunch;

import com.stavinapp.fattypunch.R;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class Describethecombo extends Activity {
	
	String[] combDescript;
	TextView showUp;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.combodescriptor);
		Bundle extras = getIntent().getExtras();
		int position = extras.getInt("position");
		showUp=(TextView) findViewById(R.id.boxbox);
		combDescript=getResources().getStringArray(R.array.comb_d);
		showUp.setText(combDescript[position]);
    }
}