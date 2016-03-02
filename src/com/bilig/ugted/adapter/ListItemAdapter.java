package com.bilig.ugted.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bilig.ugted.activity.R;
import com.bilig.ugted.domain.MyIteam;

public class ListItemAdapter extends BaseAdapter{
	
	private LayoutInflater mInflater;
	private TextView tv_author;
	private TextView tv_title;
	private TextView tv_translater;
	private TextView tv_date;
	private TextView tv_count;
	private List<Map<String, Object>> list;
	private List<MyIteam> itemList;
	
//	public ListItemAdapter(Context context,List<Map<String, Object>> list) {
//		this.mInflater=LayoutInflater.from(context);
//		this.list=list;
//	}
	public ListItemAdapter(Context context,List<MyIteam> itemList) {
		this.mInflater=LayoutInflater.from(context);
		this.itemList=itemList;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		convertView=mInflater.inflate(R.layout.menu_item, null);
		tv_author=(TextView) convertView.findViewById(R.id.tv_listItem_author);
		tv_title=(TextView) convertView.findViewById(R.id.tv_listItem_title);
		tv_translater=(TextView) convertView.findViewById(R.id.tv_listItem_translater);
		tv_date=(TextView) convertView.findViewById(R.id.tv_listItem_date);
		tv_count=(TextView) convertView.findViewById(R.id.tv_listItem_count);
		
		
		tv_author.setText(itemList.get(position).getAuthor());
		tv_title.setText(itemList.get(position).getTitle());
		tv_translater.setText(itemList.get(position).getTranslater());
		tv_date.setText(itemList.get(position).getDate());
		tv_count.setText(itemList.get(position).getCount());
		
		return convertView;
	}

}
