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
	
	private int levelNumber;

	
	Button buttonToDecreaseLevel;
	Button buttonToIncreaseLevel;
	Button goToLevel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_level_menu);
		
		//findViewById(R.id.editText1).set
		
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
				
				if (levelNumber <= 0){
					levelNumber = 0;
				}
				
				String newLevel = Integer.toString(levelNumber); 
				levelString.setText(newLevel);
			}
		});
			
		buttonToDecreaseLevel = (Button) findViewById(R.id.button2);
		buttonToDecreaseLevel.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				  levelNumber ++ ;
				 
				  String newLevel = Integer.toString(levelNumber);
				  levelString.setText(newLevel);
			}
		});
		
		goToLevel = (Button) findViewById(R.id.button3);
		goToLevel.setOnClickListener (new OnClickListener() {
				
				@Override
				public void onClick(View arg0){
					Intent intent = null ;
					
					switch(levelNumber){
						case 1: intent= new Intent(context,Level_One.class);
								break;
						default: intent = new Intent(context,MainActivity.class);
					}
					startActivity(intent);
				}
		});
	}
	
}