package com.example.acememo;

import com.example.acememo.R;

import android.app.Activity;
import android.content.ClipData;
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

public class New_Level extends Activity {
	private int level, page;
	private String userId;
	ImageView person1, person2, person3, person4, person5, person6;
	ImageView heart1, heart2, heart3, heart4, heart5, heart6;
	ImageView item1, item2, item3, item4, item5, item6;
	TextView levelName, statement1, statement2, statement3, statement4, statement5, statement6;
	Button ready, done, nextPage, prevPage;
	float dropX, dropY;
	
	ImageView[] personArray = new ImageView[6];
	ImageView[] heartArray = new ImageView[6];
	ImageView[] itemArray = new ImageView[6];
	TextView[] statementArray = new TextView[6];
	
	private LinearLayout root;
	private LinearLayout ll;
	private LinearLayout.LayoutParams widgetParams;
	private LayoutParams containerParams;
	
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
		
		
		for (int i = 1; i <= level; i ++){
			//createImageView (level, i);
		}
		 
		
		addListenerOnButton();
	}

/*	
	@SuppressWarnings("deprecation")
	public void createImageView (int totalLevel, int currentLevel){
		
		
		TextView tb = new TextView(this);
		tb.setText("test");
		tb.setLayoutParams(widgetParams);
		switch(currentLevel){
		case 1: tb.setGravity(Gravity.TOP);
				break;
		case 2:  tb.setGravity(Gravity.BOTTOM);
				break;
		case 3:  tb.setGravity(Gravity.CENTER);
				break;
		case 4:  tb.setGravity(Gravity.LEFT);
				break;
		default:  tb.setGravity(Gravity.RIGHT);
	}
		
		ll.addView(tb);
		
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
