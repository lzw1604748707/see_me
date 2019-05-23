package com.sandu.admin.user;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SmPhotoField;
import com.sandu.common.model.SmPhotoProject;;

public class PhotoProjectListController extends AdminController {
	
	static final PhotoProjectListService photoProjectService = PhotoProjectListService.me;
	
	
	public void list(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", photoProjectService.list(pageNumber, pageSize, kv)));
	}

	public void findById() {
		int id =getParaToInt("id");
		renderJson(photoProjectService.findById(id));
		
	}

	public void remove(){
		Integer id = getParaToInt();
		renderJson(photoProjectService.remove(id));
	}
	
	public void update(){
		SmPhotoProject photoProject = getBean(SmPhotoProject.class,"");
		renderJson(photoProjectService.update(photoProject));
	}
	
	public void save(){
		SmPhotoProject photoProject = getBean(SmPhotoProject.class,"");
		renderJson(photoProjectService.save(photoProject));
	}
	
	public void changeStatus(){
		Integer id = getParaToInt("id");
		String  status = getPara("status");
		renderJson(photoProjectService.changeStatus(id,status));
	}
	public void findCreateFileldList() {
		renderJson(photoProjectService.findCreateFileldList());
	}
	

}
