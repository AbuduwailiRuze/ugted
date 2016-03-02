package com.bilig.ugted.activity;

import android.os.Bundle;

import com.bilig.ugted.domain.HeadTitle;

public class SettingActivity extends BaseActivity {

	@Override
	protected HeadTitle getHeadTitle() {
		// TODO Auto-generated method stub
		return new HeadTitle(this,R.string.item_setting,R.id.iv_head_title);
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0,R.layout.setting);
	}

}
