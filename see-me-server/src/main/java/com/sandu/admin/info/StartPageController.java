package com.sandu.admin.info;

import com.sandu.common.controller.AdminController;

public class StartPageController extends AdminController{
	
	static final StartPageservice srv = StartPageservice.me;
	
	
	public void parentPageSave(){
		String cover = getPara("cover");
		String url = getPara("url");
		renderJson(srv.parentPageSave(cover, url));
	}
	

	public void teacherPageSave(){
		String cover = getPara("cover");
		String url = getPara("url");
		renderJson(srv.TeacherPageSave(cover, url));
	}
	
	public void getCover(){
		renderJson(srv.findCover());
	}

}
