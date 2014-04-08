package com.example.acememo;

import com.example.acememo.R;
import com.example.acememo.HardcodedJSON;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class New_Level extends Activity {
	private int page, mode;
	static int level;
	private String userId;
	private final int INFO = 1;
	private final int GAME = 2;
	ImageView person1, person2, person3, person4, person5, person6, person7, person8, person9, person10;
	ImageView heart1, heart2, heart3, heart4, heart5, heart6;
	ImageView item1, item2, item3, item4, item5, item6, item7, item8, item9, item10;
	TextView levelName, statement1, statement2, statement3, statement4, statement5, statement6;
	Button ready, done, nextPage, prevPage;
	
	ImageView[] personArray;
	ImageView[] heartArray = new ImageView[5];
	ImageView[] itemArray;
	TextView[] statementArray = new TextView[5];

	static int levelScore;
	private float dropX, dropY;
	private boolean isSet;
	static JSONArray levelData;
	
	private LinearLayout root;
	private LinearLayout ll;
	private LinearLayout.LayoutParams widgetParams;
	private LayoutParams containerParams;
	//private Button goToReview;
	
	private ArrayList<ImageView> likeImage;
	private ArrayList<ImageView> profileImage;
	private ArrayList<Boolean> dropCorrect;
	
		
//	private ImageView likePic;
//	private ImageView profilePic;
	
	//private ImageView profilePic;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_level);
		assignVariables(INFO);
		populateArrays(INFO);
        //findViewById(R.id.level3Relative).setOnDragListener(new MyDragListener());
        done.setVisibility(View.INVISIBLE);
		addListenerOnButton();
		
		containerParams 
		 = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT,
				0.0F);
		
		widgetParams
		 = new LinearLayout.LayoutParams(
				 ViewGroup.LayoutParams.FILL_PARENT,
				 ViewGroup.LayoutParams.WRAP_CONTENT,
				 1.0F);
		
		root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.setLayoutParams(containerParams);
		
		ll = new LinearLayout(this);
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(containerParams);
		root.addView(ll);
		page = 1;
				
		level = LevelDataFromFacebook.level;
				
		levelName.setText("Level " + level);
        
		if(level<=5){
			updateViews("onStartLessThan5", level);
			addPictures(0, level-1, INFO);
		}else{
			updateViews("onStartMoreThan5", 0);
			addPictures(0,4, INFO);
		}
		
		root.setOnDragListener(new MyDragListener());
		
		//goToReview = new Button (this);
		//root.addView(goToReview);

		isSet = false;
		levelScore = 0;
		
		likeImage = new ArrayList<ImageView>();
		profileImage = new ArrayList<ImageView>();
		dropCorrect = new ArrayList<Boolean>();
		
		addListenerOnButton();

	}

