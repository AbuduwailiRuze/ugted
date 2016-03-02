package com.bilig.ugted.listener;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bilig.ugted.model.*;
import com.bilig.ugted.net.ThreadPoolUtils;
import com.bilig.ugted.thread.HttpPostThread;
import com.bilig.ugted.util.MyJson;
import com.bilig.ugted.activity.R;
import com.bilig.ugted.activity.RegActivity;
import com.bilig.ugted.config.TedConfig;
import com.bilig.ugted.domain.UserInfo;

public class LoginBtnListener implements View.OnClickListener {

	private Activity activity;

	private Button btnSubmit;
	private Button btnRegister;

	private EditText etUserName;
	private EditText etPassword;
	
	private String url = null;
	private String value = null;
	
	private String password;
	private String username;
	
	public LoginBtnListener(Activity activity) {
		this.activity = activity;

		btnSubmit = (Button) activity.findViewById(R.id.btn_login);
		btnRegister = (Button) activity.findViewById(R.id.btn_reg);

		etUserName = (EditText) activity.findViewById(R.id.et_username);
		etPassword = (EditText) activity.findViewById(R.id.et_userpwd);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_login:

			login();
			break;
		case R.id.btn_reg:
			Intent i=new Intent(activity, RegActivity.class);
			activity.startActivityForResult(i, TedConfig.REQUEST_CODE);
			break;

		default:
			break;
		}

	}

	private void login() {
		username = etUserName.getText().toString();
		password = etPassword.getText().toString();
		
		if (username.equals("") || username.equals(null)||password.equals("") || password.equals(null)) {
			Toast.makeText(activity,R.string.LOGIN_WARNING_NOT_FILL, Toast.LENGTH_SHORT).show();
			return;
		}
		
		
		url =Model.LOGIN;
		value = "{\"uname\":\"" + username + "\",\"upassword\":\""
				+ password + "\"}";
		ThreadPoolUtils.execute(new HttpPostThread(hand, url, value));

	}
	
	Handler hand=new Handler(){
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 404) {
				Toast.makeText(activity,R.string.LOGIN_NO_SERVER_WORK, 1).show();
			} else if (msg.what == 100) {
				Toast.makeText(activity, R.string.LOGIN_NO_SERVER, 1).show();
			} else if (msg.what == 200) {
				String result=(String) msg.obj;
				
				if(result.equals(TedConfig.LOGIN_NO_USER)){
					etUserName.setText("");
					etPassword.setText("");
					Toast.makeText(activity, R.string.LOGIN_NOT_FIND_USER, 1).show();
					return;
				}else if(result.equals(null)){
					etUserName.setText("");
					etPassword.setText("");
					Toast.makeText(activity, R.string.LOGIN_NOT_FIND_USER, 1).show();
					return;
				}
				Toast.makeText(activity, "登录成功！！！", 1).show();
				SharedPreferences sp=activity.getSharedPreferences(TedConfig.USER_INFO, activity.MODE_PRIVATE);
				
				SharedPreferences.Editor editor=sp.edit();
				
				editor.putString(TedConfig.LOGIN_RESULT_JSON, result);
				UserInfo ui=new UserInfo();
				ui=MyJson.getUserInfo(result);
				TedConfig.userInfo=ui;
				editor.commit();
				activity.finish();
				
			}else{
				Toast.makeText(activity, "其他问题！！！", 1).show();
			}
		};
	};
	
	

}
