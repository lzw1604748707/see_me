#namespace("sys_account")
	#sql("admin-paginate")
		select a.id,a.name,a.username,a.lastLoginAt,a.roleId,b.name as roleName from sys_account a
		left join sys_role b on a.roleId = b.id
		where 1 = 1
		#if(sk.notBlank(name))
			and a.name = #p(name)
		#end
		#if(sk.notBlank(username))
			and a.username = #p(username)
		#end
	#end
#end

#namespace("school")
	#sql("admin-paginate")
		select a.*	from school a  where a.status < 99
		#if(sk.notBlank(name))
			and a.name = #p(name)
		#end
		order by nextPayAt
	#end
#end

#namespace("student")
	#sql("admin-paginate")
		select a.*,b.name as schoolName, c.name as gradeName from student a 
		left join school b on a.schoolId = b.id
		left join grade c on a.gradeId = c.id
		left join classes d on a.classId = d.id
		where 1=1
		#if(sk.notBlank(schoolId))
			and a.schoolId = #p(schoolId)
		#end
		#if(sk.notBlank(gradeId))
			and a.gradeId = #p(gradeId)
		#end
		#if(sk.notBlank(name))
			and a.name = #p(name)
		#end
		#if(sk.notBlank(studentCode))
			and a.studentCode = #p(studentCode)
		#end
	#end
#end


#namespace("teacher")
	#sql("admin-paginate")
		select a.*,b.name as schoolName from teacher a 
		left join school b on a.schoolId = b.id
		where 1=1
		#if(sk.notBlank(schoolId))
			and a.schoolId = #p(schoolId)
		#end
		#if(sk.notBlank(name))
			and a.name = #p(name)
		#end
		
	#end
#end


#namespace("parent")
	#sql("admin-paginate")
		select a.phone as parentPhone,a.name as parentName,studentCode,c.name as schoolName,d.name as studentName from parent_account a
		left join parent_student b on a.id = b.parentId
		left join student d on d.id = b.studentId
		left join school c on d.schoolId = c.id
		where 1 = 1
		#if(sk.notBlank(schoolId))
			and d.schoolId = #p(schoolId)
		#end
		#if(sk.notBlank(studentName))
			and d.name = #p(studentName)
		#end
		#if(sk.notBlank(studentCode))
			and d.studentCode = #p(studentCode)
		#end
	#end
#end


#namespace("school_introduction")
	#sql("admin-list")
		select a.*,b.area,b.name from school_introduction a 
		left join school b on a.schoolId = b.id
		where 1 = 1
		#if(sk.notBlank(schoolId))
			and a.id = #p(schoolId)
		#end
		order by a.updateAt
	#end
#end

#namespace("sys_info")
	#sql("admin-paginate")
		select a.*,b.name as accountName  from sys_info a 
		left join sys_account b on a.accountId = b.id
		where 1 = 1
		#if(sk.notBlank(title))
			and a.title = #p(title)
		#end
		order by a.createAt
	#end
#end


#namespace("sys_adv")
	#sql("admin-paginate")
		select a.*,b.name as accountName  from sys_adv a 
		left join sys_account b on a.accountId = b.id
		where 1 = 1
		#if(sk.notBlank(title))
			and a.title = #p(title)
		#end
		order by a.createAt
	#end
#end



#namespace("feedback")
	#sql("admin-paginate")
		select * from feedback where 1 = 1
		#if(sk.notBlank(feebackType))
			and type = #p(feebackType)
		#end
	#end
#end