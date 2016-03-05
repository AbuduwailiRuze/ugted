package com.bilig.ugted.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.bilig.ugted.activity.R;
import com.bilig.ugted.config.TedConfig;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class UpdateInfoService {
	ProgressDialog progressDialog;
	Handler handler;
	Context context;
	
	public UpdateInfoService(Context context){
		this.context=context;
	}
	
	
	public void downLoadFile(final String url,final ProgressDialog pDialog,Handler hand){
		progressDialog=pDialog;
		final boolean flag=false;
		handler=hand;
		new Thread() {
			public void run() {        
				HttpClient client = new DefaultHttpClient();
				HttpGet get = new HttpGet(url);
				HttpResponse response;
				try {
					response = client.execute(get);
					HttpEntity entity = response.getEntity();
					int length = (int) entity.getContentLength();
                     progressDialog.setMax(length);
                     Log.i("waili", "len:"+length);
					InputStream is = entity.getContent();
					
					FileOutputStream fileOutputStream = null;
					if (is != null) {
						File file = new File(Environment.getExternalStorageDirectory(),TedConfig.newApkName);
						fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[10];   
						int ch = -1;
						int process = 0;
						while ((ch = is.read(buf)) != -1) {       
							fileOutputStream.write(buf, 0, ch);
							process += ch;
							progressDialog.setProgress(process);
						}

					}
					fileOutputStream.flush();
					if (fileOutputStream != null) {
						fileOutputStream.close();
					}
					down();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					
				}
			}

		}.start();
		
	}
	void down() {
		handler.post(new Runnable() {
			public void run() {
				progressDialog.cancel();
				update();
			}
		});
	}
	//安装
		private void update() {
			
			progressDialog.cancel();
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(new File(Environment
					.getExternalStorageDirectory(), TedConfig.newApkName)),
					"application/vnd.android.package-archive");
			
			context.startActivity(intent);
		}


	
}
