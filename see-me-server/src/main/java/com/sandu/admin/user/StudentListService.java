package com.sandu.admin.user;


import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sandu.common.model.School;
import com.sandu.common.model.Student;
/**
 * 学生业务
 * @ClassName:StudentListService   
 * @author:Seven
 * @date:2017年12月15日 下午2:54:57
 */
public class StudentListService {
	
	public static final	 StudentListService me = new StudentListService();
	private static final Student dao = Student.dao;
	

	public Page<Student> paginate(int pageNumber, int pageSize, Kv kv) {
		return dao.paginate(pageNumber, pageSize, dao.getSqlPara("student.admin-paginate", kv));
	}
	
	public List<Record> findSchoolList(){
		return Db.find("select id,name from school where status < ?",School.STATUS_DELETE);
	}
	
	public List<Record> findGradeListBySchoolId(Integer schoolId){
		return Db.find("select id,name from grade where schoolId = ?",schoolId);
	}
	

}
