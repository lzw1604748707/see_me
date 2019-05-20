package com.sandu.admin.info;

import java.util.Date;

import com.jfinal.plugin.activerecord.Db;
import com.sandu.common.kit.RetKit;
import com.sandu.common.kit.UploadFileMoveKit;
import com.sandu.common.model.StartPage;

public class StartPageservice {

	public static final StartPageservice me = new StartPageservice();
	static final StartPage dao = StartPage.dao;
	public static final String UPLOAD_PATH = "/upload/admin/startpage";

	public RetKit findCover(){
		StartPage parent = dao.findFirst("select * from start_page where type = ? limit 1",StartPage.START_PARENT);
		StartPage teacher = dao.findFirst("select * from start_page where type = ? limit 1",StartPage.START_TEACHER);
		return RetKit.ok("teacher", teacher).set("parent", parent);
	}
	
	public RetKit parentPageSave(String cover, String url) {
		cover = UploadFileMoveKit.move(cover, UPLOAD_PATH);
		if (isExistParentCover()) {
			 Db.update("update start_page set cover = ? ,url = ?,updateAt = ? where type = ?",cover,url, new Date(),StartPage.START_PARENT);
		}else{
			new StartPage().setCover(cover).setUrl(url).setType(StartPage.START_PARENT).setUpdateAt(new Date()).save();
		}
		return RetKit.ok("url",cover);
	}
	
	public RetKit TeacherPageSave(String cover, String url){
		cover = UploadFileMoveKit.move(cover, UPLOAD_PATH);
		if (isExistTeacherCover()) {
			 Db.update("update start_page set cover = ? ,url = ?,updateAt = ? where type = ?",cover,url, new Date(),StartPage.START_TEACHER);
		}else{
			new StartPage().setCover(cover).setUrl(url).setType(StartPage.START_TEACHER).setUpdateAt(new Date()).save();
		}
		return RetKit.ok("url",cover);
	}

	private boolean isExistParentCover() {
		return Db.queryInt("select id from start_page where type = ? limit 1", StartPage.START_PARENT) != null;
	}

	private boolean isExistTeacherCover() {
		return Db.queryInt("select id from start_page where type = ? limit 1", StartPage.START_TEACHER) != null;
	}
}
