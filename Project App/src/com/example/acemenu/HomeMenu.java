package com.example.acemenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeMenu extends Activity{
	
	Button buttonToGameMenu;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_menu);
	}
	
	public void addListenerOnButton(){
		final Context context = this ;
		
		buttonToGameMenu = (Button) findViewById(R.id.button1);
		buttonToGameMenu.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent intent1 = new Intent(context,Game_Level.class);
				startActivity(intent1);
			}
		});
		
	}
	
}
