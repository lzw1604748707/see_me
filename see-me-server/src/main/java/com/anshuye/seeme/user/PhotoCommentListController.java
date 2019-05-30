package com.anshuye.seeme.user;

import com.anshuye.common.controller.AdminController;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SmPhotoComment;
import com.jfinal.kit.Kv;

public class PhotoCommentListController extends AdminController {
	
	static final PhotoCommentListService commentService = PhotoCommentListService.me;
	
	public void findAllProjectList(){
		renderJson(commentService.findAllProjectList());
	}

	public void list(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", commentService.list(pageNumber, pageSize, kv)));
	}

	public void remove(){
		Integer id = getParaToInt();
		renderJson(commentService.remove(id));
	}	
	
	public void save(){
		SmPhotoComment comment = getBean(SmPhotoComment.class,"");
		renderJson(commentService.save(comment));
	}

	

}
