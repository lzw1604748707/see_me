package com.anshuye.seeme.user;

import java.util.Date;
import java.util.List;

import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SmPhotoField;
import com.anshuye.common.model.SmPhotoProject;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;

public class PhotoProjectListService {

	public static final	PhotoProjectListService me = new PhotoProjectListService();
	static final SmPhotoProject dao = SmPhotoProject.dao;
	static final SmPhotoField fieldDao = SmPhotoField.dao;
	
	public Page<SmPhotoProject> list(int pageNumber, int pageSize, Kv kv) {
		Page<SmPhotoProject> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sm_photo_project.admin-paginate", kv));
		return page;
	}
	public List<SmPhotoField> findCreateFileldList() {
		List<SmPhotoField> fieldList = fieldDao.find("select * from sm_photo_field where status =1");
		return fieldList;
	}

	public SmPhotoProject findById(Integer id){
		return dao.findById(id);
	}
	
	public RetKit save(SmPhotoProject photoProject){
		if (photoProject == null ) {
			return RetKit.fail("非法参数");
		}
		photoProject.setCreateDate(new Date());
		photoProject.setUpdateDate(new Date());
		boolean success = photoProject.save();
		return success ? RetKit.ok() : RetKit.fail();
	}
	
	public RetKit update(SmPhotoProject photoProject){
		boolean success = photoProject.setUpdateDate(new Date()).update();
		//更新缓存
		return success ? RetKit.ok() : RetKit.fail();
	}
	//逻辑删除
	public RetKit remove(Integer id) {
		SmPhotoProject removePhotoProject = findById(id);
		boolean success = removePhotoProject.setIsDelete(true).update();
		return success ? RetKit.ok() : RetKit.fail();
	}	
	//上架or下架
	public RetKit changeStatus(Integer id,String status) {
		SmPhotoProject photoProject = findById(id);
		boolean success = 	photoProject.setStatus(Integer.parseInt(status)==1).update();
		return success ? RetKit.ok() : RetKit.fail();
	}
	
		
}
