package com.bilig.ugted.listener;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.bilig.ugted.activity.MainActivity;
import com.bilig.ugted.activity.PlayActivity;
import com.bilig.ugted.domain.MyIteam;
import com.letv.simple.utils.LetvParamsUtils;



public class MyOnItemClickListener implements OnItemClickListener {

	private Activity activity;
	private List<MyIteam> list;
	public MyOnItemClickListener(Activity activity ,List<MyIteam> list) {
		this.activity=activity;
		this.list=list;
		
	}
	
//    String uuid = "40ff268ca7";
//    String vuid = "6c658686bf";
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
			MyIteam item=list.get(position);
			startLecloudVod(item.getUuid(),item.getVuid());
			if(!(activity instanceof MainActivity)){
				activity.finish();
			}
	}
	
	private void startLecloudVod(String uuid,String vuid) {
        Intent intent;
   
        intent = new Intent(activity, PlayActivity.class);
        //Bundle bundle = LetvParamsUtils.setVodParams(etUUID.getText().toString().trim(), etVUID.getText().toString().trim(), "", "151398", "", false);
        Bundle bundle = LetvParamsUtils.setVodParams(uuid, vuid, "", "151398", "", false);
        intent.putExtra(PlayActivity.DATA, bundle);
        activity.startActivity(intent);
    }

}
