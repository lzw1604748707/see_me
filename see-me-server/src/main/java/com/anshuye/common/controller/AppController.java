package com.anshuye.common.controller;

import com.anshuye.common.constant.CacheConstant;
import com.anshuye.common.model.SmSysUser;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.ext.interceptor.NotAction;
import com.jfinal.plugin.ehcache.CacheKit;

public class AppController extends Controller {
	
	@Clear
	public void mobile(){
		render("/mobile_index.html");
	}
	
	@Before(NotAction.class)
	public String getAccessToken(){
		return getHeader("smUserToken");
	}

	@Before(NotAction.class)
	public SmSysUser getUser(){
		return CacheKit.get(CacheConstant.SYS_USER, getAccessToken());
	}
}
