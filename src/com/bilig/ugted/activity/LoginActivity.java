package com.bilig.ugted.activity;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.bilig.ugted.config.TedConfig;
import com.bilig.ugted.domain.HeadTitle;
import com.bilig.ugted.listener.LoginBtnListener;

public class LoginActivity extends BaseActivity {
	
	private LoginBtnListener lbl;
	
	private Button btnSubmit;
	private Button btnRegister;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState,R.layout.login);
		initLogin();
    
	}
	
	private void initLogin(){
    	
		lbl=new LoginBtnListener(this);
		
		btnSubmit=(Button) this.findViewById(R.id.btn_login);
		btnRegister=(Button) this.findViewById(R.id.btn_reg);
		
		btnSubmit.setOnClickListener(lbl);
		btnRegister.setOnClickListener(lbl);
	}
	

	@Override
	protected HeadTitle getHeadTitle() {
		// TODO Auto-generated method stub
		return new HeadTitle(this,R.string.login,R.id.iv_head_title);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode ==TedConfig.REQUEST_CODE &&resultCode == TedConfig.RESULT_CODE && data != null ){
			String username=data.getStringExtra(TedConfig.USER_NAME);
			EditText et_username=(EditText) this.findViewById(R.id.et_username);
			et_username.setText(username);
		}
	}
	
	

}
