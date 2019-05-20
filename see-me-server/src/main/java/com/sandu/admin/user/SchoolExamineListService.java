package com.sandu.admin.user;

import java.util.List;

import com.jfinal.kit.Kv;
import com.sandu.common.model.SchoolIntroduction;

public class SchoolExamineListService {

	public static final	SchoolExamineListService me = new SchoolExamineListService();
	static final SchoolIntroduction dao = SchoolIntroduction.dao;
	
	public List<SchoolIntroduction> findList(Kv kv){
		return dao.find(dao.getSqlPara("school_introduction.admin-list",kv));
	}
	
	/**
	 * 通过审核
	 * @return 
	 */
	public boolean pass(Integer id){
		return new SchoolIntroduction().setId(id).setStatus(SchoolIntroduction.STATUS_PASS).update();
	}
	
	/**
	 * 不通过审核
	 * @return 
	 */
	public boolean reject(Integer id){
		return new SchoolIntroduction().setId(id).setStatus(SchoolIntroduction.STATUS_REJECT).update();
	}
	
	
}
