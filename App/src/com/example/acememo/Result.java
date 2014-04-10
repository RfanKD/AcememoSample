package com.example.acememo;


import java.io.FileNotFoundException;

import com.example.acememo.R;
import com.example.acememo.BestScoreFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends Activity{
	private int currentUserScore ;
	private int totalUserScore;
	private int currentBestScore;
	static int levelNum;
	
	private Button goHome;
	private Button reviewAnswer;
	private Button nextLevel;
	private TextView totalScore;
	private TextView currentScore;
	private TextView bestScore;
	private TextView bestBack;
	private TextView Result;
	private String senderClass;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		goHome = (Button)findViewById(R.id.button1);
		reviewAnswer = (Button)findViewById(R.id.button2);
		nextLevel = (Button)findViewById(R.id.button3);
		nextLevel.setVisibility(View.INVISIBLE);
		bestBack = (TextView) findViewById(R.id.bestBack);
		BestScoreFile bsf = new BestScoreFile();
		
	currentUserScore = New_Level.levelScore;
	totalUserScore = Game_Level.totalScore;
	levelNum = New_Level.level;
	
	currentBestScore = bsf.readFile();
	totalScore = (TextView) findViewById(R.id.totalNumber);
	totalScore.setText("" + totalUserScore);
	
	bestScore = (TextView) findViewById(R.id.bestNumber);
	
	if(currentBestScore >= totalUserScore){
		bestScore.setText(""  + currentBestScore);
		bestBack.setBackgroundColor(getResources().getColor(R.color.darkgray));
	}else{
		bestScore.setText("" + totalUserScore);
		bestBack.setBackgroundColor(getResources().getColor(R.color.bluey));
		currentBestScore = totalUserScore;
		try {
			bsf.writeToFile(currentBestScore);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	currentScore = (TextView) findViewById(R.id.currentNumber);
	currentScore.setText("" + currentUserScore);
	
	Log.d("Score", "BEST SCORE: " + currentBestScore);
	Log.d("Score", "TOTAL SCORE: " + totalUserScore);
	Log.d("Score", "CURRENT SCORE: " + currentUserScore);
	
	Result = (TextView) findViewById(R.id.resultText);
	
	if (levelNum == 1){
		if(currentUserScore == 0){
			Result.setTextColor(getResources().getColor(R.color.red));
			Result.setText("There are too many errors!" + System.getProperty("line.separator")+ "Try Again!");
		}else{
			nextLevel.setVisibility(View.VISIBLE);
			Result.setTextColor(getResources().getColor(R.color.green));
			Result.setText("Congratulations!" +System.getProperty("line.separator")+"You passed level " + levelNum);
		}
	}else{
		if(currentUserScore < (levelNum/2)){
			Result.setTextColor(getResources().getColor(R.color.red));
			Result.setText("There are too many errors!" + System.getProperty("line.separator")+ "Try Again!");
		}else{
			nextLevel.setVisibility(View.VISIBLE);
			Result.setTextColor(getResources().getColor(R.color.green));
			Result.setText("Congratulations!" +System.getProperty("line.separator")+"You passed level " + levelNum);
		}
	}
	
	
	
//		oopsMessage.setText(userScore);
//		endGameMessage = (TextView) findViewById(R.id.pairsText);
//		senderClass = getIntent().getStringExtra("sender");
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
				//MainActivity.screenStatus = 3; 
				totalUserScore = 0 ;
				
				Intent mainActivitySyncFacebook = new Intent(context,Game_Level.class);
				
				//mainActivitySyncFacebook.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(mainActivitySyncFacebook);
				finish();
			}
		});
		
		nextLevel.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				//MainActivity.screenStatus = 3; 
				Intent nextLevel = new Intent(context,LevelDataFromFacebook.class);
				//mainActivitySyncFacebook.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				nextLevel.putExtra("sender", "fromResult");
				startActivity(nextLevel);
				finish();
			}
		});
		
		reviewAnswer.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent reviewAnswer = new Intent(); 
				//context,New_Level.class
				//mainActivitySyncFacebook.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				setResult(RESULT_OK,reviewAnswer);
				 finish();
			}
		});
	}
}
