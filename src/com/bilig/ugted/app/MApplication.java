package com.bilig.ugted.app;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

import com.bilig.ugted.config.TedConfig;
import com.example.playerdemo.handler.CrashHandler;
import com.lecloud.config.LeCloudPlayerConfig;
import com.letv.proxy.LeCloudProxy;

import java.util.List;

public class MApplication extends Application {


    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null) {
            for (RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = getProcessName(this, android.os.Process.myPid());
        if (getApplicationInfo().packageName.equals(processName)) {
            //TODO CrashHandler是一个抓取崩溃log的工具类（可选）
            CrashHandler.getInstance(this);
            LeCloudPlayerConfig.getInstance().setDeveloperMode(true).setIsApp().setUseLiveToVod(true);
            LeCloudProxy.init(getApplicationContext());
        }
        
        initGlobal();
    }
    
    /**
	 * 初始化全局变量 实际工作中这个方法中serverVersion从服务器端获取，最好在启动画面的activity中执行
	 */
    
    public void initGlobal() {
		try {
			// 获取本地版本号
			TedConfig.localVersion = getPackageManager().getPackageInfo(
					getPackageName(), 0).versionCode;
			// 假设服务端版本号为2，这个应该是要获取服务器端的版本号的，这里只是假设服务端版本号2
			//TedConfig.serverVersion = 2;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}
}
