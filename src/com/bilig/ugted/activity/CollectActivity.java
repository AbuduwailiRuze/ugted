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

public class CollectActivity extends BaseActivity {

	
	private ListView lv_list;
	private MyOnItemClickListener itemClickListener;
	private List<MyIteam> list=new ArrayList<MyIteam>();
	
	@Override
	protected HeadTitle getHeadTitle() {
		// TODO Auto-generated method stub
		return new HeadTitle(this,R.string.item_collect,R.id.iv_head_title);
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0,R.layout.list);
		
		initList();
	}
	
	private void initList() {
		itemClickListener=new MyOnItemClickListener(this,list);
		
		
		lv_list=(ListView) this.findViewById(R.id.lv_list_listview);
		ListItemAdapter adapter=new ListItemAdapter(this,list);
		lv_list.setAdapter(adapter);
		
		lv_list.setOnItemClickListener(itemClickListener);
		
	}



	

}
