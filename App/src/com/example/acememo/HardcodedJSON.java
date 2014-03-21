package com.example.acememo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;

public class HardcodedJSON {
	
	JSONObject json1 = new JSONObject();
	JSONObject json2 = new JSONObject();
	JSONObject json3 = new JSONObject();
	JSONObject json4 = new JSONObject();
	JSONObject json5 = new JSONObject();
	JSONObject json6 = new JSONObject();
	JSONObject json7 = new JSONObject();
	JSONObject json8 = new JSONObject();
	JSONObject json9 = new JSONObject();
	JSONObject json10 = new JSONObject();
	JSONObject json11 = new JSONObject();
	JSONObject json12 = new JSONObject();
	JSONObject json13 = new JSONObject();
	JSONObject json14 = new JSONObject();
	JSONObject json15 = new JSONObject();
	JSONObject json16 = new JSONObject();
	JSONArray gameArray;
	
	JSONObject[] jsonArray = new JSONObject[16];
	
	int[] itemDraw = {R.drawable.pizza, R.drawable.music, R.drawable.painting, R.drawable.biking, R.drawable.photography, R.drawable.lego, R.drawable.movies, R.drawable.soccer};
	int[] peopleDraw = {R.drawable.emir, R.drawable.alison, R.drawable.raph, R.drawable.jim, R.drawable.miley, R.drawable.will};
	String[] itemName = {"pizza", "music", "painting", "biking", "photography", "lego", "movies", "soccer"};
	String[] personName = {"Emir", "Alison", "Raph", "Jim", "Miley", "Will"};
	
	public HardcodedJSON(int level){
		setUpJSONS(level);
	}

	public void setUpJSONS(int level){
		fillArray();
		gameArray = new JSONArray();

		for(int i=0; i<level; i++){
			try {
				jsonArray[i].put("personIdd", "00000000");
				jsonArray[i].put("personName", personName[i % 6]);
				jsonArray[i].put("personImage",  peopleDraw[i % 6]);
				jsonArray[i].put("likeName", itemName[i % 8]);
				jsonArray[i].put("likeImage", itemDraw[i % 8]);
				
				gameArray.put(i, jsonArray[i]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public JSONArray getGameArray(){
		return gameArray;
	}
	
	private void fillArray(){
		jsonArray[0] = json1;
		jsonArray[1] = json2;
		jsonArray[2] = json3;
		jsonArray[3] = json4;
		jsonArray[4] = json5;
		jsonArray[5] = json6;
		jsonArray[6] = json7;
		jsonArray[7] = json8;
		jsonArray[8] = json9;
		jsonArray[9] = json10;
		jsonArray[10] = json11;
		jsonArray[11] = json12;
		jsonArray[12] = json13;
		jsonArray[13] = json14;
		jsonArray[14] = json15;
		jsonArray[15] = json16;
	}
} 
