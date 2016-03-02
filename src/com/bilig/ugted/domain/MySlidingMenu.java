package com.bilig.ugted.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bilig.ugted.activity.AboutActivity;
import com.bilig.ugted.activity.CollectActivity;
import com.bilig.ugted.activity.DownloadActivity;
import com.bilig.ugted.activity.LoginActivity;
import com.bilig.ugted.activity.MainActivity;
import com.bilig.ugted.activity.MainMenuActivity;
import com.bilig.ugted.activity.R;
import com.bilig.ugted.activity.SettingActivity;
import com.bilig.ugted.activity.TranslaterActivity;
import com.bilig.ugted.activity.UpdateActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MySlidingMenu implements OnItemClickListener {

	private static Activity activity;
	private SlidingMenu slidingMenu;

	private Intent mainMenu;
	private Intent login;
	private Intent translate;
	private Intent download;
	private Intent about;
	private Intent setting;
	private Intent collect;
	private Intent update;
	
	
	private ImageView loginItem;
	
	List<Map<String, Object>> data;

	public MySlidingMenu(Activity activity) {
		this.activity = activity;
		this.data=getData();

	}

	public void showSlidingMenu() {
		slidingMenu = new SlidingMenu(activity);
												
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// slidingMenu.setShadowWidthRes(R.dimen.sliding_menu_ShadowWidth);
		// slidingMenu.setShadowDrawable(R.drawable.ic_launcher);

		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(activity, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.slidingmenu);
		
		loginItem = (ImageView) activity.findViewById(R.id.goto_login);
		
		
		loginItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(login == null){
					login = new Intent(activity, LoginActivity.class);
				}
				activity.startActivity(login);

			}
		});
		setDataToSliding();
	}

	private void setDataToSliding() {
		ListView menuListView = (ListView) activity
				.findViewById(R.id.lv_leftslider);
		
		String[] arrStr=new String[] { "title", "img" };
		int[] arrInt=new int[] { R.id.tv_slider_item_title, R.id.iv_slider_item_icon };
		SimpleAdapter adapter = new SimpleAdapter(
				activity,data,R.layout.slidemenu_listitem,arrStr,arrInt);
		menuListView.setAdapter(adapter);
		menuListView.setOnItemClickListener(this);
	}

	public void setToggle(boolean flag) {
		slidingMenu.toggle(flag);
	}

	private static List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		String item=activity.getResources().getString(R.string.item_catagory);
		map.put("title", item);
		map.put("img", R.drawable.slidemenu_listitem_ic01);
		list.add(map);
		
		map = new HashMap<String, Object>();
		item=activity.getResources().getString(R.string.item_transalter);
		map.put("title", item);
		map.put("img", R.drawable.slidemenu_listitem_ic01);
		list.add(map);
		
		map = new HashMap<String, Object>();
		item=activity.getResources().getString(R.string.item_aboutTed);
		map.put("title", item);
		map.put("img", R.drawable.slidemenu_listitem_ic01);
		list.add(map);
		
		map = new HashMap<String, Object>();
		item=activity.getResources().getString(R.string.item_collect);
		map.put("title", item);
		map.put("img", R.drawable.slidemenu_listitem_ic01);
		list.add(map);
		
		map = new HashMap<String, Object>();
		item=activity.getResources().getString(R.string.item_download);
		map.put("title", item);
		map.put("img", R.drawable.slidemenu_listitem_ic01);
		list.add(map);
		map = new HashMap<String, Object>();
		item=activity.getResources().getString(R.string.item_setting);
		map.put("title", item);
		map.put("img", R.drawable.slidemenu_listitem_ic01);
		list.add(map);
		map = new HashMap<String, Object>();
		item=activity.getResources().getString(R.string.item_update);
		map.put("title", item);
		map.put("img", R.drawable.slidemenu_listitem_ic01);
		list.add(map);
		
		
		
		
		return list;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int itemid,
			long arg3) {
		// TODO Auto-generated method stub
		switch (itemid) {
			case 0:
				setToggle(false);
				if (mainMenu == null) {
					mainMenu = new Intent(activity, MainMenuActivity.class);
				}
				activity.startActivity(mainMenu);
				break;
			case 1:
				setToggle(false);
				if (translate == null) {
					translate = new Intent(activity, TranslaterActivity.class);
				}
				activity.startActivity(translate);
				break;
			case 2:
				setToggle(false);
				if (about == null) {
					about = new Intent(activity, AboutActivity.class);
				}
				activity.startActivity(about);
				break;
			case 3:
				setToggle(false);
				if (collect == null) {
					collect = new Intent(activity, CollectActivity.class);
				}
				activity.startActivity(collect);
				break;
			case 4:
				setToggle(false);
				if (download == null) {
					download = new Intent(activity, DownloadActivity.class);
				}
				activity.startActivity(download);
				break;
			case 5:
				setToggle(false);
				if (setting == null) {
					setting = new Intent(activity, SettingActivity.class);
				}
				activity.startActivity(setting);
				break;
			case 6:
				setToggle(false);
				if (update == null) {
					update = new Intent(activity, UpdateActivity.class);
				}
				activity.startActivity(update);
				break;
				
		}
		if(!(activity instanceof MainActivity)){
			activity.finish();
		}
		
	}

}
