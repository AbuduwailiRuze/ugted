package com.bilig.ugted.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.letv.controller.PlayProxy;
import com.letv.simple.utils.LetvParamsUtils;
import com.letv.simple.utils.LetvSimplePlayBoard;
import com.letv.skin.utils.ScreenUtils;
import com.letv.skin.v4.V4PlaySkin;
import com.letv.universal.iplay.ISplayer;

public class PlayActivity extends Activity implements OnClickListener {
	public final static String DATA = "data";

	// /////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////

	private V4PlaySkin skin;
	private LetvSimplePlayBoard playBoard;
	private Bundle bundle;
	private TextView tvCount;
	private Button ibtnDownload;
	private Button ibtnShare;
	private Button btnPlay;
	private String uuid;
	private String vuid;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_acy);
		loadDataFromIntent();// load data
		skin = (V4PlaySkin) findViewById(R.id.videobody);
		playBoard = new LetvSimplePlayBoard();
		playBoard.init(this, bundle, skin);
		
		initBtn();
	}

	private void loadDataFromIntent() {
		Intent intent = getIntent();
		if (intent != null) {
			bundle = intent.getBundleExtra("data");
			if (bundle == null) {
				Toast.makeText(this, "no data", Toast.LENGTH_LONG).show();
			} else {
				uuid = bundle.getString(PlayProxy.PLAY_UUID);
				vuid = bundle.getString(PlayProxy.PLAY_VUID);
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		int screenWidth = ScreenUtils.getWight(this);
        int screenHeight = ScreenUtils.getHeight(this);
        //System.out.println("onResume");
	
		if (playBoard != null) {
			playBoard.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (playBoard != null) {
			playBoard.onPause();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy");
		if (playBoard != null) {
			playBoard.onDestroy();
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//System.out.println("onConfigurationChanged");
		if (playBoard != null) {
			this.findViewById(R.id.ugetd_head_layout).setVisibility(View.GONE);
			playBoard.onConfigurationChanged(newConfig);
		}
	}

	private void initBtn() {

		ibtnShare = (Button) findViewById(R.id.btnLike);
		ibtnDownload = (Button) findViewById(R.id.btnDownload);
		btnPlay = (Button) findViewById(R.id.btn2);
		tvCount = (TextView) findViewById(R.id.tvCount);
		
		ibtnShare.setOnClickListener(this);
		ibtnDownload.setOnClickListener(this);
		btnPlay.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLike:
			Toast.makeText(this, "do you like ?", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btnDownload:
			if (bundle != null) {
				Intent intent = new Intent(this, DownloadListActivity.class);
				intent.putExtra(PlayActivity.DATA, bundle);
				this.startActivity(intent);
			} else {
				Toast.makeText(this, "想多了吧？", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.btn2:
			if (playBoard != null && v.isShown()) {
				ISplayer player = playBoard.getPlayer();
				if (player != null) {
					player.stop();
					player.reset();
					// String uuid = "";
					// String vuid = "";
					// uuid = "2b686d84e3";
					// vuid = "15d2678091";
					player.setParameter(player.getPlayerId(), LetvParamsUtils
							.setVodParams(uuid, vuid, "", "151398", "", false));
					player.prepareAsync();
				}
			}
			break;

		default:
			break;
		}
	}
}
