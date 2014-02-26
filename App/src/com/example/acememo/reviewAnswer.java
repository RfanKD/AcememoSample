package com.example.acememo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class reviewAnswer extends Activity {
	
	Button endGame ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_three_correct);
		endGame = (Button)findViewById(R.id.endGame);
					
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		
		final Context context = this ;
		
		endGame.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				
				Intent finishGame = new Intent(context,Result.class);
				finishGame.putExtra("sender", "reviewed");
				startActivity(finishGame);
				finish();
			}
		});
		
	}
}