/*	
	@SuppressWarnings("deprecation")

//	private void addListenerOnButton() {
//		// TODO Auto-generated method stub
//	final Context context = this;
//			
//	goToReview.setOnClickListener (new OnClickListener() {
//			@Override
//			public void onClick(View arg0){
//				Intent intent = new Intent(context, Result.class);
//				startActivity(intent);
//			}
//		});
//		
//	}
//	*/
//	public void createImageView (int totalLevel, int currentLevel){
//		
//		
//		TextView tb = new TextView(this);
//		tb.setText("test");
//		tb.setLayoutParams(containerParams);
//
//		
//		root.addView(tb);
//		
//		String IMAGEVIEW_TAG;
//		
//		
//		switch(currentLevel){
//		case 1: IMAGEVIEW_TAG = "Raph " + " likes " +  " photo ";
//				break;
//		case 2:  IMAGEVIEW_TAG = "Joe " + " likes " +  " Fishing ";
//				break;
//		case 3:  IMAGEVIEW_TAG = "Mike " + " likes " +  " bowling ";;
//				break;
//		case 4:  IMAGEVIEW_TAG = "Craig " + " likes " +  " archery ";;
//				break;
//		default:  IMAGEVIEW_TAG = "Alex " + " likes " +  " reading ";;
//	   }
//		
//		ImageView likePic = new ImageView(this);
//		likePic.setProfileId("457041557690681");
//		likePic.setPresetSize(ImageView.NORMAL);
//		likePic.setTag(IMAGEVIEW_TAG);
//		likeImage.add(likePic);
//		root.addView(likePic);
//		
//		ImageView profilePic = new ImageView(this);
//		profilePic.setProfileId("1651320295");
//		profilePic.setPresetSize(ImageView.NORMAL);
//		profilePic.setOnTouchListener(new MyTouchListener());
//		profilePic.setTag(IMAGEVIEW_TAG);
//		profileImage.add(profilePic);
//		root.addView(profilePic);
//		
//		 		
//		setContentView(root);
//		
//		
//	}
	
	public void addListenerOnButton(){
		final Context context = this ;
		
		prevPage.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				updateViews("useAllSpots",5);
				if(page==2){
					prevPage.setVisibility(View.INVISIBLE);
				}else{
					prevPage.setVisibility(View.VISIBLE);
				}
				page --;
				addPictures((page*5) - 5, page*5 -1, INFO);
				
				nextPage.setVisibility(View.VISIBLE);
				

			}
		});
		
		nextPage.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				page++;
				if(level <= page*5){
					updateViews("nextLastPage", (level % 5));
					addPictures(5*(page-1), level-1, INFO);
				}else{
					addPictures(5*(page-1), (5*page)-1, INFO);
				}
				prevPage.setVisibility(View.VISIBLE);
	
			}
		});
		
		ready.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				setContentView(R.layout.game_play);
				assignVariables(GAME);
				populateArrays(GAME);
				
				done.setOnClickListener (new OnClickListener() {
					
					@Override
					public void onClick(View arg0){
						Intent continueTO = new Intent(context,Result.class);
						//continueTO.putExtra("sender", "three");
						startActivityForResult(continueTO, 0);
						showCorrectAnswer();
					}

					private void showCorrectAnswer() {
						setContentView(R.layout.game_play);
						assignVariables(GAME);
						populateArrays(GAME);
						
						 for (int i=0; i < likeImage.size(); i++){
							 if(i < 5){
							 personArray[i].setImageDrawable(profileImage.get(i).getDrawable());
							 personArray[i].setImageMatrix(profileImage.get(i).getImageMatrix());
							 
							 personArray[i+5].setImageDrawable(likeImage.get(i).getDrawable());
							 personArray[i+5].setImageMatrix(likeImage.get(i).getImageMatrix());
							 
												 
							 }else{
								 itemArray[i].setImageDrawable(profileImage.get(i).getDrawable());
								 itemArray[i].setImageMatrix(profileImage.get(i).getImageMatrix());
								 
								 itemArray[i-5].setImageDrawable(likeImage.get(i).getDrawable());
								 itemArray[i-5].setImageMatrix(likeImage.get(i).getImageMatrix());
								 
							 }
							 
							 if (dropCorrect.get(i) == false){
								 personArray[i].setPadding(5, 5, 5, 5);
								 personArray[i].setBackgroundColor(Color.rgb(255, 0, 0));
								 
								 personArray[i+5].setPadding(5, 5, 5, 5);
								 personArray[i+5].setBackgroundColor(Color.rgb(255, 0, 0));
								 
							 }
							 
						 }
						
//						 private ArrayList<ImageView> likeImage;
//							private ArrayList<ImageView> profileImage;
//							private ArrayList<Boolean> dropCorrect;
//							
//							ImageView[] personArray;
//							ImageView[] heartArray = new ImageView[5];
//							ImageView[] itemArray;
//						
						//test.equals(likeImage.get(0)) ;
						//test.setIm
							done.setOnClickListener (new OnClickListener() {
								
								@Override
								public void onClick(View arg0){
									Intent continueTO = new Intent(context,Result.class);
									//continueTO.putExtra("sender", "three");
									startActivityForResult(continueTO, 0);
									finish();
								}
							});
											
						
					}
				});
				
				updateViews("forGamePlay", level);
				addPictures(0, level-1, GAME);
				
			//	setTouchListeners();
				
				findViewById(R.id.level3Relative).setOnDragListener(new MyDragListener());
				
				ready.setVisibility(View.INVISIBLE);
				done.setVisibility(View.VISIBLE);
			}
		});
		

	
	}
	
