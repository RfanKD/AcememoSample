package com.example.acememo;

import java.util.Arrays;
import java.util.Collections;

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

	JSONArray gameArray;
	
	JSONObject[] jsonPairInfo = new JSONObject[16];
	
	//int[] itemDraw = {R.drawable.pizza, R.drawable.music, R.drawable.painting, R.drawable.biking, R.drawable.photography, R.drawable.lego, R.drawable.movies, R.drawable.soccer, R.drawable.apple, R.drawable.beach, R.drawable.chocolate, R.drawable.dog, R.drawable.fireworks, R.drawable.microsoft, R.drawable.money, R.drawable.plane, R.drawable.school, R.drawable.scuba, R.drawable.travel, R.drawable.yoga};
	//int[] peopleDraw = {R.drawable.emir, R.drawable.alison, R.drawable.raph, R.drawable.jim, R.drawable.miley, R.drawable.will, R.drawable.albert, R.drawable.ashton, R.drawable.diana, R.drawable.howie, R.drawable.kim, R.drawable.mark, R.drawable.mrbean, R.drawable.nelson, R.drawable.queen, R.drawable.shrek};
	String[] itemName = {"pizza", "music", "painting", "biking", "photography", "lego", "movies", "soccer", "Apple", "beach", "chocolate", "dogs", "fireworks", "Microsoft", "money", "airplanes", "school", "scuba diving", "travelling", "yoga"};
	String[] personName = {"Emir", "Alison", "Raph", "Jim", "Miley", "Will", "Albert", "Ashton", "Diana", "Howie", "Kim", "Mark", "Mr.Bean", "Nelson", "The Queen", "Shrek"};
	
	
	int[] itemDraw = {R.drawable.pizza, R.drawable.music, R.drawable.painting, R.drawable.biking, R.drawable.photography, R.drawable.lego, R.drawable.movies, R.drawable.soccer};
	int[] peopleDraw = {R.drawable.emir, R.drawable.alison, R.drawable.raph, R.drawable.jim, R.drawable.miley, R.drawable.will};

	public HardcodedJSON(int level){
		setUpJSONS(level);
	}

	public void setUpJSONS(int level){
		fillArray();
		gameArray = new JSONArray();
		Integer[] peopleIndex = {0,1,2,3,4,5};
		//Integer[] peopleIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		Collections.shuffle(Arrays.asList(peopleIndex));
		
		Integer[] likeIndex = {0,1,2,3,4,5,6,7};
		//Integer[] likeIndex = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		Collections.shuffle(Arrays.asList(likeIndex));

		for(int i=0; i<level; i++){
			try {
				jsonPairInfo[i].put("personId", "00000000");
				jsonPairInfo[i].put("personName", personName[peopleIndex[i]]);
				jsonPairInfo[i].put("personImage",  peopleDraw[peopleIndex[i]]);
				jsonPairInfo[i].put("likeName", itemName[likeIndex[i]]);
				jsonPairInfo[i].put("likeImage", itemDraw[likeIndex[i]]);
				
				gameArray.put(i, jsonPairInfo[i]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public JSONArray getGameArray(){
		return gameArray;
	}
	
	public void clearData(){
		jsonPairInfo = null;
		gameArray = null;
	}
	
	private void fillArray(){
		jsonPairInfo[0] = json1;
		jsonPairInfo[1] = json2;
		jsonPairInfo[2] = json3;
		jsonPairInfo[3] = json4;
		jsonPairInfo[4] = json5;
	}
} 
