package com.example.acemenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Game_Level extends Activity{
	
	private int levelNumber;
	
	Button buttonToLevelOne;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_level_menu);
		
		//findViewById(R.id.editText1).set
		
		addListenerOnButton();
	}
	
	public void addListenerOnButton(){
		final Context context = this ;
		
		buttonToLevelOne = (Button) findViewById(R.id.button1);
		buttonToLevelOne.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent intent1 = new Intent(context,Level_One.class);
				startActivity(intent1);
			}
		});
		
	}
	
}