//	private boolean dropEventNotHandled(DragEvent dragEvent) {
//        return !dragEvent.getResult();
//	}
	
	private void updateViews(String callingLocation, int numBoxesNeeded){
		if(callingLocation.equals("onStartLessThan5")){
			nextPage.setVisibility(View.INVISIBLE);
			prevPage.setVisibility(View.INVISIBLE);
			for(int i=4; i>=numBoxesNeeded; i--){
				personArray[i].setVisibility(View.INVISIBLE);
				heartArray[i].setVisibility(View.INVISIBLE);
				itemArray[i].setVisibility(View.INVISIBLE);
				statementArray[i].setVisibility(View.INVISIBLE);
			}
		}else if(callingLocation.equals("nextLastPage")){
			nextPage.setVisibility(View.INVISIBLE);
			prevPage.setVisibility(View.VISIBLE);
			for(int i=4; i>=numBoxesNeeded; i--){
				personArray[i].setVisibility(View.INVISIBLE);
				heartArray[i].setVisibility(View.INVISIBLE);
				itemArray[i].setVisibility(View.INVISIBLE);
				statementArray[i].setVisibility(View.INVISIBLE);
			}
		}else if(callingLocation.equals("useAllSpots")){
			nextPage.setVisibility(View.INVISIBLE);
			for(int i=0; i<5; i++){
				personArray[i].setVisibility(View.VISIBLE);
				heartArray[i].setVisibility(View.VISIBLE);
				itemArray[i].setVisibility(View.VISIBLE);
				statementArray[i].setVisibility(View.VISIBLE);
			}
		}else if(callingLocation.equals("onStartMoreThan5")){
			nextPage.setVisibility(View.VISIBLE);
			prevPage.setVisibility(View.INVISIBLE);
		
		}else if(callingLocation.equals("forGamePlay")){
			for(int i=9; i>(numBoxesNeeded); i--){
				personArray[i].setVisibility(View.INVISIBLE);
				//itemArray[i].setVisibility(View.INVISIBLE);
			}
		}
	}
	
