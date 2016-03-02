package com.bilig.ugted.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.widget.ListView;

import com.bilig.ugted.adapter.ListItemAdapter;
import com.bilig.ugted.domain.HeadTitle;
import com.bilig.ugted.domain.MyIteam;
import com.bilig.ugted.listener.MyOnItemClickListener;


public class DownloadActivity extends BaseActivity {

	private ListView lv_list;
	private MyOnItemClickListener itemClickListener;
	private List<MyIteam> list=new ArrayList<MyIteam>();
	
	@Override
	protected HeadTitle getHeadTitle() {
		return new HeadTitle(this,R.string.item_download,R.id.iv_head_title);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState,R.layout.list);
		
		initList();
	}
	private void initList() {
		itemClickListener=new MyOnItemClickListener(this,list);
		
		
		lv_list=(ListView) this.findViewById(R.id.lv_list_listview);
		ListItemAdapter adapter=new ListItemAdapter(this,list);
		lv_list.setAdapter(adapter);
		
		lv_list.setOnItemClickListener(itemClickListener);
		
	}



	private List<Map<String, Object>> getData() {
		
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("author", "waili");
		map.put("title", "���ص���Ƶ");
		map.put("translater", "����");
		map.put("date", "2015-11-11");
		map.put("count", "512");
		map.put("icon", R.drawable.menu_item_ic01);
		
		list.add(map);
		
		
		map.put("author", "waili");
		map.put("title", "���ص���Ƶ");
		map.put("translater", "����");
		map.put("date", "2015-11-11");
		map.put("count", "512");
		map.put("icon", R.drawable.menu_item_ic01);
		
		list.add(map);
		// TODO Auto-generated method stub
		return list;
	}

}
