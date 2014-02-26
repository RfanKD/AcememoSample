package com.example.acememo;

import android.app.Activity;
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
import android.widget.TextView;

public class Continue_TO extends Activity{
	Button continueNext, replay;
	TextView message;
	String senderClass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.continue_next);
		//message = (TextView) findViewById(R.id.goodJob);
		senderClass = getIntent().getStringExtra("sender");
//		if(senderClass.equals("three")){
//			message.setText(R.string.oops);
//		}
		continueNext = (Button)findViewById(R.id.continueGame);
		replay = (Button) findViewById(R.id.replay);
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		final Context context = this;
		
		continueNext.setOnClickListener (new OnClickListener() {
			@Override
			public void onClick(View arg0){
				startActivity(determineNextClass(context, "continue"));
				finish();
			}
		});
		
		replay.setOnClickListener (new OnClickListener() {
			@Override
			public void onClick(View arg0){
				startActivity(determineNextClass(context, "replay"));
				finish();
			}
		});
	}
	
	private Intent determineNextClass(Context c, String button){
		
		Intent intent = null;
		if(button.equals("continue")){
			if(senderClass.equals("one")){
				intent = new Intent(c,Level_Two.class);
			}else if(senderClass.equals("two")){
				intent = new Intent(c,Level_Three.class);
			}
		}else if (button.equals("replay")){
			if(senderClass.equals("one")){
				intent = new Intent(c,Level_One.class);
			}else if(senderClass.equals("two")){
				intent = new Intent(c,Level_Two.class);
			}else{
				intent = new Intent(c,Level_Three.class);
			}
		}
		return intent;
	}

}
