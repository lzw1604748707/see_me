package com.anshuye.seeme.user;

import com.anshuye.common.controller.AdminController;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SmPhotoField;
import com.jfinal.kit.Kv;

public class PhotoFieldListController extends AdminController {
	
	static final PhotoFieldListService fieldService = PhotoFieldListService.me;
	
	
	public void list(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", fieldService.list(pageNumber, pageSize, kv)));
	}

	public void remove(){
		Integer id = getParaToInt();
		renderJson(fieldService.remove(id));
	}
	
	public void update(){
		SmPhotoField field = getBean(SmPhotoField.class,"");
		renderJson(fieldService.update(field));
	}
	
	public void save(){
		SmPhotoField field = getBean(SmPhotoField.class,"");
		renderJson(fieldService.save(field));
	}
	public void changeStatus(){
		Integer id = getParaToInt("id");
		Integer  status = getParaToInt("status");
		renderJson(fieldService.changeStatus(id,status));
	}
	

}
