package com.example.acememo;


import com.example.acememo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
			
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		final Context context = this ;
		Button goHome;
		
		goHome = (Button) findViewById(R.id.button1);
		goHome.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent goBack = new Intent(context,Game_Level.class);
				startActivity(goBack);
				finish();
			}
		});
	}
}
