package com.bilig.ugted.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bilig.ugted.config.TedConfig;
import com.bilig.ugted.domain.HeadTitle;
import com.bilig.ugted.model.Model;
import com.bilig.ugted.net.ThreadPoolUtils;
import com.bilig.ugted.thread.HttpPostThread;

public class RegActivity extends BaseActivity implements OnClickListener{
	

	private String url = null;
	private String value = null;
	
	private EditText etUserName;
	private EditText etPassword;
	private Button btnReg;
	private Button btnLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState,R.layout.reg);
		
		initReg();
	}

	private void initReg() {
		etUserName=(EditText) this.findViewById(R.id.et_reg_username);
		etPassword=(EditText) this.findViewById(R.id.et_reg_userpwd);
		btnReg=(Button) this.findViewById(R.id.btn_reg_user);
		btnLogin=(Button) this.findViewById(R.id.btn_reg_login);
		btnReg.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_reg_login:
			Intent i=new Intent(this, LoginActivity.class);
			startActivity(i);
			break;
		case R.id.btn_reg_user:
			regUser();
			break;

		default:
			break;
		}
	}

	private void regUser() {
		String username = etUserName.getText().toString();
		String password = etPassword.getText().toString();
		
		if (username.equals("") || username.equals(null)||password.equals("") || password.equals(null)) {
			Toast.makeText(this,R.string.REG_WARNING_NOT_FILL, Toast.LENGTH_SHORT).show();
			return;
		}else if (username.length() < 6 && password.length() < 6) {
			Toast.makeText(RegActivity.this, R.string.REG_WARNING_OVER_LENGTH, 1).show();
			return;
		}
		
		url =Model.REG;
		value = "{\"uname\":\"" + username + "\",\"upassword\":\""
				+ password + "\"}";
		ThreadPoolUtils.execute(new HttpPostThread(hand, url, value));
	}
	
	
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 404) {
				Toast.makeText(RegActivity.this,R.string.LOGIN_NO_SERVER_WORK, 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(RegActivity.this, R.string.LOGIN_NO_SERVER, 1).show();
			} else if (msg.what == 200) {
				String result=(String) msg.obj;
				if(result.equals(TedConfig.REG_USER_EXIST)){
					etUserName.setText("");
					etPassword.setText("");
					Toast.makeText(RegActivity.this, R.string.REG_USER_EXIST, 1).show();
					return;
				}else if(result.equals(null)){
					etUserName.setText("");
					etPassword.setText("");
					Toast.makeText(RegActivity.this, R.string.LOGIN_NOT_FIND_USER, 1).show();
					return;
				}
				Toast.makeText(RegActivity.this, "注册成功！！！", 1).show();
				Intent i=new Intent(RegActivity.this, LoginActivity.class);
				i.putExtra(TedConfig.USER_NAME,etUserName.getText().toString());
				setResult(TedConfig.RESULT_CODE, i);
				finish();
				
			}else{
				Toast.makeText(RegActivity.this, "其他问题！！！", 1).show();
			}
		};
	};
	
	@Override
	protected HeadTitle getHeadTitle() {
		return new HeadTitle(this,R.string.reg,R.id.iv_head_title);
	}

	


}
