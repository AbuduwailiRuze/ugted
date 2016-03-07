package com.bilig.ugted.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.LauncherActivity.ListItem;

import com.bilig.ugted.domain.MyIteam;
import com.bilig.ugted.domain.UpdateInfo;
import com.bilig.ugted.domain.UserInfo;

public class MyJson {

	public static UserInfo getUserInfo(String value) {
		// TODO Auto-generated method stub
		try {
			JSONObject jobj = new JSONObject(value);
			String username = jobj.getString("username");
			String password = jobj.getString("password");
			UserInfo ui = new UserInfo();
			ui.setUsername(username);
			//System.out.println(username+password);
			ui.setPassword(password);
			return ui;
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public static UpdateInfo getUpdateInfo(String value) {
		// TODO Auto-generated method stub
		try {
			
			JSONObject jobj = new JSONObject(value);
			String version = jobj.getString("VERSION");
			String desc = jobj.getString("DESCRIPTION");
			String url = jobj.getString("URL");
			System.out.println("MyJson："+version+desc+url);
			UpdateInfo updateInfo = new UpdateInfo();
			
			updateInfo.setDescription(desc);
			updateInfo.setVersion(Integer.valueOf(version));
			updateInfo.setUrl(url);
			
			return updateInfo;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	public static List<MyIteam> getNewestList(String value){
		List<MyIteam> list=new ArrayList<MyIteam>();
		
		try {
			JSONArray jay=new JSONArray(value);
			for(int i=0;i<jay.length();i++){
				MyIteam item=new MyIteam();
				JSONObject jobj=jay.getJSONObject(i);
				item.setAuthor(jobj.getString("author"));
				item.setTitle(jobj.getString("title"));
				item.setCount(jobj.getString("count"));
				item.setDate(jobj.getString("date"));
				item.setTranslater(jobj.getString("translater"));
				item.setUuid(jobj.getString("uuid"));
				item.setVuid(jobj.getString("vuid"));
				list.add(item);
			}
			return list;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
