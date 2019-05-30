package com.anshuye.seeme.common.interceptor;

import java.util.List;

import com.anshuye.common.constant.Constant;
import com.anshuye.common.constant.RetConstant;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SysAccount;
import com.anshuye.common.model.SysMenu;
import com.anshuye.seeme.admin.RoleService;
import com.anshuye.seeme.login.LoginService;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class AdminAuthInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		Controller con = inv.getController();
		String sessionId = con.getHeader(Constant.ADMIN_SESSION_ID);
		SysAccount account = LoginService.me.getAccountCacheWithSessionId(sessionId);
		if (validateAuth(account, inv.getControllerKey())) {
			inv.invoke();
		}else{
			con.renderJson(RetKit.fail(RetConstant.CODE_NO_AUTH, "你无权访问此路径"));
		}
	}
	
	private boolean validateAuth(SysAccount account,String controllerKey){
		if (account.isSysAdmin()) {
			return true;
		}
		List<SysMenu> list = RoleService.me.findMenuListByRoleId(account.getRoleId());
		boolean isHaveAuth = false;
		for (SysMenu menu : list) {
			if (menu.getAction().equals(controllerKey)) {
				isHaveAuth = true;
				break;
			}
		}
		return isHaveAuth;
	}

}
