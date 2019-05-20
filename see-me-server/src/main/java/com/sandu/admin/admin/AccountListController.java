package com.sandu.admin.admin;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SysAccount;

public class AccountListController extends AdminController {
	
	static final AccountListService srv = AccountListService.me;
	
	
	public void list(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", srv.paginate(pageNumber, pageSize, kv)));
	}

	public void remove(){
		Integer id = getParaToInt();
		renderJson(srv.remove(id,getAccount()));
	}
	
	public void update(){
		SysAccount account = getBean(SysAccount.class,"");
		renderJson(srv.update(account));
	}
	
	public void save(){
		SysAccount account = getBean(SysAccount.class,"");
		renderJson(srv.save(account));
	}
	
	public void getRoleList(){
		renderJson(RetKit.ok("list",srv.getRoleList()));
	}
	

}
