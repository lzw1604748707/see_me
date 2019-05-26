package com.sandu.admin.info;

import java.util.List;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SysAdv;
import com.sandu.common.model.SysInfo;

public class AdvListController extends AdminController {

	static final AdvListService srv = AdvListService.me;

	
	public void list() {
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", srv.paginate(pageNumber, pageSize, kv)));
	}

	public void save() {
		String currentSessionId= getHeader("jxtAdminSessionId");
		SysAdv adv = getBean(SysAdv.class,"");
		renderJson(srv.save(adv,currentSessionId));
	}

	public void remove() {
		Integer id = getParaToInt();
		renderJson(srv.remove(id));
	}

	public void update() {
		String currentSessionId= getHeader("jxtAdminSessionId");
		SysAdv adv = getBean(SysAdv.class,"");
		renderJson(srv.update(adv,currentSessionId));
	}

}
