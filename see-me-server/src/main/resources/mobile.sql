#namespace("sm_photo_project")
	#sql("mobile-paginate")
		select p.id,p.cover,p.title,u.name as author from sm_photo_project p
		left join sm_sys_user u on u.id=p.createrId
		where p.isDelete=0
		#if(sk.notBlank(title))
			and title like "%"#p(title)"%"
		#end
		#if(sk.notBlank(author))
			and u.name like "%"#p(author)"%"
		#end
	#end
#end