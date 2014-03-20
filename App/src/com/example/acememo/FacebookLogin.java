package com.example.acememo;

import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.acememo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.*;
import com.facebook.Request.GraphUserListCallback;
import com.facebook.model.*;


public class FacebookLogin extends Activity{

	Button allow;
	Button disallow;
	
	static String user_id;
	
	private static final int MAX_FRIENDS = 10;
	private String friendLikes = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebook_login);
		
		allow = (Button)findViewById(R.id.allow);
		disallow = (Button) findViewById(R.id.disallow);
		
		addListenerOnButton();
		Log.d("LogByEmir", "FacebookLogin Activity Started");
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
								TextView welcome = (TextView) findViewById(R.id.facebookInstructions);
								welcome.setText("Hello " + user.getName() + "!");
								getFriends();
								user_id = user.getId();
							}
						}
					}).executeAsync();
				}

			}
		});
	}	

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
	final Context context = this;
		
		allow.setOnClickListener (new OnClickListener() {
			@Override
			public void onClick(View arg0){
				Intent intent1 = new Intent(context,Game_Level.class);
				startActivity(intent1);
			}
		});
		
		disallow.setOnClickListener (new OnClickListener() {
			@Override
			public void onClick(View arg0){
				Intent intent = new Intent(context,MainActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
		Log.d("LogByEmir", "here in OnActivityResult");
	}

	private void getFriends(){
		Session activeSession = Session.getActiveSession();
		if(activeSession.getState().isOpened()){
			Request friendRequest = Request.newMyFriendsRequest(activeSession, 
					new GraphUserListCallback(){
				@Override
				public void onCompleted(List<GraphUser> users,
						Response response) {
					JSONArray data;
					try {
						data = (JSONArray) response.getGraphObject().getInnerJSONObject().get("data");
						getLikesOfAFriend(data);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			Bundle params = new Bundle();
			params.putString("fields", "id, first_name, picture");
			friendRequest.setParameters(params);
			friendRequest.executeAsync();
		}
	} 

	private void getLikesOfAFriend(JSONArray friends) throws JSONException {
		Random random = new Random();
		int randomNumber = random.nextInt(friends.length());

		JSONArray result = new JSONArray();
		for (int i=0; i<MAX_FRIENDS; i++) {
			JSONObject element = new JSONObject();
			JSONObject f = (JSONObject) friends.get(randomNumber);
			element.put("personId", (String) f.get("id"));
			element.put("personName", (String) f.get("first_name"));
			String picture = (String) ((JSONObject) ((JSONObject) f.get("picture")).get("data")).get("url");
			element.put("personImage", picture.replace("https", "http"));
//			Log.d("LikeLog", getFriendsLikes((String) f.get("id")));
			friendLikes = null;
			element.put("likeName", "xx");
			element.put("likeImage", "yy");
			result.put(element);
		}
		Log.d("LogByEmir", result.toString());
	}

	private String getFriendsLikes(String uid) {
		Session session = Session.getActiveSession();
		Request.Callback callback = new Request.Callback() {
			@Override
			public void onCompleted(Response response) {
				// response should have the likes
				friendLikes = response.toString();
	        	Log.d("LikeLog", friendLikes.toString());
			}
		};
		Request request = new Request(session, "me" + "/likes", null, HttpMethod.GET, callback);
		RequestAsyncTask task = new RequestAsyncTask(request);
		task.execute();
		while (friendLikes==null) {
			// do nothing
		}
		return friendLikes;
	}


}
