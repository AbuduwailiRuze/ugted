package com.bilig.ugted.activity;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import com.bilig.ugted.config.TedConfig;
import com.bilig.ugted.domain.HeadTitle;
import com.bilig.ugted.domain.UpdateInfo;
import com.bilig.ugted.model.Model;
import com.bilig.ugted.net.ThreadPoolUtils;
import com.bilig.ugted.net.UpdateInfoService;
import com.bilig.ugted.thread.MyGetFileThread;
import com.bilig.ugted.util.CommonUtils;
import com.bilig.ugted.util.MyJson;

public class UpdateActivity extends BaseActivity {

	
	private UpdateInfo updateInfo=new UpdateInfo();
	
	@Override
	protected HeadTitle getHeadTitle() {
		return new HeadTitle(this,R.string.item_update,R.id.iv_head_title);
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0,R.layout.update);
		
		ThreadPoolUtils.execute(new MyGetFileThread(hand, Model.VERSION_URL));
	}
	@SuppressLint("HandlerLeak") 
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 404) {
				Toast.makeText(UpdateActivity.this, "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(UpdateActivity.this, "传输失败", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				if (result != null) {
					updateInfo=MyJson.getUpdateInfo(result);
					TedConfig.serverVersion = updateInfo.getVersion();
					TedConfig.newApkName=CommonUtils.getNameFromUrl(updateInfo.getUrl());
					checkVersion();
				}else{
					Toast.makeText(UpdateActivity.this, "加载失败！", 1).show();
				}
			}else{
				Toast.makeText(UpdateActivity.this, "加载失败！", 1).show();
			}
			
		};
	};
	
	/**
	 * 检查版本更新
	 */
	public void checkVersion() {
		//获取service的版本号
		// 判断本地版本是否小于服务器端的版本号
		System.out.println("Local"+TedConfig.localVersion+"  serv:"+TedConfig.serverVersion);
		if (TedConfig.localVersion < TedConfig.serverVersion) {
			// 发现新版本，提示用户更新
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("软件升级")
					.setMessage("发现新版本，建议您立即更新使用.")
					.setPositiveButton("更新",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									if (Environment.getExternalStorageState().equals(
											Environment.MEDIA_MOUNTED)){
									downFile(Model.HTTPURL+updateInfo.getUrl());
									}else{
										Toast.makeText(UpdateActivity.this, "SD卡不可用，请插入SD卡",
												Toast.LENGTH_SHORT).show();
									}
									
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							});
			alert.create().show();
		} else {
			// 清理工作，略去
			cheanUpdateFile(TedConfig.newApkName);
		}
	}
	
	private void downFile(final String url) { 
		UpdateInfoService updateInfoService=new UpdateInfoService(UpdateActivity.this);
		ProgressDialog progressDialog = new ProgressDialog(UpdateActivity.this);    //进度条，在下载的时候实时更新进度，提高用户友好度
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setTitle("正在下载");
		progressDialog.setMessage("请稍候...");
		progressDialog.setProgress(0);
		progressDialog.show();
		updateInfoService.downLoadFile(url, progressDialog,hand);
		
	}
	
	/**
	 * 清理缓存的下载文件
	 */
	private void cheanUpdateFile(String apkName) {
		File updateFile = new File(Environment.getExternalStorageDirectory(), apkName);
		if (updateFile.exists()) {
			// 当不需要的时候，清除之前的下载文件，避免浪费用户空间
			updateFile.delete();
		}
	}
}
