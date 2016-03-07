package com.bilig.ugted.activity;

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
import com.bilig.ugted.thread.MyGetFileThread;
import com.bilig.ugted.util.CommonUtils;
import com.bilig.ugted.util.MyJson;

public class MainActivity extends BaseActivity {

	// 实现tabhost
	private TextView bestTedContent;
	private TextView newestTedContent;

	public MainBtnListener mbl = null;
	private UpdateInfo updateInfo = new UpdateInfo();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_main);

		initView();

		// 默认 fragment
		mbl.selectFragment(100);
		
		ThreadPoolUtils.execute(new MyGetFileThread(hand, Model.VERSION_URL));

	}
	
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 404) {
				Toast.makeText(MainActivity.this, "最新版本不存在", 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(MainActivity.this, "最新版，请期待", 1).show();
			} else if (msg.what == 200) {
				String result = (String) msg.obj;
				if (result != null) {
					updateInfo=MyJson.getUpdateInfo(result);
					TedConfig.serverVersion = updateInfo.getVersion();
					TedConfig.newApkName=CommonUtils.getNameFromUrl(updateInfo.getUrl());
					checkUpdate();
				}else{
					Toast.makeText(MainActivity.this, "最新版，请期待", 1).show();
				}
			}else{
				Toast.makeText(MainActivity.this, "最新版，请期待", 1).show();
			}
			
		};
	};
	
	private void checkUpdate(){
		if (TedConfig.localVersion < TedConfig.serverVersion) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("软件升级")
					.setMessage("发现新版本，建议您立即更新使用.")
					.setPositiveButton("更新",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent=new Intent(MainActivity.this, UpdateActivity.class);
									MainActivity.this.startActivity(intent);
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
			alert.setCancelable(false);
			alert.create().show();
		}
	}

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

}
