package com.bilig.ugted.config;

import com.bilig.ugted.domain.UserInfo;

public class TedConfig {
	
	public static final String LOGIN_NO_USER="nouser";
	public static final String USER_INFO="userInfo";
	public static final String LOGIN_RESULT_JSON="userResultJson";
	public static final String REG_USER_EXIST = "exist_user";
	public static final String USER_NAME = "username";
	public static final String BEST_TED_GET_PARAM ="best" ;
	public static final String NEWEST_TED_GET_PARAM ="new" ;
	
	//本地版本信息
    public static int localVersion = 0;
    //服務器版本信息
    public static int serverVersion = 0;
    //下載地址
    public static String downloadDir = "app/download/";
    
	public static final int REQUEST_CODE = 1;
	public static final int RESULT_CODE = 2;
	public static final int BEST_TED_GET_CODE =1 ;
	public static final int NEWEST_TED_GET_CODE =2;
	
	
	public static UserInfo userInfo=null;

}
