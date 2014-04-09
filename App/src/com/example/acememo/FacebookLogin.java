package com.example.acememo;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.facebook.*;
import com.facebook.model.*;
public class FacebookLogin extends Activity {

	Button allow;
	Button disallow;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		Log.d("LogByEmir", "FacebookLogin Activity Started");
		
		final Context context = this;
		// start Facebook Login
		Session.openActiveSession(this, true, new Session.StatusCallback() {
			// callback when session changes state
			@Override
			public void call(Session session, SessionState state, Exception exception) {
				Log.d("LogByEmir", "callback");
				if (session.isOpened()) {
					Log.d("LogByEmir", "session is opened");
					Request.newMeRequest(session, new Request.GraphUserCallback() {
						// callback after Graph API response with user object
						@Override
						public void onCompleted(GraphUser user, Response response) {
							if (user != null) {
								Log.d("LogByEmir", "request completed");
								Log.d("LikeLog", user.getName());
								System.out.println("YOOOOO");
								// 5 is how many pairs we want
								LevelDataFromFacebook.facebookData = new FacebookData(25);					
								
								// You delay pulling the data 5 seconds to wait all the requests finish up
								new Timer().schedule( 
								        new TimerTask() {
								            @Override
								            public void run() {
								                // fb.getResult() here returns a JSONArray.
								            	// you should set up the layout with this, here.
								            	String result = LevelDataFromFacebook.facebookData.getResult().toString();
								            	Log.d("LikeLog", "RESULT: " + result);
								            	
								            	int index = result.indexOf("is");
								            	int count = 0;
								            	while (index != -1) {
								            	    count++;
								            	    result = result.substring(index + 1);
								            	    index = result.indexOf("personId");
								            	}
								            	Log.d("AlisonLog", "RESULT COUNT: " + count);
								            	

								            }
								        }, 
								        60000
								);
								
								
								

								
								// You delay pulling the data 5 seconds to wait all the requests finish up
								new Timer().schedule( 
								        new TimerTask() {
								            @Override
								            public void run() {
												
												// User is logged in, forward to next activity
												Intent goHome = new Intent(context,MainActivity.class);
												MainActivity.screenStatus = 3;
												startActivity(goHome);
												finish();

								            }
								        }, 
								        6000
								);
							}
						}
					}).executeAsync();
				}

			}
		});
	}
		
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}


}
