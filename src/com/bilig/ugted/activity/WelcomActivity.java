package com.bilig.ugted.activity;

import com.bilig.ugted.activity.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Toast;

public class WelcomActivity extends Activity {
	
	protected boolean _active = true;
    protected int _splashTime = 3000;
    private ConnectivityManager manager =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.welcome);
    	checkNetworkState();
      	
     }
    
    private void gotoMain(final Class clazz){
    	Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while(_active && (waited < _splashTime)) {
                        sleep(100);
                        if(_active) {
                            waited += 100;
                        }
                    }
                } catch(InterruptedException e) {
                    // do nothing
               	 throw new RuntimeException(e);
                } finally {
                   redirectTo(clazz);
                }
            }
        };
        splashTread.start();
    }
    
     @Override
     public boolean onTouchEvent(MotionEvent event) {
         if (event.getAction() == MotionEvent.ACTION_DOWN) {
             _active = false;
         }
         return true;
     }
     
     private void redirectTo(Class clazz) {
 		startActivity(new Intent(getApplicationContext(), clazz));
 		finish();
 	}
    
     /*
      * 判断是否立案网
      */
     private boolean checkNetworkState() {  
         boolean flag = false;  
         //得到网络连接信息  
         manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);  
         //去进行判断网络是否连接  
         if (manager.getActiveNetworkInfo() != null) {  
             flag = manager.getActiveNetworkInfo().isAvailable();  
         }  
         if (!flag) {  
             setNetwork();  
         } else {  
             isNetworkAvailable();  
         }  
   
         return flag;  
     } 
     /** 
      * 网络未连接时，调用设置方法 
      */  
     private void setNetwork(){  
         Toast.makeText(this, "wifi is closed!", Toast.LENGTH_SHORT).show();  
           
         AlertDialog.Builder builder = new AlertDialog.Builder(this);  
         builder.setIcon(R.drawable.ic_launcher);  
         builder.setTitle("网络提示信息");  
         builder.setMessage("网络不可用，如果继续，请先设置网络！");  
         builder.setPositiveButton("设置", new OnClickListener() {  
             @Override  
             public void onClick(DialogInterface dialog, int which) {  
                 Intent intent = null;  
                 /** 
                  * 判断手机系统的版本！如果API大于10 就是3.0+ 
                  * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同 
                  */  
                 if (android.os.Build.VERSION.SDK_INT > 10) {  
                     intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);  
                 } else {  
                     intent = new Intent();  
                     ComponentName component = new ComponentName(  
                             "com.android.settings",  
                             "com.android.settings.WirelessSettings");  
                     intent.setComponent(component);  
                     intent.setAction("android.intent.action.VIEW");  
                 }  
                 startActivity(intent);  
             }  
         });  
   
         builder.setNegativeButton("取消", new OnClickListener() {  
             @Override  
             public void onClick(DialogInterface dialog, int which) {  
            	gotoMain(DownloadListActivity.class);
             }  
         });  
         builder.create();  
         builder.show();  
     }  
     /** 
      * 网络已经连接，然后去判断是wifi连接还是GPRS连接 
      * 设置一些自己的逻辑调用 
      */  
     private void isNetworkAvailable(){  
           
         State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();  
         State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();  
         if(gprs == State.CONNECTED || gprs == State.CONNECTING){  
             Toast.makeText(this, "wifi is open! gprs", Toast.LENGTH_SHORT).show();
             gotoMain(MainActivity.class);
         }  
         //判断为wifi状态下才加载广告，如果是GPRS手机网络则不加载！  
         if(wifi == State.CONNECTED || wifi == State.CONNECTING){  
             Toast.makeText(this, "wifi is open! wifi", Toast.LENGTH_SHORT).show();
             gotoMain(MainActivity.class);
         }  
           
     }

}
