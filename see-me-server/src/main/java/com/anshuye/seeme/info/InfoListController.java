package com.anshuye.seeme.info;

import java.util.List;

import com.anshuye.common.controller.AdminController;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SysAccount;
import com.anshuye.common.model.SysInfo;
import com.jfinal.kit.Kv;

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
		renderJson(srv.save(info,currentSessionId));
	}

	public void remove() {
		Integer id = getParaToInt();
		renderJson(srv.remove(id));
	}

	public void update() {
		String currentSessionId= getHeader("jxtAdminSessionId");
		SysInfo info = getBean(SysInfo.class,"");
		renderJson(srv.update(info,currentSessionId));
	}


}
