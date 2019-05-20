package com.sandu.admin.user;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;

/**
 * 学生列表控制层
 * @ClassName:StudentListController
 * @author:Seven
 * @date:2017年12月15日 下午2:54:12
 */
public class StudentListController extends AdminController {
	
	static final StudentListService srv = StudentListService.me;
	
	public void list(){
		int pageNumber = getParaToInt("pageNumber",1);
		int pageSize = getParaToInt("pageSize",10);
		Kv kv = getParaToMap();
		renderJson(RetKit.ok("page",srv.paginate(pageNumber, pageSize, kv)));
	}
	
	public void schoolList(){
		renderJson(RetKit.ok("schoolList",srv.findSchoolList()));
	}

	
	public void gradeList(){
		Integer schoolId = getParaToInt("schoolId");
		renderJson(RetKit.ok("gradeList",srv.findGradeListBySchoolId(schoolId)));
	}
}
