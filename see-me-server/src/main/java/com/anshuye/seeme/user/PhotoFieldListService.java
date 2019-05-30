package com.anshuye.seeme.user;

import java.util.Date;

import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SmPhotoField;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;

public class PhotoFieldListService {

	public static final	PhotoFieldListService me = new PhotoFieldListService();
	static final SmPhotoField dao = SmPhotoField.dao;
	
	public Page<SmPhotoField> list(int pageNumber, int pageSize, Kv kv) {
		Page<SmPhotoField> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sm_photo_field.admin-paginate", kv));
		return page;
	}
	public SmPhotoField findById(Integer id){
		return dao.findById(id);
	}
	
	public RetKit save(SmPhotoField field){
		if (field == null ) {
			return RetKit.fail("非法参数");
		}
		if(field.getProviderId()==-1)field.setStatus(1);
		field.setCreateDate(new Date());
		boolean success = field.save();
		return success ? RetKit.ok() : RetKit.fail();
	}
	public RetKit update(SmPhotoField field){
		boolean success = field.update();
		//更新缓存
		return success ? RetKit.ok() : RetKit.fail();
	}
	
	//删除
	public RetKit remove(Integer id) {
		SmPhotoField removeField = findById(id);
		boolean success = removeField.delete();
		return success ? RetKit.ok() : RetKit.fail();
	}
	//改变当前状态
	public RetKit changeStatus(Integer id,Integer status) {
		SmPhotoField photoField = findById(id);
		boolean success = 	photoField.setStatus(status).update();
		return success ? RetKit.ok() : RetKit.fail();
	}
}
