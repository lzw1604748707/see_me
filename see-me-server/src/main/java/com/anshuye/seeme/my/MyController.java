package com.anshuye.seeme.my;

import com.anshuye.common.controller.AdminController;
import com.anshuye.common.model.SysAccount;
import com.anshuye.seeme.common.interceptor.AdminLoginInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
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
