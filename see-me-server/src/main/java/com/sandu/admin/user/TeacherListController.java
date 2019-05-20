package com.sandu.admin.user;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
/**
 * 教师
 * @ClassName:TeacherListController   
 * @author:Seven
 * @date:2017年12月15日 下午2:55:07
 */
public class TeacherListController extends AdminController {
	
	static final TeacherListService srv = TeacherListService.me;
	
	public void list(){
		int pageNumber = getParaToInt("pageNumber",1);
		int pageSize = getParaToInt("pageSize",10);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page",srv.paginate(pageNumber, pageSize, kv)));
	}
	
	public void schoolList(){
		renderJson(RetKit.ok("schoolList",StudentListService.me.findSchoolList()));
	}


}
