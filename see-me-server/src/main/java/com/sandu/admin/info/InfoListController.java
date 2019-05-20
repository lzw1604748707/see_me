package com.sandu.admin.info;

import java.util.List;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SysInfo;

public class InfoListController extends AdminController {

	static final InfoListService srv = InfoListService.me;
	

	public void list() {
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", srv.paginate(pageNumber, pageSize, kv)));
	}

	public void getSchoolList(){
		String area = getPara("area");
		renderJson(RetKit.ok("list",srv.findSchoolListByArea(area)));
	}
	
	public void downShelf() {
		Integer id = getParaToInt();
		srv.updateShelfStatus(id, false);
		renderJson(RetKit.ok());
	}

	public void upShelf() {
		Integer id = getParaToInt();
		srv.updateShelfStatus(id, true);
		renderJson(RetKit.ok());
	}

	public void save() {
		SysInfo info = getBean(SysInfo.class,"");
		List<String> extCoverList = getParaArrToList("extCoverList","url");
		List<String> schoolIds = getParaToList("schoolIdList");
		String area = getPara("area");
		renderJson(RetKit.ok(srv.save(info,extCoverList,schoolIds,area)));
	}

	public void remove() {
		Integer id = getParaToInt();
		boolean succ = srv.remove(id);
		renderJson(succ ? RetKit.ok() : RetKit.fail());
	}

	public void update() {
		SysInfo info = getBean(SysInfo.class,"");
		List<String> extCoverList = getParaArrToList("extCoverList","url");
		renderJson(RetKit.ok(srv.update(info,extCoverList)));
	}


}
