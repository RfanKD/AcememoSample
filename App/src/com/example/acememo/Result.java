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
	private int userScore ;
	
	Button goHome;
	Button reviewAnswer;
	TextView oopsMessage;
	TextView endGameMessage;
	String senderClass;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		goHome = (Button) findViewById(R.id.button1);
		reviewAnswer = (Button) findViewById(R.id.button2);
		
		userScore = New_Level.levelScore;
		
		oopsMessage = (TextView) findViewById(R.id.oopsText);
		oopsMessage.setText(userScore);
		endGameMessage = (TextView) findViewById(R.id.pairsText);
		senderClass = getIntent().getStringExtra("sender");
//		if(senderClass.equals("reviewed")){
//			oopsMessage.setText("");
//			endGameMessage.setX(oopsMessage.getX());
//			endGameMessage.setY(oopsMessage.getY());
//		}
		
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		final Context context = this ;
				
		goHome.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				MainActivity.screenStatus = 3; 
				Intent mainActivitySyncFacebook = new Intent(context,MainActivity.class);
				mainActivitySyncFacebook.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(mainActivitySyncFacebook);
				finish();
			}
		});
		
		reviewAnswer.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent reviewAnswer = new Intent(context,reviewAnswer.class);
				//mainActivitySyncFacebook.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(reviewAnswer);
				finish();
			}
		});
	}
}
