package com.example.acememo;

import com.example.acememo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FacebookLogin extends Activity{
	
	Button allow;
	Button disallow;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebook_login);
		addListenerOnButton();
	}
	
	public void addListenerOnButton(){
		final Context context = this ;
		
		allow = (Button) findViewById(R.id.allow);
		allow.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				MainActivity.screenStatus = 2; 
				Intent mainActivitySyncFacebook = new Intent(context,MainActivity.class);
				mainActivitySyncFacebook.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(mainActivitySyncFacebook);
				finish();
			}
		});
		
		
		disallow = (Button) findViewById(R.id.disallow);
		disallow.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				MainActivity.screenStatus = 1; 
				Intent mainActivityStartGame = new Intent(context,MainActivity.class);
				mainActivityStartGame.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(mainActivityStartGame);
				finish();
			}
		});
		
	}
	
}
