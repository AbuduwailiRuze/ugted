package com.bilig.ugted.activity;

import java.io.File;

import android.R.integer;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;

import com.bilig.ugted.config.TedConfig;
import com.bilig.ugted.domain.HeadTitle;
import com.bilig.ugted.domain.UpdateInfo;
import com.bilig.ugted.fragment.BestTedListFragment;
import com.bilig.ugted.fragment.NewestTedListFragment;
import com.bilig.ugted.listener.MainBtnListener;
import com.bilig.ugted.model.Model;
import com.bilig.ugted.net.ThreadPoolUtils;
import com.bilig.ugted.service.UpdateService;
import com.bilig.ugted.thread.MyGetFileThread;
import com.bilig.ugted.util.MyJson;

public class MainActivity extends BaseActivity {

	// 实现tabhost
	private TextView bestTedContent;
	private TextView newestTedContent;

	public MainBtnListener mbl;
	private UpdateInfo updateInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_main);

		initView();

		// 默认 fragment
		mbl.selectFragment(100);
		ThreadPoolUtils.execute(new MyGetFileThread(hand, Model.VERSION_URL));
		checkVersion();

	}
	
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 404) {
				Toast.makeText(MainActivity.this, "找不到地址", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(MainActivity.this, "传输失败", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				if (result != null) {
					updateInfo=MyJson.getUpdateInfo(result);
					TedConfig.serverVersion = Integer.getInteger(updateInfo.getVersion());
				}else{
					Toast.makeText(MainActivity.this, "加载失败！", 1).show();
				}
			}else{
				Toast.makeText(MainActivity.this, "加载失败！", 1).show();
			}
			
		};
	};

	private void initView() {
		mbl = new MainBtnListener(this);

		bestTedContent = (TextView) this.findViewById(R.id.tv_bestTed_content);
		newestTedContent = (TextView) this
				.findViewById(R.id.tv_newestTed_content);
		// 监听
		bestTedContent.setOnClickListener(mbl);
		newestTedContent.setOnClickListener(mbl);

	}

	@Override
	public void onAttachFragment(Fragment fragment) {
		// TODO Auto-generated method stub
		super.onAttachFragment(fragment);
		if (mbl.newestTedsFragment == null
				&& fragment instanceof NewestTedListFragment) {
			mbl.newestTedsFragment = (NewestTedListFragment) fragment;
		} else if (mbl.bestTedsFragment == null
				&& fragment instanceof BestTedListFragment) {
			mbl.bestTedsFragment = (BestTedListFragment) fragment;
		}
	}

	@Override
	protected HeadTitle getHeadTitle() {
		// TODO Auto-generated method stub
		return new HeadTitle(this);
	}

	/**
	 * 检查版本更新
	 */
	public void checkVersion() {
		// 判断本地版本是否小于服务器端的版本号
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
									// 开启更新服务UpdateService
									// 这里为了把update更好模块化，可以传一些updateService依赖的值
									// 如布局ID，资源ID，动态获取的标题,这里以app_name为例
									Intent updateIntent = new Intent(
											MainActivity.this,
											UpdateService.class);
									updateIntent.putExtra("titleId",
											R.string.app_name);
									updateIntent.putExtra("downloadUrl",
											Model.HTTPURL+updateInfo.getUrl());
									System.out.println("Model.HTTPURL+updateInfo.getUrl()");
									startService(updateIntent);
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
			cheanUpdateFile();
		}
	}

	/**
	 * 清理缓存的下载文件
	 */
	private void cheanUpdateFile() {
		File updateFile = new File(TedConfig.downloadDir, getResources()
				.getString(R.string.app_name) + ".apk");
		if (updateFile.exists()) {
			// 当不需要的时候，清除之前的下载文件，避免浪费用户空间
			updateFile.delete();
		}
	}

}
