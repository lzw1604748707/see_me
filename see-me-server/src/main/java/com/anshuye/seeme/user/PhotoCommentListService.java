package com.anshuye.seeme.user;

import java.util.Date;
import java.util.List;

import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SmPhotoComment;
import com.anshuye.common.model.SmPhotoProject;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

public class PhotoCommentListService {

	public static final	PhotoCommentListService me = new PhotoCommentListService();
	static final SmPhotoComment dao = SmPhotoComment.dao;
	static final SmPhotoProject projectDao = SmPhotoProject.dao;
	
	public Page<SmPhotoComment> list(int pageNumber, int pageSize, Kv kv) {
		Page<SmPhotoComment> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sm_photo_comment.admin-paginate", kv));
		return page;
	}
	
	public SmPhotoComment findById(Integer id){
		return dao.findById(id);
	}
	public List<SmPhotoProject> findAllProjectList() {
		List<SmPhotoProject> projectList = projectDao.find("select id,title from sm_photo_project");
		return projectList;
	}
	
	public RetKit save(SmPhotoComment comment){
		if (comment == null ) {
			return RetKit.fail("非法参数");
		}
		comment.setCreateDate(new Date());
		boolean success = comment.save();
		return success ? RetKit.ok() : RetKit.fail();
	}
	
	//删除
	public RetKit remove(Integer id) {
		SmPhotoComment removeComment = findById(id);
		boolean success = removeComment.delete();
		return success ? RetKit.ok() : RetKit.fail();
	}	
}
