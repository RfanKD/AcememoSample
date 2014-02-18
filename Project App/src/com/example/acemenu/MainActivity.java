package com.example.acemenu;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button buttonToHome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton();
	}

	
	public void addListenerOnButton(){
		final Context context = this ;
		
		buttonToHome = (Button) findViewById(R.id.button1);
		buttonToHome.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent intent1 = new Intent(context,HomeMenu.class);
				startActivity(intent1);
			}
		});
		
	}

}
