package com.example.acememo;

import com.example.acememo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class New_Level extends Activity {
	private int level;
	private LinearLayout root;
	private LinearLayout ll;
	private LinearLayout.LayoutParams widgetParams;
	private LayoutParams containerParams;
	
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
		ll.setOrientation(LinearLayout.VERTICAL);
		ll.setLayoutParams(containerParams);
		root.addView(ll);
		
		level = Game_Level.levelNumber;
		// System.out.println(level);
		
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
		
		
	}

}
