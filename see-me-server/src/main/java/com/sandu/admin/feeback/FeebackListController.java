package com.sandu.admin.feeback;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.Feedback;
import com.sandu.common.model.SysAdv;

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
	
}
