package com.example.acememo;

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
								// User is logged in, forward to next activity
								Intent GameLevel = new Intent(context,Game_Level.class);
								startActivity(GameLevel);
								finish();
								
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
		Log.d("LogByEmir", "here in OnActivityResult");
	}


}
