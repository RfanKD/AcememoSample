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
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;

public class New_Level extends Activity {
	private int level;
	private String userId;
	float dropX, dropY;
	
	private LinearLayout root;
	private LinearLayout ll;
	private LinearLayout.LayoutParams widgetParams;
	private LayoutParams containerParams;
	
	private ArrayList<ProfilePictureView> likeImage;
	private ArrayList<ProfilePictureView> profileImage;
	
//	private ProfilePictureView likePic;
//	private ProfilePictureView profilePic;
	
	//private ProfilePictureView profilePic;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_level);
		
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
		ll.setOrientation(LinearLayout.HORIZONTAL);
		ll.setLayoutParams(widgetParams);
		root.addView(ll);
		root.setOnDragListener(new MyDragListener());
		
		level = Game_Level.levelNumber;
		// System.out.println(level);
		userId = FacebookLogin.user_id;
		System.out.println(userId);
		
		likeImage = new ArrayList<ProfilePictureView>();
		profileImage = new ArrayList<ProfilePictureView>();
		
		for (int i = 1; i <= level; i ++){
			createImageView (level, i);
		}
		 
		
		addListenerOnButton();
	}

	private void addListenerOnButton() {
		// TODO Auto-generated method stub
		final Context context = this ;
		
	}
	
	@SuppressWarnings("deprecation")
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
			              
			               dragView1.setVisibility(View.VISIBLE);
			               
			           break;
		            default: return true;
		            }
		            return true;
		         }
		  
	}

}
