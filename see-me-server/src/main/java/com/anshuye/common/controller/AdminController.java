package com.anshuye.common.controller;

import com.anshuye.common.constant.Constant;
import com.anshuye.common.model.SysAccount;
import com.anshuye.seeme.login.LoginService;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.ext.interceptor.NotAction;

public class AdminController extends BaseController {
	
	@Clear
	public void index(){
		render("/index.html");
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
