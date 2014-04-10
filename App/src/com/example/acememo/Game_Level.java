package com.example.acememo;

import com.example.acememo.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Game_Level extends Activity{
	
	static int levelNumber;
	static int totalScore ;

	TextView level1Message;
	Button buttonToDecreaseLevel;
	Button buttonToIncreaseLevel;
	Button goToLevel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_level_menu);
		
		totalScore = 0;
		//findViewById(R.id.editText1).set
		level1Message = (TextView) findViewById(R.id.tutorialLevel);
		addListenerOnButton();
	}
	
	public void addListenerOnButton(){
		final Context context = this ;
		
		 final TextView levelString = (TextView) findViewById(R.id.gameLevelText);
		 final String gameLevel = levelString.getText().toString();
		
		 levelNumber = Integer.parseInt((String)gameLevel);
		
		buttonToDecreaseLevel = (Button) findViewById(R.id.button1);
		buttonToDecreaseLevel.setOnClickListener (new OnClickListener() {
			
			
			
			@Override
			public void onClick(View arg0){
				
				levelNumber -- ;
				
				if (levelNumber <= 1){
					levelNumber = 1;
				}
				
				if(levelNumber==1){
					  level1Message.setVisibility(View.VISIBLE);
				  }else{
					  level1Message.setVisibility(View.INVISIBLE);
				  }
				
				String newLevel = Integer.toString(levelNumber); 
				levelString.setText(newLevel);
			}
		});
			
		buttonToIncreaseLevel = (Button) findViewById(R.id.button2);
		buttonToIncreaseLevel.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				  levelNumber ++ ;
				  
				  if (levelNumber >= 10){
						levelNumber = 10;
					}
				  
				  if(levelNumber==1){
					  level1Message.setVisibility(View.VISIBLE);
				  }else{
					  level1Message.setVisibility(View.INVISIBLE);
				  }
				 
				  String newLevel = Integer.toString(levelNumber);
				  levelString.setText(newLevel);
			}
		});
		
		goToLevel = (Button) findViewById(R.id.button3);
		goToLevel.setOnClickListener (new OnClickListener() {
				
				@Override
				public void onClick(View arg0){
					Intent intent = null ;
				intent= new Intent(context,LevelDataFromFacebook.class);
				intent.putExtra("sender", "fromChooser");
					startActivity(intent);
					finish();
				}
		});
	}
	
}