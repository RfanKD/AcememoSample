package com.example.acememo;

import com.example.acememo.R;
import com.example.acememo.HardcodedJSON;
import android.app.Activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


public class LevelDataFromFacebook extends Activity{
	static int level;
	String senderClass;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		final Context context = this ;
		
	    senderClass = getIntent().getStringExtra("sender");
		
		if(senderClass.equals("fromChooser")){
			level = Game_Level.levelNumber;
			
		}else if (senderClass.equals("fromResult"))  {
			level = Result.levelNum + 1;
		}
		
		
	    if(!MainActivity.withFacebook){
			HardcodedJSON hj = new HardcodedJSON(level);
			New_Level.levelData = hj.getGameArray();
			hj.clearData();
			Intent intent = new Intent(context,New_Level.class);
			
			startActivity(intent);
			finish();
		}else{
			final FacebookData fb = new FacebookData(level);
			new Timer().schedule( 
			        new TimerTask() {
			            @Override
			            public void run() {
			                // fb.getResult() here returns a JSONArray.
			            	// you should set up the layout with this, here.
			            	New_Level.levelData = fb.getResult();
			            	Log.d("LikeLog", "RESULT: " + fb.getResult().toString());
			            	
			            	Intent intent = new Intent(context,New_Level.class);
			    			startActivity(intent);
			    			finish();
			        		// System.out.println(level);
			        		//userId = FacebookLogin.user_id;
			        		//System.out.println(userId);
			            }
			        }, 
			        10000
			);
		}
	}
}
