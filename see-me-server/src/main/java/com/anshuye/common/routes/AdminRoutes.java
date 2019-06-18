package com.anshuye.common.routes;

import com.anshuye.seeme.admin.AccountListController;
import com.anshuye.seeme.admin.RoleController;
import com.anshuye.seeme.common.controller.IndexController;
import com.anshuye.seeme.common.interceptor.AdminAuthInterceptor;
import com.anshuye.seeme.common.interceptor.AdminLoginInterceptor;
import com.anshuye.seeme.dashboard.DashBoardController;
import com.anshuye.seeme.feeback.FeebackListController;
import com.anshuye.seeme.info.AdvListController;
import com.anshuye.seeme.info.InfoListController;
import com.anshuye.seeme.login.LoginController;
import com.anshuye.seeme.my.MyController;
import com.anshuye.seeme.user.PhotoCollectionListController;
import com.anshuye.seeme.user.PhotoCommentListController;
import com.anshuye.seeme.user.PhotoFieldListController;
import com.anshuye.seeme.user.PhotoProjectListController;
import com.anshuye.seeme.user.UserListController;
import com.jfinal.config.Routes;

public class AdminRoutes extends Routes {

	@Override
	public void config() {
		//登陆拦截器
		this.addInterceptor(new AdminLoginInterceptor());
		//权限验证拦截器
		this.addInterceptor(new AdminAuthInterceptor());
		//用户模块
		this.add("/", IndexController.class);
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
		//看板模快
		this.add("/admin/dashBoard",DashBoardController.class);

	}

}
