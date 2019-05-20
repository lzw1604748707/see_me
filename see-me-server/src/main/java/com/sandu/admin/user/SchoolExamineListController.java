package com.sandu.admin.user;

import java.util.List;

import com.jfinal.kit.Kv;
import com.sandu.common.controller.AdminController;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SchoolIntroduction;
/**
 * 学校介绍审核
 * @ClassName:SchoolExamineListController   
 * @author:Seven
 * @date:2017年12月15日 下午2:40:26
 */
public class SchoolExamineListController extends AdminController{

	static final SchoolExamineListService srv = SchoolExamineListService.me;
	
	
	/**
	 * 审核列表
	 */
	public void list(){
		Kv kv = getParaToMap();
		List<SchoolIntroduction> list = srv.findList(kv);
		renderJson(RetKit.ok("list",list));
	}
	
	/**
	 * 审核通过
	 */
	public void pass(){
		srv.pass(getParaToInt());
		renderJson(RetKit.ok());
	}

	/**
	 * 审核不通过
	 */
	public void reject(){
		srv.reject(getParaToInt());
		renderJson(RetKit.ok());
	}
	
	public void schoolList(){
		renderJson(RetKit.ok("schoolList",StudentListService.me.findSchoolList()));
	}
	
}
