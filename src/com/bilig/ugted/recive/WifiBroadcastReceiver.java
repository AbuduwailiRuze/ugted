package com.bilig.ugted.recive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

public class WifiBroadcastReceiver extends BroadcastReceiver {
	
	
	public static final String WIFI_STATE_CHANGED = "android.net.wifi.WIFI_STATE_CHANGED";
	private WifiManager manger;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		boolean flag=false;
		context.getSystemService(Context.WIFI_SERVICE);
		String action = intent.getAction();
		if (WIFI_STATE_CHANGED.equals(action)) {
			 flag = manger.isWifiEnabled();
			 Log.i("waili", "wifi---->"+flag);
			 if(!flag){
				 Log.i("waili", "wifi---->"+"wifi关闭");
				 Toast.makeText(context, "wifi关闭状态", Toast.LENGTH_LONG);
			 }else{
				 Log.i("waili", "wifi---->"+"wifi已开启");
				 Toast.makeText(context, "wifi已开启", Toast.LENGTH_LONG);
			 }
		}else if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
			
			Log.i("waili", "wifi---->"+"wifi链接状态");
			 Toast.makeText(context, "wifi链接状态", Toast.LENGTH_LONG);
			 ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			 NetworkInfo wifiNetInfo = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			 if (wifiNetInfo.isConnected() ) {
                 System.out.println("WIFI成功连接了。");
                 // 【【注意看这里】】 蛤蟆问题是 WIFI连接成功能监听到，但是会吐司四次。这里要怎么做，成功之后才会吐司一次。
                 Toast.makeText(context, "WIFI已经连接上", Toast.LENGTH_LONG).show();
         
         } else {
                 Toast.makeText(context, "WIFI出现异常", Toast.LENGTH_LONG).show();
         }
			 
			 
			 
			 
		}
		
	}

}
