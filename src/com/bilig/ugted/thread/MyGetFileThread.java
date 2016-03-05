package com.bilig.ugted.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bilig.ugted.model.Model;
import com.bilig.ugted.net.MyGetFile;

public class MyGetFileThread implements Runnable{

	
	private Handler hand;
	private String url;
	private MyGetFile mgf=new MyGetFile();
	public MyGetFileThread(Handler hand,String endParamerse) {
		this.hand=hand;
		// 拼接访问服务器完整的地址
		url = Model.HTTPURL + endParamerse;
		
	}
	
	@Override
	public void run() {
		Message msg = hand.obtainMessage();
		Log.e("victor", url);
		BufferedReader reader = null;
		String line=null;
		StringBuffer sb = new StringBuffer();
		try {
			InputStream is=null;
			if(mgf.doGet(url)!=null){
				HttpResponse httpResponse=mgf.doGet(url);
				is=httpResponse.getEntity().getContent();
				reader = new BufferedReader(new InputStreamReader(is));
				while ((line = reader.readLine()) != null) {
					System.out.println(sb.toString());
					sb.append(line);
				}
				msg.what = 200;
				msg.obj = sb.toString();
				System.out.println("myGetT"+sb.toString());
				
			}else{
				msg.what = 404;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.what = 404;
		}finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		hand.sendMessage(msg);
	}

}
