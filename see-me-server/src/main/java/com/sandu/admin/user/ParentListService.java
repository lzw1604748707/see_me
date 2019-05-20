package com.sandu.admin.user;


import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.sandu.common.model.Student;
/**
 * 家长列表业务
 * @ClassName:ParentListService   
 * @author:Seven
 * @date:2017年12月15日 下午2:54:01
 */
public class ParentListService {
	
	public static final	 ParentListService me = new ParentListService();
	private static final Student dao = Student.dao;

	
	
	public Page<Student> paginate(int pageNumber, int pageSize, Kv kv) {
		Page<Student> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("parent.admin-paginate", kv));
		return page;
	}
	
}
