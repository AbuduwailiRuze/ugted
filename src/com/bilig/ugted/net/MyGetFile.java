package com.bilig.ugted.net;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;

public class MyGetFile {

	public HttpResponse doGet(String url) throws ClientProtocolException, IOException {
		InputStream is=null;// 我们的网络交互返回值
		
		HttpGet myGet = new HttpGet(url);
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 5 * 1000);
		httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				30 * 1000);
		HttpResponse httpResponse = httpClient.execute(myGet);
		if (httpResponse.getStatusLine().getStatusCode() == 200) {
			return httpResponse;
		}
		return null;
	}
}
