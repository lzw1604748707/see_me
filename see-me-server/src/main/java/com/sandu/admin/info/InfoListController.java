package com.sandu.admin.info;

import java.util.List;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SysAccount;
import com.sandu.common.model.SysInfo;

public class InfoListController extends AdminController {

	static final InfoListService srv = InfoListService.me;

	public void list() {
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", srv.paginate(pageNumber, pageSize, kv)));
	}

	public void findById() {
		int id =getParaToInt("id");
		renderJson(srv.findById(id));
		
	}
	public void save() {
		String currentSessionId= getHeader("jxtAdminSessionId");
		SysInfo info = getBean(SysInfo.class,"");
		renderJson(RetKit.ok(srv.save(info,currentSessionId)));
	}

	public void remove() {
		Integer id = getParaToInt();
		renderJson(srv.remove(id));
	}

	public void update() {
		String currentSessionId= getHeader("jxtAdminSessionId");
		SysInfo info = getBean(SysInfo.class,"");
		renderJson(RetKit.ok(srv.update(info,currentSessionId)));
	}


}
