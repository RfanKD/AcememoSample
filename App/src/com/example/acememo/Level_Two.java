package com.example.acememo;

import com.example.acememo.R;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Level_Two extends Activity{
	
	Button ready,done;
	ImageView image1, image2, heart1, heart2, likesImage,likesImage2;
	TextView statement1, statement2;

	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_two);
		
		
		 image1 = (ImageView) findViewById(R.id.imageView1);
	     image2 = (ImageView) findViewById(R.id.imageView3);
	     heart1 = (ImageView) findViewById(R.id.imageView5);
	     heart2 = (ImageView) findViewById(R.id.imageView6);
	     statement1 = (TextView) findViewById(R.id.statement1);
	     statement2 = (TextView) findViewById(R.id.statement2);
	     likesImage = (ImageView) findViewById(R.id.imageView2);
	     likesImage2 = (ImageView) findViewById(R.id.imageView4);
	     done = (Button) findViewById(R.id.button1);
	     done.setVisibility(View.INVISIBLE);
	     
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		final Context context = this ;
		// TODO Auto-generated method stub
		ready = (Button) findViewById(R.id.imReady);
		ready.setOnClickListener (new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				heart1.setVisibility(View.INVISIBLE);
				heart2.setVisibility(View.INVISIBLE);
				statement1.setVisibility(View.INVISIBLE);
				statement2.setVisibility(View.INVISIBLE);
				likesImage.setImageResource(R.drawable.biking);
				likesImage2.setImageResource(R.drawable.photography);
				ready.setVisibility(View.INVISIBLE);
				statement1.setVisibility(View.INVISIBLE);
				statement2.setVisibility(View.INVISIBLE);
				done.setVisibility(View.VISIBLE);
				
				image1.setOnTouchListener(new  MyTouchListener());
				image2.setOnTouchListener(new  MyTouchListener());
		        findViewById(R.id.level2Relative).setOnDragListener(new MyDragListener());
			}
		});
			
		done.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent continueTO = new Intent(context,Continue_TO.class);
				continueTO.putExtra("sender", "two");
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
}
