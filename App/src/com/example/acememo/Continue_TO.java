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

public class Continue_TO extends Activity{
	Button continueNext;
	String senderClass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		senderClass = getIntent().getStringExtra("sender");
		setContentView(R.layout.continue_next);
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		final Context context = this;
		continueNext = (Button)findViewById(R.id.button1);
		continueNext.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent intent1;
				
				if(senderClass.equals("one")){
					intent1 = new Intent(context,Level_Two.class);
				}else if(senderClass.equals("two")){
					intent1 = new Intent(context,Level_Three.class);
				}else{
					intent1 = new Intent(context,Result.class);
				}
				 
				startActivity(intent1);
				finish();
			}
		});
	}

}
