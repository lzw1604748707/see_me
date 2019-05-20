package com.sandu.admin.my;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.sandu.admin.common.interceptor.AdminLoginInterceptor;
import com.sandu.common.controller.AdminController;
import com.sandu.common.model.SysAccount;
/**
 * 个人信息控制器 用于修改密码
 * @ClassName:MyController   
 * @author:Seven
 * @date:2017年12月25日 下午2:38:01
 */

@Clear
@Before({AdminLoginInterceptor.class})
public class MyController extends AdminController{

	static final MyService srv = MyService.me;

	public void resetPassword(){
		String oldPass = getPara("oldPassword");
		String password = getPara("password");
		String checkPass = getPara("checkPass");
		SysAccount loginAccount = getAccount();
		renderJson(srv.resetPassword(oldPass, password, checkPass, loginAccount));
	}
	
}
