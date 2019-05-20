package com.sandu.admin.login;

import com.jfinal.aop.Clear;
import com.jfinal.kit.LogKit;
import com.sandu.common.constant.Constant;
import com.sandu.common.constant.RetConstant;
import com.sandu.common.controller.BaseController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SysAccount;

@Clear
public class LoginController extends BaseController{
	
	static final LoginService srv = LoginService.me;

	public void doLogin(){
		String username = getPara("username");
		String password = getPara("password");
		String loginIp = getIpAddress();
		renderJson(srv.doLogin(username, password, loginIp));
	}
	
	public void logout(){
		String sessionId  = getHeader(Constant.ADMIN_SESSION_ID);
		srv.logout(sessionId);
		renderJson(RetKit.ok());
	}
	
	public void loginBySessionId(){
		String sessionId = getHeader(Constant.ADMIN_SESSION_ID);
		LogKit.info(getHeader(Constant.ADMIN_SESSION_ID));
		SysAccount account =  srv.getAccountCacheWithSessionId(sessionId);
		if (account == null) {
			account = srv.loginWithSessionId(sessionId, getIpAddress());
		}
		if (account == null) {
			
			renderJson(RetKit.fail(RetConstant.CODE_LOGIN_EXPIRE, "Session失效，請重新登陸"));
		}else{
			renderJson(RetKit.ok("account",account));
		}
	}
}
