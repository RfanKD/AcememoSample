package com.example.acememo;

import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import com.facebook.*;
import com.facebook.Request.GraphUserListCallback;
import com.facebook.model.*;

public class FacebookData {

	private final JSONArray result = new JSONArray();
	private static final Random random = new Random();

	
	public FacebookData(int number) {
		getFriends(number);
	}
	
	private void getFriends(int howMany){
		final int amount = howMany;
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
						getLikesOfAFriend(data, amount);

					} catch (JSONException e) {
						e.printStackTrace();
					}

				}
			});
			Bundle params = new Bundle();
			params.putString("fields", "id, first_name");
			friendRequest.setParameters(params);
			friendRequest.executeAsync();
		}
	}
	
	private static int getRandom(int max) {
		int randomNumber = random.nextInt(max);
		return randomNumber;
	}

	private void getLikesOfAFriend(JSONArray friends, int howMany) throws JSONException {
		// Start constructing the resulting array
		for (int i=0; i<howMany; i++) {
			JSONObject f = (JSONObject) friends.get(getRandom(friends.length()));
			addToResult((String) f.get("id"), (String) f.get("first_name"));
		}
		
	}

	private void addToResult(String uid, String fname) {
		String fqlQuery = "SELECT page_id, name FROM page where page_id IN (SELECT page_id FROM page_fan WHERE uid=" + uid + ")";
		Bundle params = new Bundle();
		params.putString("q", fqlQuery);
		final String userId = uid;
		final String firstName = fname;
		Session session = Session.getActiveSession();
		Request request = new Request(session, 
				"/fql", 
				params, 
				HttpMethod.GET, 
				new Request.Callback(){ 
			public void onCompleted(Response response) {
				try {
					JSONArray dataArray = (JSONArray) response.getGraphObject().getInnerJSONObject().get("data");
					JSONObject data = (JSONObject) dataArray.get(1);
					String pid = (String) data.get("page_id");
					String name = (String) data.get("name");
					JSONObject j = new JSONObject();
					j.put("personId", userId);
					j.put("personName", firstName);
					j.put("likeId", pid);
					j.put("likeName", name);
					result.put(j);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		Request.executeBatchAsync(request);

	}
	
	// This should be called with some delay
	public JSONArray getResult() {
	      
        return result;
	}



}
