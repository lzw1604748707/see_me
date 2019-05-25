package com.sandu.admin.dashboard;

import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;


public class DashBoardController extends AdminController {

	static final DashBoardService srv = DashBoardService.me;
	
	public void platformCount() {
		renderJson(srv.platFormCount());
	}
	
	public void historyOnline() {
		renderJson(RetKit.ok(srv.historyOnline()));
	}
	
}
