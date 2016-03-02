package com.bilig.ugted.domain;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bilig.ugted.activity.R;

public class HeadTitle {
	
	
	private LinearLayout llHeadTitle;
	private TextView tv_title;
	private ImageView ivTitleIcon;
	
	public HeadTitle(Activity activity) {
		//头部设置
    	llHeadTitle=(LinearLayout) activity	.findViewById(R.id.ll_head_title);
    	llHeadTitle.removeAllViews();
	}
	//头部标题
	public HeadTitle(Activity activity,String str,int iconId) {
		llHeadTitle=(LinearLayout) activity	.findViewById(R.id.ll_head_title);
		ivTitleIcon=(ImageView) activity.findViewById(R.id.iv_head_title);
		tv_title=(TextView) activity.findViewById(R.id.tv_head_title);
		setIcon(iconId);
		setTitle(str);
		
	}
	public HeadTitle(Activity activity,int textId,int iconId) {
		llHeadTitle=(LinearLayout) activity	.findViewById(R.id.ll_head_title);
		ivTitleIcon=(ImageView) activity.findViewById(R.id.iv_head_title);
		tv_title=(TextView) activity.findViewById(R.id.tv_head_title);
		setIcon(iconId);
		setTitle(textId);
		
	}
	
	public void removeTitle() {
		llHeadTitle.removeAllViews();

	}
	
	public void setTitle(String str) {
		// TODO Auto-generated method stub
		tv_title.setText(str);

	}
	public void setTitle(int textId) {
		// TODO Auto-generated method stub
		tv_title.setText(textId);
		
	}
	public void setIcon(int iconId) {
		
		ivTitleIcon.setImageResource(iconId);
		
	}
	
	

}
