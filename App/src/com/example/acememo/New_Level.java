package com.example.acememo;

import com.example.acememo.R;
import com.facebook.widget.ProfilePictureView;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.ArrayList;

public class New_Level extends Activity {

	private int level, page;
	private String userId;
	ImageView person1, person2, person3, person4, person5, person6;
	ImageView heart1, heart2, heart3, heart4, heart5, heart6;
	ImageView item1, item2, item3, item4, item5, item6;
	TextView levelName, statement1, statement2, statement3, statement4, statement5, statement6;
	Button ready, done, nextPage, prevPage;
	
	ImageView[] personArray = new ImageView[6];
	ImageView[] heartArray = new ImageView[6];
	ImageView[] itemArray = new ImageView[6];
	TextView[] statementArray = new TextView[6];

	static int levelScore;
	private float dropX, dropY;
	private boolean isSet;
	
	private LinearLayout root;
	private LinearLayout ll;
	private LinearLayout.LayoutParams widgetParams;
	private LayoutParams containerParams;
	//private Button goToReview;
	
	private ArrayList<ProfilePictureView> likeImage;
	private ArrayList<ProfilePictureView> profileImage;
	
//	private ProfilePictureView likePic;
//	private ProfilePictureView profilePic;
	
	//private ProfilePictureView profilePic;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_level);
		assignVariables();
		populateArrays();
        //findViewById(R.id.level3Relative).setOnDragListener(new MyDragListener());
        done.setVisibility(View.INVISIBLE);
		addListenerOnButton();
/*		
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
<<<<<<< HEAD
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(containerParams);
		root.addView(ll);*/
		page = 1;
		//level = Game_Level.levelNumber;
		level=8;
		levelName.setText("Level " + level);
		// System.out.println(level);
		//userId = FacebookLogin.user_id;
		//System.out.println(userId);
		
		if(level<=5){
			upadteInfoViews("onStartLessThan5", level-1);
		}else{
			upadteInfoViews("onStartMoreThan5", 0);
		}
		
		root.setOnDragListener(new MyDragListener());
		
		//goToReview = new Button (this);
		//root.addView(goToReview);

		isSet = false;
		levelScore = 0;
		
		likeImage = new ArrayList<ProfilePictureView>();
		profileImage = new ArrayList<ProfilePictureView>();
		
		for (int i = 1; i <= level; i ++){
			//createImageView (level, i);
		}
		 
		
		addListenerOnButton();
	}

