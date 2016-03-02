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

public class ItemActivity extends BaseActivity {
	
	private ListView lv_list;
	private MyOnItemClickListener itemClickListener;
	private List<MyIteam> list=new ArrayList<MyIteam>();
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0, R.layout.item);
		
		initItem();
		
	}
	
	private void initItem(){
		
		
		itemClickListener=new MyOnItemClickListener(this,list);
		
		lv_list=(ListView) this.findViewById(R.id.lv_item_listview);
		ListItemAdapter adapter=new ListItemAdapter(this,list);
		lv_list.setAdapter(adapter);
		
		lv_list.setOnItemClickListener(itemClickListener);
	
	}

	@Override
	protected HeadTitle getHeadTitle() {
		// TODO Auto-generated method stub
		return new HeadTitle(this);
	}
	
	private List<Map<String, Object>> getData() {
		
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("author", "Item");
		map.put("title", "Item��ɵ����");
		map.put("translater", "����");
		map.put("date", "2015-11-11");
		map.put("count", "512");
		map.put("icon", R.drawable.menu_item_ic01);
		
		list.add(map);
		
		
		map.put("author", "Item");
		map.put("title", "Item��ɵ����");
		map.put("translater", "����");
		map.put("date", "2015-11-11");
		map.put("count", "512");
		map.put("icon", R.drawable.menu_item_ic01);
		
		list.add(map);
		// TODO Auto-generated method stub
		return list;
	}

	


}
