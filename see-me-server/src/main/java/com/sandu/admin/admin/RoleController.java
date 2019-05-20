package com.sandu.admin.admin;

import java.util.List;

import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SysRole;

public class RoleController extends AdminController{
	
	static final RoleService srv  = RoleService.me;

	public void list(){
		renderJson(RetKit.ok("list", srv.list()));
	}
	
	public void save(){
		List<String> menus = getParaToList("menus");
		String name = getPara("name");
		renderJson(srv.save(menus, name));
	}
	
	public void remove(){
		Integer id = getParaToInt();
		renderJson(srv.remove(id));
	}
	
	public void update(){
		List<String> menus = getParaToList("menus");
		SysRole role = getBean(SysRole.class,"");
		renderJson(srv.update(menus, role));
	}
	
	public void getMenuList(){
		renderJson(RetKit.ok("list", srv.findMenus()));
	}
}
