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
import android.widget.RelativeLayout;

public class Level_One extends Activity {
	
	private android.widget.RelativeLayout.LayoutParams layoutParams;
	String msg;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_one);
		
		findViewById(R.id.imageView1).setOnTouchListener(new MyTouchListener());
		findViewById(R.id.imageView1).setOnDragListener(new MyDragListener());
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
		            case DragEvent.ACTION_DRAG_STARTED:
		               layoutParams = (RelativeLayout.LayoutParams) 
		               view.getLayoutParams();
		               Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
		               // Do nothing
		               break;
		            case DragEvent.ACTION_DRAG_ENTERED:
		               Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
		               int x_cord = (int) event.getX();
		               int y_cord = (int) event.getY();  
		               break;
		            case DragEvent.ACTION_DRAG_EXITED :
		               Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
		               x_cord = (int) event.getX();
		               y_cord = (int) event.getY();
		               layoutParams.leftMargin = x_cord;
		               layoutParams.topMargin = y_cord;
		               view.setLayoutParams(layoutParams);
		               break;
		            case DragEvent.ACTION_DRAG_LOCATION  :
		               Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
		               x_cord = (int) event.getX();
		               y_cord = (int) event.getY();
		               break;
		            case DragEvent.ACTION_DRAG_ENDED   :
		               Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
		               
		               break;
		            case DragEvent.ACTION_DROP:
		               Log.d(msg, "ACTION_DROP event");
		               View dragView = (View) event.getLocalState();
		               dragView.setVisibility(View.VISIBLE);
		               // Do nothing
		               break;
		            default: break;
		            }
		            return true;
		         }
		  
	}
	
	
	
}
