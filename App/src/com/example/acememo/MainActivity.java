package com.example.acememo;

import com.example.acememo.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {
	final static int NEW = 1;
	final static int LOADING = 2;
	final static int START = 3; 
	
	Button buttonToHome;
	Button startGame;
	ProgressBar loadingAccount;
	static int screenStatus = NEW;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setView();
		addListenerOnButton();
	}
	
	public void setView(){
		buttonToHome = (Button) findViewById(R.id.facebookLoginButton);
		startGame = (Button) findViewById(R.id.startGame);
		loadingAccount = (ProgressBar) findViewById(R.id.facebookSync);
		
		buttonToHome.setVisibility(View.INVISIBLE);
		startGame.setVisibility(View.INVISIBLE);
		loadingAccount.setVisibility(View.INVISIBLE);
		
		if(screenStatus == NEW){
			buttonToHome.setVisibility(View.VISIBLE);
		}else if(screenStatus == LOADING){
			loadingAccount.setVisibility(View.VISIBLE);
			timerDelayRemoveProgressBar(3600, loadingAccount);
			screenStatus = START;
		}else if(screenStatus == START){
			startGame.setVisibility(View.VISIBLE);
		}
	}
	
	public void timerDelayRemoveProgressBar(long time, final ProgressBar pb){
	    Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() {           
	        public void run() {                
	            pb.setVisibility(View.INVISIBLE);   
	            startGame.setVisibility(View.VISIBLE);
	        }
	    }, time); 
	}

	public void addListenerOnButton(){
		final Context context = this ;
		
		buttonToHome = (Button) findViewById(R.id.facebookLoginButton);
		buttonToHome.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent intent1 = new Intent(context,FacebookLogin.class);
				startActivity(intent1);
				finish();
			}
		});
		
	startGame.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent intent2 = new Intent(context,Game_Level.class);
				startActivity(intent2);
				finish();
			}
		});
		
	}
}
