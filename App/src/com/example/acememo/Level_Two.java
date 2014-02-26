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

public class Level_Two extends Activity{
	
	Button ready,done;
	ImageView image1, image2, heart1, heart2, likesImage,likesImage2;
	TextView statement1,statement2;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_two);
		
		
		 image1 = (ImageView) findViewById(R.id.imageView1);
	     image2 = (ImageView) findViewById(R.id.imageView3);
	     heart1 = (ImageView) findViewById(R.id.imageView5);
	     heart2 = (ImageView) findViewById(R.id.imageView6);
	     statement1 = (TextView) findViewById(R.id.statement1);
	     statement2 = (TextView) findViewById(R.id.statement2);
	     likesImage = (ImageView) findViewById(R.id.imageView2);
	     likesImage2 = (ImageView) findViewById(R.id.imageView4);
	     done = (Button) findViewById(R.id.button1);
	     done.setVisibility(View.INVISIBLE);
	     
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		final Context context = this ;
		// TODO Auto-generated method stub
		ready = (Button) findViewById(R.id.imReady);
		ready.setOnClickListener (new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				heart1.setVisibility(View.INVISIBLE);
				heart2.setVisibility(View.INVISIBLE);
				ready.setVisibility(View.INVISIBLE);
				statement1.setVisibility(View.INVISIBLE);
				statement2.setVisibility(View.INVISIBLE);
				done.setVisibility(View.VISIBLE);
			}
		});
			
		done.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent continueTO = new Intent(context,Result.class);
				startActivity(continueTO);
				finish();
			}
		});
	}
}
