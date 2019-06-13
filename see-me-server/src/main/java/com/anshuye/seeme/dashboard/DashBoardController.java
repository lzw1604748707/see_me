package com.anshuye.seeme.dashboard;

import com.anshuye.common.controller.AdminController;
import com.anshuye.common.kit.RetKit;


public class DashBoardController extends AdminController {

	static final DashBoardService srv = DashBoardService.me;
	
	public void platformCount() {
		renderJson(srv.platFormCount());
	}
	
	public void historyOnline() {
		int dayNumber =getParaToInt("dayNumber");
		renderJson(srv.historyOnline(dayNumber));
	}
	
}
