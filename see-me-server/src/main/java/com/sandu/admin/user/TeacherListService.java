package com.sandu.admin.user;

import java.util.Date;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.sandu.common.model.Teacher;
import com.xiaoleilu.hutool.date.DateUtil;
/**
 * 教师列表
 * @ClassName:TeacherListService   
 * @author:Seven
 * @date:2017年12月15日 下午2:55:15
 */
public class TeacherListService {
	
	public static final	 TeacherListService me = new TeacherListService();
	private static final Teacher dao = Teacher.dao;

	
	
	public Page<Teacher> paginate(int pageNumber, int pageSize, Kv kv) {
		Page<Teacher> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("teacher.admin-paginate", kv));
		for (Teacher teacher : page.getList()) {
			Date startTeachDate = teacher.getStartTeachDate();
			Long days = DateUtil.betweenDay(startTeachDate, new Date(), true);
			int year = days.intValue() / 365;
			teacher.put("teachYear", year);
		}
		return page;
	}
	
}
