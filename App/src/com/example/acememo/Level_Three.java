package com.example.acememo;

import com.example.acememo.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class Level_Three extends Activity{

		
		private android.widget.RelativeLayout.LayoutParams layoutParams;
		String msg;
		int windowwidth;
		int windowheight;   
		ImageView person1, person2, person3, person4, person5, person6;
		ImageView heart1, heart2, heart3, heart4, heart5, heart6;
		ImageView item1, item2, item3, item4, item5, item6;
		TextView statement1, statement2, statement3, statement4, statement5, statement6;
		Button ready, done;
		
		ImageView[] personArray = new ImageView[6];
		ImageView[] heartArray = new ImageView[6];
		TextView[] statementArray = new TextView[6];
		
		
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.level_three);
			assignVariables();
			populateArrays();
	        //findViewById(R.id.level3Relative).setOnDragListener(new MyDragListener());
	        done.setVisibility(View.INVISIBLE);
			addListenerOnButton();
		}
		
		public void addListenerOnButton(){
			final Context context = this ;
			
			ready = (Button) findViewById(R.id.imReady);
			ready.setOnClickListener (new OnClickListener() {
				
				@Override
				public void onClick(View arg0){
					
					setTouchListeners();
					removeUnusedViews();
					switchImagesAround();
					ready.setVisibility(View.INVISIBLE);
					done.setVisibility(View.VISIBLE);
				}
			});
			
			
			done.setOnClickListener (new OnClickListener() {
				
				@Override
				public void onClick(View arg0){
					Intent continueTO = new Intent(context,Continue_TO.class);
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
			            	return false;
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
		
		private void removeUnusedViews(){
			
			for(int i=0; i<5; i++){
				heartArray[i].setVisibility(View.INVISIBLE);
				statementArray[i].setVisibility(View.INVISIBLE);
			}
		}
		
		private void setTouchListeners(){
			for(int i=0; i<5; i++){
				personArray[i].setOnTouchListener(new  MyTouchListener());
			}
		}
 
 		private void assignVariables(){
			
			done = (Button)findViewById(R.id.button1);
			ready = (Button)findViewById(R.id.imReady);
			person1 = (ImageView) findViewById(R.id.person1);
			person2 = (ImageView) findViewById(R.id.person2);
			person3 = (ImageView) findViewById(R.id.person3);
			person4 = (ImageView) findViewById(R.id.person4);
			person5 = (ImageView) findViewById(R.id.person5);
			//person6 = (ImageView) findViewById(R.id.person6);
			
			heart1 = (ImageView) findViewById(R.id.likes1);
			heart2 = (ImageView) findViewById(R.id.likes2);
			heart3 = (ImageView) findViewById(R.id.likes3);
			heart4 = (ImageView) findViewById(R.id.likes4);
			heart5 = (ImageView) findViewById(R.id.likes5);
			//heart6 = (ImageView) findViewById(R.id.likes6);
			
			item1 = (ImageView) findViewById(R.id.item1);
			item2 = (ImageView) findViewById(R.id.item2);
			item3 = (ImageView) findViewById(R.id.item3);
			item4 = (ImageView) findViewById(R.id.item4);
			item5 = (ImageView) findViewById(R.id.item5);
			//item6 = (ImageView) findViewById(R.id.item6);
			
			statement1 = (TextView) findViewById(R.id.statement1);
			statement2 = (TextView) findViewById(R.id.statement2);
			statement3 = (TextView) findViewById(R.id.statement3);
			statement4 = (TextView) findViewById(R.id.statement4);
			statement5 = (TextView) findViewById(R.id.statement5);
			//statement6 = (TextView) findViewById(R.id.statement6);
			
	}
	
		private void populateArrays(){
		
			personArray[0] = person1;
			personArray[1] = person2;
			personArray[2] = person3;
			personArray[3] = person4;
			personArray[4] = person5;
			//personArray[5] = person6;
			
			heartArray[0] = heart1;
			heartArray[1] = heart2;
			heartArray[2] = heart3;
			heartArray[3] = heart4;
			heartArray[4] = heart5;
			//heartArray[5] = heart6;
			
			statementArray[0] = statement1;
			statementArray[1] = statement2;
			statementArray[2] = statement3;
			statementArray[3] = statement4;
			statementArray[4] = statement5;
			//statementArray[5] = statement6;
	}

}
