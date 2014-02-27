package com.example.acememo;

import com.example.acememo.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
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

public class Level_One extends Activity {
	
	private android.widget.RelativeLayout.LayoutParams layoutParams;
	String msg;
	int windowwidth;
	int windowheight;   
	ImageView image1, heart, likesImage;
	TextView statement, buttonTutorial, actionTutorial;
	Button ready,done;
	float dropX, dropY;
	private static final String IMAGEVIEW_TAG = "Emir likes pizza";
			
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_one);
			
        image1 = (ImageView) findViewById(R.id.imageView1);
        heart = (ImageView) findViewById(R.id.likes);
        likesImage = (ImageView) findViewById(R.id.whatTheyLike1);
        statement = (TextView) findViewById(R.id.statement1);
        done = (Button)findViewById(R.id.button1);
        buttonTutorial = (TextView) findViewById(R.id.buttonTut);
        actionTutorial = (TextView) findViewById(R.id.turotialInstructionAction);
        done.setVisibility(View.INVISIBLE);
        
        image1.setTag(IMAGEVIEW_TAG);
        likesImage.setTag(IMAGEVIEW_TAG);
        
		addListenerOnButton();
	}
	
	public void addListenerOnButton(){
		final Context context = this ;
		
		ready = (Button) findViewById(R.id.imReady);
		ready.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				 image1.setOnTouchListener(new  MyTouchListener());
			       findViewById(R.id.levelOneRelative).setOnDragListener(new MyDragListener());
				heart.setVisibility(View.INVISIBLE);
				statement.setVisibility(View.INVISIBLE);
				ready.setVisibility(View.INVISIBLE);
				done.setVisibility(View.VISIBLE);
				buttonTutorial.setText(R.string.clickButtonDone);
				actionTutorial.setText(R.string.instr2);
			}
		});
		
		
		done.setOnClickListener (new OnClickListener() {
			
			@Override
			public void onClick(View arg0){
				Intent continueTO = new Intent(context,Continue_TO.class);
				continueTO.putExtra("sender", "one");
				startActivity(continueTO);
				finish();
			}
		});
		
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
		            	 ClipData.Item item = event.getClipData().getItemAt(0); 
		            	 
		            	 String incomingText = item.getText().toString();
		            	 String targetText = likesImage.getTag().toString();
		            	 	            	 		               
		               dropX = event.getX();
		               dropY = event.getY();
		                     		               
		               dragView.setX(dropX - dragView.getWidth() / 2 );
		               dragView.setY(dropY - dragView.getHeight() / 2);
		               
		               dragView.bringToFront();
		               dragView.setVisibility(View.VISIBLE);  
		               
		               int[] location = new int[2];
		               likesImage.getLocationOnScreen(location);
		               
		               int x = location[0];
		               int y = location[1];
		               
		               int width = likesImage.getWidth();
		               int height = likesImage.getHeight();
		               
		               if(x <= dropX && dropX <= (x + width) && (y - height) <= dropY && dropY <= y){
			            	 if (targetText.equals(incomingText)){
			            		 System.out.println("we have match");    
			            	 }else{
			            		 System.out.println("no match found");
			            	 }
		               }
		               
		               break;
		            case DragEvent.ACTION_DRAG_ENDED   :
		            	View dragView1 = (View) event.getLocalState();
			               System.out.println("im here5");
			              
			               dragView1.setVisibility(View.VISIBLE);
			               
			           break;
		            default: return true;
		            }
		            return true;
		         }
		  
	}
	
	private boolean dropEventNotHandled(DragEvent dragEvent) {
        return !dragEvent.getResult();
	}
	
	
	
	
	
}
