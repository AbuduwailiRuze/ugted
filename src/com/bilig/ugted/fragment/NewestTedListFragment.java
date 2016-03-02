package com.bilig.ugted.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bilig.ugted.activity.R;
import com.bilig.ugted.adapter.ListItemAdapter;
import com.bilig.ugted.config.TedConfig;
import com.bilig.ugted.domain.MyIteam;
import com.bilig.ugted.listener.MyOnItemClickListener;
import com.bilig.ugted.model.Model;
import com.bilig.ugted.net.ThreadPoolUtils;
import com.bilig.ugted.thread.HttpGetThread;
import com.bilig.ugted.util.MyJson;

public class NewestTedListFragment extends Fragment {
	
	
	
	private View view;
	private ListView lv_list;
	private LinearLayout mLinearLayout, load_progressBar;
	
	private MyOnItemClickListener itemClickListener;
	private List<MyIteam> itemList=new ArrayList<MyIteam>();
	private ListItemAdapter adapter;
	
	
	private Context ctx;
	private int mStart = 0;
	private int mEnd = 5;
	private String url = null;
	private boolean loadflag = false;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.newest_content, container, false);
		this.ctx=view.getContext();
		initView();
		return view;
	}
	
	private void initView() {
		load_progressBar = (LinearLayout) view.findViewById(R.id.load_progressBar);
		mLinearLayout = (LinearLayout) view.findViewById(R.id.ll_frame_content);
		lv_list=(ListView) view.findViewById(R.id.lv_bestcontent_listview);
		
		adapter=new ListItemAdapter(ctx, itemList);
		
		itemClickListener=new MyOnItemClickListener(this.getActivity(),itemList);
		lv_list.setOnItemClickListener(itemClickListener);
		
		
		url=Model.BEST_TED+TedConfig.NEWEST_TED_GET_PARAM+"="
				+TedConfig.NEWEST_TED_GET_CODE+ "&start=" + mStart + "&end=" + mEnd;
		ThreadPoolUtils.execute(new HttpGetThread(hand, url));
		
		lv_list.setAdapter(adapter);
		
	
	}
	
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 404) {
				Toast.makeText(ctx, "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(ctx, "传输失败", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				if (result != null) {
					System.out.println(result);
					List<MyIteam> list=MyJson.getNewestList(result);
					if (list.size() == 5) {
						mStart += 5;
						mEnd += 5;
					} else if(list.size()==0){
						if(itemList.size()==0){
							Toast.makeText(ctx, "已经没有了...", 1).show();
						}
					}else{
						
						if (!loadflag) {
							itemList.removeAll(itemList);
						}
						for (MyIteam mi:list) {
							itemList.add(mi);
						}
					}
					
					mLinearLayout.setVisibility(View.VISIBLE);
					load_progressBar.setVisibility(View.GONE);
					adapter.notifyDataSetChanged();
					loadflag = true;
				}
				
			}else{
				Toast.makeText(ctx, "加载失败！", 1).show();
			}
		};
	};
	
}