package com.anshuye.seeme.feeback;

import com.anshuye.common.controller.AdminController;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.Feedback;
import com.jfinal.kit.Kv;
public class FeebackListController extends AdminController {

	static final FeebackListService srv = FeebackListService.me;
	
	
	public void save() {
		Feedback feedback = getBean(Feedback.class,"");
		renderJson(RetKit.ok(srv.save(feedback)));
	}
	
	public void list(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", srv.paginate(pageNumber, pageSize, kv)));
	}
	
	public void remove(){
		srv.deleteById(getParaToInt());
		renderJson(RetKit.ok());
	}
	
	public void changeStatus(){
		Integer id = getParaToInt("id");
		Integer  status = getParaToInt("status");
		renderJson(srv.changeStatus(id,status));
	}
	
}
