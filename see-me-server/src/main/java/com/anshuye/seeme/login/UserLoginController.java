package com.anshuye.seeme.login;

import com.anshuye.common.constant.Constant;
import com.anshuye.common.constant.RetConstant;
import com.anshuye.common.controller.BaseController;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SmSysUser;
import com.anshuye.common.model.SysAccount;
import com.jfinal.aop.Clear;
import com.jfinal.kit.LogKit;

@Clear
public class UserLoginController extends BaseController{
	
	static final UserLoginService srv = UserLoginService.me;

	public void doLogin(){
		String account = getPara("account");
		String password = getPara("password");
		String loginIp = getIpAddress();
		renderJson(srv.doLogin(account, password, loginIp));
	}
	public void logout(){
		String sessionId  = getHeader(Constant.USER_SESSION_ID);
		srv.logout(sessionId);
		renderJson(RetKit.ok());
	}
	
	public void loginBySessionId(){
		String sessionId = getHeader(Constant.USER_SESSION_ID);
		LogKit.info(getHeader(Constant.USER_SESSION_ID));
		SmSysUser user =  srv.getAccountCacheWithSessionId(sessionId);
		if (user == null) {
			user = srv.loginWithSessionId(sessionId, getIpAddress());
		}
		if (user == null) {
			renderJson(RetKit.fail(RetConstant.CODE_LOGIN_EXPIRE, "登录过期，请重新登录"));
		}else{
			renderJson(RetKit.ok("user",user));
		}
	}
}
