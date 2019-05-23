package com.sandu.admin.user;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SmPhotoCollection;

public class PhotoCollectionListController extends AdminController {
	
	static final PhotoCollectionListService collectionService = PhotoCollectionListService.me;
	
	
	public void list(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", collectionService.list(pageNumber, pageSize, kv)));
	}

	public void remove(){
		Integer id = getParaToInt();
		renderJson(collectionService.remove(id));
	}
	
	public void update(){
		SmPhotoCollection collection = getBean(SmPhotoCollection.class,"");
		String projectIds =getPara("projectIdListStr");
		renderJson(collectionService.update(collection,projectIds));
	}
	
	public void save(){
		SmPhotoCollection collection = getBean(SmPhotoCollection.class,"");
		String projectIds =getPara("projectIdListStr");
		renderJson(collectionService.save(collection,projectIds));
	}
	
	public void changeStatus(){
		Integer id = getParaToInt("id");
		String  status = getPara("status");
		renderJson(collectionService.changeStatus(id,status));
	}
	
	public void findAllProjectToCardList(){
		renderJson(collectionService.findAllProjectToCardList());
	}
	

}