//	private void setTouchListeners(){
//		for(int i=0; i<5; i++){
//			personArray[i].setOnTouchListener(new  MyTouchListener());
//		}
//	}
//	
//	private void setDragListeners(){
//		for(int i=0; i<5; i++){
//			itemArray[i].setOnTouchListener(new  MyTouchListener());
//		}
//	}
	
	private final class MyTouchListener implements OnTouchListener {
		  public boolean onTouch(View view, MotionEvent motionEvent) {
		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		      ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
		      
		      String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
		      
		      ClipData dragData = new ClipData(view.getTag().toString(),mimeTypes,item);
		      
		    // System.out.println(view.getTag().toString());
		      
		      DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
		      view.startDrag(dragData, 
		    		         shadowBuilder, 
		    		         view, 
		    		         0);
		      view.setVisibility(View.INVISIBLE);
		      return true;
		    } else {
		    	return false;
		    }
		  }

	}
	
	private final class MyDragListener implements OnDragListener  {
		 
		  public boolean onDrag(View view,  DragEvent event){	
			     int index = 0 ;
			  	 switch(event.getAction())                   
		         {
		            case DragEvent.ACTION_DRAG_ENTERED:
		            	System.out.println("im here1");
		            	return true;
		            case DragEvent.ACTION_DRAG_EXITED :
		            	System.out.println("im here2");
		            	 return true;
		            case DragEvent.ACTION_DRAG_STARTED:
		            	//View dragViewA = (View) event.getLocalState();
		            	 ImageView dragViewA = (ImageView) event.getLocalState();
		            	
		            	int profilePicIndex = profileImage.indexOf(dragViewA);
		            	
		            	System.out.println ("this is my id" +profilePicIndex);
		            	//ClipData.Item itemA = event.getClipData().getItemAt(0); 
		            	//String incomingTextA = itemA.getText().toString();
		            	
		            	// for (int i=0; i < profileImage.size(); i++) {
		            	//	 String targetText = likeImage.get(i).getTag().toString();
		            	//	 if (targetText == incomingTextA){
		     	            	//if (isSet == true){
		     	            	if(dropCorrect.get(profilePicIndex) == true){	
		     	            		System.out.println(profileImage.get(index).getTag());
				            		levelScore --;
				            		 Game_Level.totalScore --;
				            		isSet = false ;
				            		dropCorrect.set(profilePicIndex, false);
				            		// dropCorrect.set(i, false);
				            		System.out.println(levelScore);
				            		
				            	}
		     	            	

			            		//int[] locationB = new int[2];
			            		//profileImage.get(profilePicIndex).getLocationOnScreen(locationB);
			            		 dropX = dragViewA.getLeft();
					             dropY = dragViewA.getTop();
					        			             					           	          	 		            	 
				            	 for (int i=0; i < likeImage.size(); i++) {
				            		 int[] locationA = new int[2];
				            		 likeImage.get(i).getLocationOnScreen(locationA);
				            		 int a = locationA[0];
						             int b = locationA[1];
						             
						             int widthA = likeImage.get(i).getWidth();
						             int heightA = likeImage.get(i).getHeight();
						             
						             if(a <= dropX && dropX <= (a + widthA) && (b - heightA) <= dropY && dropY <= b){
						            	 
						            	// likeImage.get(i).setPadding(5, 5, 5, 5);
						            	 likeImage.get(i).setBackgroundColor(Color.rgb(255, 255, 255));
						            	 
						             }
				            		 //System.out.println(sFruit);
				                  }
		            		 //}
		            	// }
		            	System.out.println("im here3");
	
	                    return true;
		            case DragEvent.ACTION_DRAG_LOCATION:
		            	//System.out.println("im here4");
	                    //v.setVisibility(View.VISIBLE);
	                    return false;
		            case DragEvent.ACTION_DROP:
		            	 View dragView = (View) event.getLocalState();
		            	 
		            	 
		            	 
		            	  dragView.bringToFront();
			              dragView.setVisibility(View.VISIBLE);  
		               //RelativeLayout containView = (RelativeLayout) view;
		               //containView.addView(dragView);
		            	 ClipData.Item item = event.getClipData().getItemAt(0); 
		            	 
		            	 String incomingText = item.getText().toString();
		            	 
		            	 
		            	 
		                 dropX = event.getX();
			             dropY = event.getY();
			                     		               
			             dragView.setX(dropX - dragView.getWidth() / 2 );
			             dragView.setY(dropY - dragView.getHeight() / 2);
			             
		            		            	 		            	 
		            	 for (int i=0; i < likeImage.size(); i++) {
		            		 int[] locationA = new int[2];
		            		 likeImage.get(i).getLocationOnScreen(locationA);
		            		 int a = locationA[0];
				             int b = locationA[1];
				             
				             int widthA = likeImage.get(i).getWidth();
				             int heightA = likeImage.get(i).getHeight();
				             
				             if(a <= dropX && dropX <= (a + widthA) && (b - heightA) <= dropY && dropY <= b){
				            	 String targetText = likeImage.get(i).getTag().toString();
				            	 likeImage.get(i).setPadding(5, 5, 5, 5);
				            	 likeImage.get(i).setBackgroundColor(Color.rgb(50, 150, 200));
				            	 if (targetText.equals(incomingText)){
				            		 System.out.println("we have match"); 
				            		 dropCorrect.set(i, true);
				            		 index = i;
				            		 levelScore ++;
				            		 Game_Level.totalScore ++;
				            		 isSet = true;
				            		 System.out.println(levelScore);
				            	 }else{
				            		 System.out.println("no match found");
				            	 }
				             }
		            		 //System.out.println(sFruit);
		                  }
		            	       	 
		          	     		               
		             		               
		              // int[] location = new int[2];
		              // likePic.getLocationOnScreen(location);
		               
		              // int x = location[0];
		              // int y = location[1];
		               
		             //  int width = likePic.getWidth();
		             //  int height = likePic.getHeight();
		               
		             //  if(x <= dropX && dropX <= (x + width) && (y - height) <= dropY && dropY <= y){
			            
		             //  }
		               
		               break;
		            case DragEvent.ACTION_DRAG_ENDED   :
		            	View dragView1 = (View) event.getLocalState();
			               System.out.println("im here5");
			               
			               System.out.println(Game_Level.totalScore);
			               dragView1.setVisibility(View.VISIBLE);
			               
			           break;
		            default: return true;
		            }
		            return true;
		         }
		  
	}
	
	public void addPictures(int startNumber, int endNumber, int mode){

		if(mode == INFO){
			boolean stop = false;
			for(int i=0; i<5 && !stop; i++){
				if(startNumber <= endNumber){
					try {
						if(MainActivity.withFacebook){
								AsyncTask<String, Void, Bitmap> getPersonImage = new RetreiveImage().execute(levelData.getJSONObject(startNumber).getString("personImage"));
								AsyncTask<String, Void, Bitmap> getLikeImage = new RetreiveImage().execute(levelData.getJSONObject(startNumber).getString("likeImage"));

								try {
									Bitmap person = getPersonImage.get();
									Bitmap item = getLikeImage.get();
									
									personArray[i].setImageBitmap(person);
									itemArray[i].setImageBitmap(item);
									
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (ExecutionException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								

							
						}else{
							personArray[startNumber].setImageResource(levelData.getJSONObject(startNumber).getInt("personImage"));
							itemArray[startNumber].setImageResource(levelData.getJSONObject(startNumber).getInt("likeImage"));
						}
						String newStatement = levelData.getJSONObject(startNumber).getString("personName") +
											   " likes "+
											   levelData.getJSONObject(startNumber).getString("likeName");
						statementArray[i].setText(newStatement);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					startNumber++;
				}else{
					stop = true;
				}
			}
		}else{
			
			Integer[] arr = {0,1,2,3,4,5,6,7,8,9};
			Collections.shuffle(Arrays.asList(arr));
		    
			while(startNumber<= endNumber){
					try {
						if(MainActivity.withFacebook){
							AsyncTask<String, Void, Bitmap> getPersonImage = new RetreiveImage().execute(levelData.getJSONObject(startNumber).getString("personImage"));
							AsyncTask<String, Void, Bitmap> getLikeImage = new RetreiveImage().execute(levelData.getJSONObject(startNumber).getString("likeImage"));
							try {
								Bitmap person = getPersonImage.get();
								Bitmap item = getLikeImage.get();
								personArray[startNumber].setImageBitmap(person);
								itemArray[arr[startNumber]].setImageBitmap(item);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ExecutionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							
							
						}else{
							personArray[startNumber].setImageResource(levelData.getJSONObject(startNumber).getInt("personImage"));
							itemArray[arr[startNumber]].setImageResource(levelData.getJSONObject(startNumber).getInt("likeImage"));
						}
						
						String newStatement = levelData.getJSONObject(startNumber).getString("personName") +
								   " likes "+
								   levelData.getJSONObject(startNumber).getString("likeName");
						
						personArray[startNumber].setTag(newStatement);
						personArray[startNumber].setOnTouchListener(new  MyTouchListener());
						itemArray[arr[startNumber]].setTag(newStatement);
						
						ImageView likeTest = itemArray[arr[startNumber]];
						ImageView profileTest = personArray[startNumber];
						boolean isCorrect = false;
						
						likeImage.add(likeTest);
						profileImage.add(profileTest);
						dropCorrect.add(isCorrect);
					
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					startNumber++;
			}		
		}
	}

	private void assignVariables(int mode){
			person1 = (ImageView) findViewById(R.id.person1);
			person2 = (ImageView) findViewById(R.id.person2);
			person3 = (ImageView) findViewById(R.id.person3);
			person4 = (ImageView) findViewById(R.id.person4);
			person5 = (ImageView) findViewById(R.id.person5);
			//person1.setOnTouchListener(new  MyTouchListener());
			//person2.setOnTouchListener(new  MyTouchListener());
			//person3.setOnTouchListener(new  MyTouchListener());
			//person4.setOnTouchListener(new  MyTouchListener());
			//person5.setOnTouchListener(new  MyTouchListener());
			
			item1 = (ImageView) findViewById(R.id.item1);
			item2 = (ImageView) findViewById(R.id.item2);
			item3 = (ImageView) findViewById(R.id.item3);
			item4 = (ImageView) findViewById(R.id.item4);
			item5 = (ImageView) findViewById(R.id.item5);
			
		 
		if(mode == INFO){
			done = (Button)findViewById(R.id.button1);
			ready = (Button)findViewById(R.id.imReady);
			nextPage = (Button) findViewById(R.id.nextPage);
			prevPage = (Button) findViewById(R.id.prevPage);
			levelName = (TextView) findViewById(R.id.levelNum);
			
			heart1 = (ImageView) findViewById(R.id.likes1);
			heart2 = (ImageView) findViewById(R.id.likes2);
			heart3 = (ImageView) findViewById(R.id.likes3);
			heart4 = (ImageView) findViewById(R.id.likes4);
			heart5 = (ImageView) findViewById(R.id.likes5);
		
			statement1 = (TextView) findViewById(R.id.statement1);
			statement2 = (TextView) findViewById(R.id.statement2);
			statement3 = (TextView) findViewById(R.id.statement3);
			statement4 = (TextView) findViewById(R.id.statement4);
			statement5 = (TextView) findViewById(R.id.statement5);
				
		}else{
			done = (Button)findViewById(R.id.button1);
			person6 = (ImageView) findViewById(R.id.person6);
			person7 = (ImageView) findViewById(R.id.person7);
			person8 = (ImageView) findViewById(R.id.person8);
			person9 = (ImageView) findViewById(R.id.person9);
			person10 = (ImageView) findViewById(R.id.person10);
			//person6.setOnTouchListener(new  MyTouchListener());
			//person7.setOnTouchListener(new  MyTouchListener());
			//person8.setOnTouchListener(new  MyTouchListener());
			//person9.setOnTouchListener(new  MyTouchListener());
			//person10.setOnTouchListener(new  MyTouchListener());
			
			item6 = (ImageView) findViewById(R.id.item6);
			item7 = (ImageView) findViewById(R.id.item7);
			item8 = (ImageView) findViewById(R.id.item8);
			item9 = (ImageView) findViewById(R.id.item9);
			item10 = (ImageView) findViewById(R.id.item10);
		}
		
}

	private void populateArrays(int mode){
		System.out.println("HERE");
		if(mode == INFO){
			personArray = new ImageView[5];
			itemArray = new ImageView[5];
			
			heartArray[0] = heart1;
			heartArray[1] = heart2;
			heartArray[2] = heart3;
			heartArray[3] = heart4;
			heartArray[4] = heart5;
			
			statementArray[0] = statement1;
			statementArray[1] = statement2;
			statementArray[2] = statement3;
			statementArray[3] = statement4;
			statementArray[4] = statement5;
			
		}else{
			
			personArray = new ImageView[10];
			itemArray = new ImageView[10];
			
			personArray[5] = person6;
			personArray[6] = person7;
			personArray[7] = person8;
			personArray[8] = person9;
			personArray[9] = person10;
			
			itemArray[5] = item6;
			itemArray[6] = item7;
			itemArray[7] = item8;
			itemArray[8] = item9;
			itemArray[9] = item10;
			
		}
		personArray[0] = person1;
		personArray[1] = person2;
		personArray[2] = person3;
		personArray[3] = person4;
		personArray[4] = person5;
		
		itemArray[0] = item1;
		itemArray[1] = item2;
		itemArray[2] = item3;
		itemArray[3] = item4;
		itemArray[4] = item5;
	}
	
	class RetreiveImage extends AsyncTask<String, Void, Bitmap> {

	    protected Bitmap doInBackground(String... urls) {
		    try {
		        URL url = new URL(urls[0]);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        connection.setDoInput(true);
		        connection.connect();
		        InputStream input = connection.getInputStream();
		        Bitmap myBitmap = BitmapFactory.decodeStream(input);
		        return myBitmap;
		    } catch (IOException e) {
		        e.printStackTrace();
		        return null;
		    }
	    }

	    protected void onPostExecute(Bitmap bit) {
	        // TODO: check this.exception 
	        // TODO: do something with the feed
	    }
	}
}