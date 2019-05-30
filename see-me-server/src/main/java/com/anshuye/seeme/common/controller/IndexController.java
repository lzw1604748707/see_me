package com.anshuye.seeme.common.controller;

import com.anshuye.common.constant.Constant;
import com.anshuye.common.controller.AdminController;
import com.anshuye.common.kit.RetKit;
import com.anshuye.seeme.common.interceptor.AdminLoginInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Kv;
import com.jfinal.upload.UploadFile;
/**
 * 
 * @ClassName:IndexController   
 * @author:Seven
 * @date:2017年12月13日 下午5:46:05
 */
@Clear
@Before({AdminLoginInterceptor.class})
public class IndexController extends AdminController{
	
	
	public void upload(){
		UploadFile uf = getFile("file");
		String url = Constant.UPLOAD_PATH +"/" + uf.getFileName();
		Kv kv = Kv.by("url", url).set("name", uf.getFileName());
		renderJson(RetKit.ok("file",kv));
	}
	
	
	

}
