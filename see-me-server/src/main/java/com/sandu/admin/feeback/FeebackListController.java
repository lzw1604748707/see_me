package com.sandu.admin.feeback;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;

public class FeebackListController extends AdminController {

	static final FeebackListService srv = FeebackListService.me;
	
	
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
