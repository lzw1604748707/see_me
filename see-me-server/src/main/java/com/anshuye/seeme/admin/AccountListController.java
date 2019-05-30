package com.anshuye.seeme.admin;

import com.anshuye.common.controller.AdminController;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SysAccount;
import com.jfinal.kit.Kv;

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
