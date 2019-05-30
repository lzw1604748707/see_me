package com.anshuye.seeme.common.interceptor;

import com.anshuye.common.constant.Constant;
import com.anshuye.common.constant.RetConstant;
import com.anshuye.common.kit.Ipkit;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SmSysUser;
import com.anshuye.common.model.SysAccount;
import com.anshuye.seeme.login.LoginService;
import com.anshuye.seeme.login.UserLoginService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

public class UserLoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller con = inv.getController();
		String sessionId = con.getHeader(Constant.ADMIN_SESSION_ID);
	
		if (StrKit.isBlank(sessionId)) {
			con.renderJson(RetKit.fail(RetConstant.CODE_NO_LOGIN, "未登录"));
			return;
		}
		SmSysUser user = UserLoginService.me.getAccountCacheWithSessionId(sessionId);
		if (user == null) {
			user = UserLoginService.me.loginWithSessionId(sessionId, Ipkit.getRealIp(con.getRequest()));
		}
		if (user == null) {
			con.renderJson(RetKit.fail(RetConstant.CODE_LOGIN_EXPIRE, "会话过期，请重新登录"));
			return;
		}
		inv.invoke();
	}

}
