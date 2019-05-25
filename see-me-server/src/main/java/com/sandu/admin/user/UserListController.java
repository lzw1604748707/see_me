package com.sandu.admin.user;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SmPhotoCollection;
import com.sandu.common.model.SmSysUser;;

public class UserListController extends AdminController {
	
	static final UserListService userService = UserListService.me;
	
	
	public void list(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", userService.list(pageNumber, pageSize, kv)));
	}

	public void followingslist(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", userService.followingslist(pageNumber, pageSize, kv)));
	}
	public void followerslist(){
		int pageSize = getParaToInt("pageSize", 10);
		int pageNumber = getParaToInt("pageNumber", 1);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page", userService.followerslist(pageNumber, pageSize, kv)));
	}
	public void remove(){

		Integer id = getParaToInt();
		renderJson(userService.remove(id));
	}
	
	public void update(){
		SmSysUser user = getBean(SmSysUser.class,"");
		renderJson(userService.update(user));
	}
	
	public void save(){
		SmSysUser user = getBean(SmSysUser.class,"");
		renderJson(userService.save(user));
	}
	public void changeFreeze(){
		Integer id = getParaToInt("id");
		Integer  status = getParaToInt("status");
		renderJson(userService.changeFreeze(id,status));
	}
	/**
	 * 开关
	 */
	public void changeStop(){
		Integer id = getParaToInt("id");
		Integer  status = getParaToInt("status");
		renderJson(userService.changeStop(id,status));
	}
	

}
