package com.anshuye.seeme.common.interceptor;

import com.anshuye.common.constant.Constant;
import com.anshuye.common.constant.RetConstant;
import com.anshuye.common.kit.Ipkit;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SysAccount;
import com.anshuye.seeme.login.LoginService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;

public class AdminLoginInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller con = inv.getController();
		String sessionId = con.getHeader(Constant.ADMIN_SESSION_ID);
	
		if (StrKit.isBlank(sessionId)) {
			con.renderJson(RetKit.fail(RetConstant.CODE_NO_LOGIN, "未登錄"));
			return;
		}
		SysAccount account = LoginService.me.getAccountCacheWithSessionId(sessionId);
		if (account == null) {
			account = LoginService.me.loginWithSessionId(sessionId, Ipkit.getRealIp(con.getRequest()));
		}
		if (account == null) {
			con.renderJson(RetKit.fail(RetConstant.CODE_LOGIN_EXPIRE, "會話過期，請重新登陸"));
			return;
		}
		inv.invoke();
	}

}