/*	
	@SuppressWarnings("deprecation")
=======
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
//	
	public void createImageView (int totalLevel, int currentLevel){
		
		
		TextView tb = new TextView(this);
		tb.setText("test");
		tb.setLayoutParams(containerParams);

		
		root.addView(tb);
		
		String IMAGEVIEW_TAG;
		
		
		switch(currentLevel){
		case 1: IMAGEVIEW_TAG = "Raph " + " likes " +  " photo ";
				break;
		case 2:  IMAGEVIEW_TAG = "Joe " + " likes " +  " Fishing ";
				break;
		case 3:  IMAGEVIEW_TAG = "Mike " + " likes " +  " bowling ";;
				break;
		case 4:  IMAGEVIEW_TAG = "Craig " + " likes " +  " archery ";;
				break;
		default:  IMAGEVIEW_TAG = "Alex " + " likes " +  " reading ";;
	   }
		 
		setContentView(root); 
		
		
	}*/
	
	public void addListenerOnButton(){
		final Context context = this ;
		
		prevPage.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				upadteInfoViews("useAllSpots",5);
				if(page==2){
					prevPage.setVisibility(View.INVISIBLE);
				}else{
					prevPage.setVisibility(View.VISIBLE);
				}
				nextPage.setVisibility(View.VISIBLE);
				page --;

			}
		});
		
		nextPage.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				page++;
				if(level <= page*5){
					upadteInfoViews("nextLastPage", (level % 5));
				}else{
					//use all 5 spots
				}
				prevPage.setVisibility(View.VISIBLE);
				
		
				switchImagesAround();
			}
		});
		
		ready.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				
				setTouchListeners();
				findViewById(R.id.level3Relative).setOnDragListener(new MyDragListener());
				upadteInfoViews("forGamePlay",0);
				switchImagesAround();
				ready.setVisibility(View.INVISIBLE);
				done.setVisibility(View.VISIBLE);
			}
		});
		
		ProfilePictureView likePic = new ProfilePictureView(this);
		likePic.setProfileId("457041557690681");
		likePic.setPresetSize(ProfilePictureView.NORMAL);
		likePic.setTag(IMAGEVIEW_TAG);
		likeImage.add(likePic);
		root.addView(likePic);
		
		ProfilePictureView profilePic = new ProfilePictureView(this);
		profilePic.setProfileId("1651320295");
		profilePic.setPresetSize(ProfilePictureView.NORMAL);
		profilePic.setOnTouchListener(new MyTouchListener());
		profilePic.setTag(IMAGEVIEW_TAG);
		profileImage.add(profilePic);
		root.addView(profilePic);
		
		 		
		setContentView(root);
		
		done.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent continueTO = new Intent(context,Result.class);
				continueTO.putExtra("sender", "three");
				startActivity(continueTO);
				finish();
			}
		});
		
	}
	
	private final class MyTouchListener implements OnTouchListener {
		  public boolean onTouch(View view, MotionEvent motionEvent) {
		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		      ClipData data = ClipData.newPlainText("", "");
		      DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
		      view.startDrag(data, shadowBuilder, view, 0);
		      view.setVisibility(View.INVISIBLE);
		      return true;
		    } else {
		    	return false;
		    }
		  }

	}
	private final class MyDragListener implements OnDragListener  {
			 
		  public boolean onDrag(View view,  DragEvent event){		  		
			  	 switch(event.getAction())                   
		         {
		            case DragEvent.ACTION_DRAG_ENTERED: 
		            	System.out.println("im here3");
		            	return true;
		            case DragEvent.ACTION_DRAG_EXITED :
		            	 return true;
		            case DragEvent.ACTION_DRAG_STARTED:
	                    return true;
		            case DragEvent.ACTION_DRAG_LOCATION:
	                    //v.setVisibility(View.VISIBLE);
	                    return false;
		            case DragEvent.ACTION_DROP:
		            	 View dragView = (View) event.getLocalState();
		               //RelativeLayout containView = (RelativeLayout) view;
		               //containView.addView(dragView);
		              
		               System.out.println("im here4");
		           				               
			               dropX = event.getX();
			               dropY = event.getY() ;
			               
			               dragView.setX(dropX - dragView.getWidth() / 2 );
			               dragView.setY(dropY - dragView.getHeight() / 2);
			               
			               dragView.bringToFront();
			               dragView.setVisibility(View.VISIBLE);     
		               
		               break;
		            case DragEvent.ACTION_DRAG_ENDED   :
		            	View dragView1 = (View) event.getLocalState();
			               System.out.println("im here5");
			              
			               view.setVisibility(View.VISIBLE);
			               
			           break;
		            default: return true;
		            }
		            return true;
		         }
		  
	}
	
	private boolean dropEventNotHandled(DragEvent dragEvent) {
        return !dragEvent.getResult();
	}
	
	private void switchImagesAround(){
		item1.setImageResource(R.drawable.music);
		item2.setImageResource(R.drawable.biking);
		item3.setImageResource(R.drawable.pizza);
		item4.setImageResource(R.drawable.painting);
		item5.setImageResource(R.drawable.photography);
	}
	
	private void upadteInfoViews(String callingLocation, int numBoxesNeeded){
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
			for(int i=0; i<5; i++){
				heartArray[i].setVisibility(View.INVISIBLE);
				statementArray[i].setVisibility(View.INVISIBLE);
			}
		}
	}
	
	private void setTouchListeners(){
		for(int i=0; i<5; i++){
			personArray[i].setOnTouchListener(new  MyTouchListener());
		}
	}
	
	private void setDragListeners(){
		for(int i=0; i<5; i++){
			itemArray[i].setOnTouchListener(new  MyTouchListener());
		}
	}
	
	private final class MyTouchListener implements OnTouchListener {
		  public boolean onTouch(View view, MotionEvent motionEvent) {
		    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
		      ClipData.Item item = new ClipData.Item((CharSequence) view.getTag());
		      
		      String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
		      
		      ClipData dragData = new ClipData(view.getTag().toString(),mimeTypes,item);
		      
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
			  	 switch(event.getAction())                   
		         {
		            case DragEvent.ACTION_DRAG_ENTERED:
		            	System.out.println("im here1");
		            	return true;
		            case DragEvent.ACTION_DRAG_EXITED :
		            	System.out.println("im here2");
		            	 return true;
		            case DragEvent.ACTION_DRAG_STARTED:
		            	System.out.println("im here3");
		            	if (isSet == true){
		            		levelScore --;
		            		 Game_Level.totalScore --;
		            		isSet = false ;
		            		System.out.println(levelScore);
		            	}
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
				            	 if (targetText.equals(incomingText)){
				            		 System.out.println("we have match"); 
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

		private void assignVariables(){
		
		done = (Button)findViewById(R.id.button1);
		ready = (Button)findViewById(R.id.imReady);
		nextPage = (Button) findViewById(R.id.nextPage);
		prevPage = (Button) findViewById(R.id.prevPage);
		levelName = (TextView) findViewById(R.id.levelNum);
		
		person1 = (ImageView) findViewById(R.id.person1);
		person2 = (ImageView) findViewById(R.id.person2);
		person3 = (ImageView) findViewById(R.id.person3);
		person4 = (ImageView) findViewById(R.id.person4);
		person5 = (ImageView) findViewById(R.id.person5);
		
		heart1 = (ImageView) findViewById(R.id.likes1);
		heart2 = (ImageView) findViewById(R.id.likes2);
		heart3 = (ImageView) findViewById(R.id.likes3);
		heart4 = (ImageView) findViewById(R.id.likes4);
		heart5 = (ImageView) findViewById(R.id.likes5);
		
		item1 = (ImageView) findViewById(R.id.item1);
		item2 = (ImageView) findViewById(R.id.item2);
		item3 = (ImageView) findViewById(R.id.item3);
		item4 = (ImageView) findViewById(R.id.item4);
		item5 = (ImageView) findViewById(R.id.item5);
		
		statement1 = (TextView) findViewById(R.id.statement1);
		statement2 = (TextView) findViewById(R.id.statement2);
		statement3 = (TextView) findViewById(R.id.statement3);
		statement4 = (TextView) findViewById(R.id.statement4);
		statement5 = (TextView) findViewById(R.id.statement5);
		
}

	private void populateArrays(){
	
		personArray[0] = person1;
		personArray[1] = person2;
		personArray[2] = person3;
		personArray[3] = person4;
		personArray[4] = person5;
		
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
		
		itemArray[0] = item1;
		itemArray[1] = item2;
		itemArray[2] = item3;
		itemArray[3] = item4;
		itemArray[4] = item5;
}




}
