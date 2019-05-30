package com.anshuye.common.constant;

import com.jfinal.kit.PathKit;

public class Constant {

	/**
	 * 后台sessionId
	 */
	public static final String ADMIN_SESSION_ID = "jxtAdminSessionId";
	/**
	 * 前台token
	 */
	public static final String USER_SESSION_ID = "SM_USER_ID";
	
	/**
	 * 默认上传临时文件夹 PathKit.getWebRootPath() +"/upload/temp";
	 */
	public static final String BASE_UPLOAD_PATH = PathKit.getWebRootPath() +"/upload/temp";
	public static final String UPLOAD_PATH = "/upload/temp";
	
			
}
