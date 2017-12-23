package com.stavinapp.fattypunch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.stavinapp.fattypunch.MESSAGE";
	private static final String PREFS_KEY = "PREFS_KEY";
	public static final String CURRENT_KEY = "CURRENT_KEY";
	
	public int mCurrent;
	TextView mComboBox;
	TextView mPunchBox;
	String[] combNames;
	String[] combNumbs;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    	
    	combNames = getResources().getStringArray(R.array.comb_names);
    	combNumbs = getResources().getStringArray(R.array.comb_numbers);
    	mComboBox = (TextView) findViewById(R.id.comboNames);
        mPunchBox =  (TextView) findViewById(R.id.comboNums);
		mPunchBox.setOnLongClickListener(new OnLongClickListener() { 
										@Override
										public boolean onLongClick(View v) {
											processClicks(v);
										return false;}});
 
        
        SharedPreferences prefs = getSharedPreferences(PREFS_KEY, Activity.MODE_PRIVATE);
        mCurrent = prefs.getInt(CURRENT_KEY, -1);
        if (mCurrent>-1) setCombo();
       }	
    


    
    
    public void processClicks(View v){
    	switch(v.getId()){
    	case R.id.back: prevCombo();	break;
    	case R.id.shuffle: randomCombo();	break;
    	case R.id.forward: nextCombo(); break;
    	case R.id.comboNums: 
    		Intent intent = new Intent(this, Describethecombo.class);
    		intent.putExtra("position",mCurrent);
    		startActivity(intent);
    		break;
    	default: break;}
    	}
    


    public void prevCombo(){
    	if (mCurrent>0)mCurrent--; else mCurrent=6;
    	    mComboBox.setText(combNames[mCurrent]);
    	    mPunchBox.setText(combNumbs[mCurrent]);
    }

    public void randomCombo(){
    	mCurrent = (int)(Math.random()*7);
	    mComboBox.setText(combNames[mCurrent]);
	    mPunchBox.setText(combNumbs[mCurrent]);
    }

    public void nextCombo(){
    		if (mCurrent<6)mCurrent++; else mCurrent=0;
    	    mComboBox.setText(combNames[mCurrent]);
    	    mPunchBox.setText(combNumbs[mCurrent]);
    	}
    
	public void setCombo(){
    	mComboBox.setText(combNames[mCurrent]);
	    mPunchBox.setText(combNumbs[mCurrent]);
	    }
    
	@Override
	protected void onPause() {
		super.onPause();
		
		SharedPreferences prefs = getSharedPreferences(PREFS_KEY, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(CURRENT_KEY, this.mCurrent);
		editor.commit();
	}

   

}
