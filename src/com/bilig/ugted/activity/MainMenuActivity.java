package com.bilig.ugted.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bilig.ugted.domain.HeadTitle;


public class MainMenuActivity extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState,R.layout.main_menu);
		
	}
	
	public void clickMenuItem(View view){
		
		Intent intent=new Intent(this,ListActivity.class);
		this.startActivity(intent);
		
	}
	
	
	@Override
	protected HeadTitle getHeadTitle() {
		// TODO Auto-generated method stub
		return new HeadTitle(this,R.string.item_catagory,R.id.iv_head_title);
	}
	
}
