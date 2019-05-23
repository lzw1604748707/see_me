package com.sandu.common.routes;

import com.jfinal.config.Routes;
import com.sandu.admin.admin.AccountListController;
import com.sandu.admin.admin.RoleController;
import com.sandu.admin.common.controller.IndexController;
import com.sandu.admin.common.interceptor.AdminAuthInterceptor;
import com.sandu.admin.common.interceptor.AdminLoginInterceptor;
import com.sandu.admin.feeback.FeebackListController;
import com.sandu.admin.info.AdvListController;
import com.sandu.admin.info.InfoListController;
import com.sandu.admin.login.LoginController;
import com.sandu.admin.my.MyController;
import com.sandu.admin.user.PhotoCollectionListController;
import com.sandu.admin.user.PhotoCommentListController;
import com.sandu.admin.user.PhotoFieldListController;
import com.sandu.admin.user.PhotoProjectListController;
import com.sandu.admin.user.UserListController;

public class AdminRoutes extends Routes {

	@Override
	public void config() {
		//登陆拦截器
		this.addInterceptor(new AdminLoginInterceptor());
		//权限验证拦截器
		this.addInterceptor(new AdminAuthInterceptor());
		//用户模块
		this.add("/admin", IndexController.class);
		this.add("/admin/user/user", UserListController .class);
		this.add("/admin/user/project", PhotoProjectListController .class);
		this.add("/admin/user/collection", PhotoCollectionListController.class);
		this.add("/admin/user/comment", PhotoCommentListController.class);
		this.add("/admin/user/createfield", PhotoFieldListController.class);
		//资讯模块
		this.add("/admin/info/information", InfoListController.class);
		this.add("/admin/info/adv", AdvListController.class);
		//意见反馈
		this.add("/admin/feeback", FeebackListController.class);
		//管理员管理模块
		this.add("/admin/auth/account", AccountListController.class);
		this.add("/admin/auth/role", RoleController.class);
		//登陆
		this.add("/admin/login", LoginController.class);
		//个人模块
		this.add("/admin/my",MyController.class);

	}

}
