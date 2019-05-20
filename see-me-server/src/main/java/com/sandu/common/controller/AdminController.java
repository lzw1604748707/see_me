package com.sandu.common.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.NotAction;
import com.sandu.admin.login.LoginService;
import com.sandu.common.constant.Constant;
import com.sandu.common.model.SysAccount;

public class AdminController extends BaseController {
	
	@Clear
	public void index(){
		render("/admin_index.html");
	}
	
	@Before(NotAction.class)
	public SysAccount getAccount(){
		return LoginService.me.getAccountCacheWithSessionId(getSessionId());
		
	}
	@Before(NotAction.class)
	public String getSessionId(){
		return getHeader(Constant.ADMIN_SESSION_ID);
	}
	 
}
