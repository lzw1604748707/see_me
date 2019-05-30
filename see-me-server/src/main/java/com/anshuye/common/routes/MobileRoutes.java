package com.anshuye.common.routes;

import com.anshuye.seeme.common.controller.IndexController;
import com.anshuye.seeme.common.interceptor.UserLoginInterceptor;
import com.anshuye.seeme.feeback.FeebackListController;
import com.anshuye.seeme.login.LoginController;
import com.anshuye.seeme.login.UserLoginController;
import com.anshuye.seeme.my.MyController;
import com.anshuye.seeme.user.PhotoCollectionListController;
import com.anshuye.seeme.user.PhotoCommentListController;
import com.anshuye.seeme.user.PhotoFieldListController;
import com.anshuye.seeme.user.PhotoProjectListController;
import com.anshuye.seeme.user.UserListController;
import com.jfinal.config.Routes;


public class MobileRoutes extends Routes {

	@Override
	public void config() {
		//登陆拦截器
		this.addInterceptor(new UserLoginInterceptor());
		//用户模块
		this.add("/mobile", IndexController.class);
		this.add("/mobile/user", UserListController .class);
		this.add("/mobile/project", PhotoProjectListController .class);
		this.add("/mobile/collection", PhotoCollectionListController.class);
		this.add("/mobile/comment", PhotoCommentListController.class);
		this.add("/mobile/createfield", PhotoFieldListController.class);
		//意见反馈
		this.add("/mobile/feeback", FeebackListController.class);
		//登陆
		this.add("/mobile/login", UserLoginController.class);
		//个人模块
		this.add("/mobile/my",MyController.class);

	}

}
