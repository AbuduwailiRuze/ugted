package com.bilig.ugted.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.bilig.ugted.domain.HeadTitle;
import com.bilig.ugted.domain.MySlidingMenu;

public abstract class BaseActivity extends FragmentActivity{
	
	private MySlidingMenu slidingMenu;
	protected void onCreate(Bundle arg0,int layoutResID){
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(layoutResID);
		
		//侧滑菜单
    	slidingMenu=new MySlidingMenu(this);
    	slidingMenu.showSlidingMenu();
    	
    	getHeadTitle();
		
	}
	
	
	
	public void toggleMenu(View view){
		slidingMenu.setToggle(true);
	}
	
	abstract protected HeadTitle getHeadTitle();


}
