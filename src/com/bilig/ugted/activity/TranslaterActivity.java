package com.bilig.ugted.activity;

import android.os.Bundle;

import com.bilig.ugted.domain.HeadTitle;

public class TranslaterActivity extends BaseActivity {

	
	@Override
	protected HeadTitle getHeadTitle() {
		return new HeadTitle(this,R.string.item_transalter,R.id.iv_head_title);
	}
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0,R.layout.list);
	}

}
