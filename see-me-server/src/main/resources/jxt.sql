

#namespace("sm_sys_user")
	#sql("admin-paginate")
		select id,name,avatar,account,sex,career,area,userType,mobile,freezeDate,freezeCause,status,createDate,lastLoginDate,ifnull(fa.followers ,0) as followers, ifnull(fu.followings ,0) as followings
		from sm_sys_user as u
		left join (select  attentionId, count(userId) as followers from sm_sys_user_attention GROUP BY  attentionId) fa 
		ON u.id=fa.attentionId 
		left join (select  userId, count(attentionId) as followings from sm_sys_user_attention GROUP BY  userId) fu 
		ON u.id=fu.userId 
		where isDelete =0
		#if(sk.notBlank(name))
			and name like "%"#p(name)"%"
		#end
	#end
	
	#sql("admin-paginate-followers")
		select id,name,account,sex,career,area,userType,mobile,freezeDate,freezeCause,status,createDate
		from sm_sys_user as u, (select  userId from sm_sys_user_attention 
		where 1=1
		#if(sk.notBlank(id))
			and attentionId = #p(id)
		#end
		) fa 
		WHERE u.id=fa.userId 
	#end
	
	#sql("admin-paginate-followings")
		select id,name,account,sex,career,area,userType,mobile,freezeDate,freezeCause,status,createDate
		from sm_sys_user as u, (select attentionId  from sm_sys_user_attention 
		where 1=1
		#if(sk.notBlank(id))
			and userId = #p(id)
		#end
		) fa 
		WHERE u.id=fa.attentionId 
	#end
#end

#namespace("sm_photo_project")
	#sql("admin-paginate")
		select p.id,p.cover,p.title,u.name as author,p.createrId,p.createFields,p.praiseCount,p.viewCount,p.status from sm_photo_project p
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
#namespace("dash-board")
	#sql("platform")
		SELECT sum(projectCount) as projectCount,sum(collectionCount) as collectionCount,sum(userCount) as userCount,sum(advCount) as advCount,sum(infoCount) as infoCount
		FROM (
		    SELECT count(*) as projectCount,0 as collectionCount,0 as  userCount,0 as advCount,0 as infoCount FROM sm_photo_project 
	  		UNION ALL
	  		SELECT 0 as projectCount,0 as collectionCount,count(*) as userCount,0 as advCount,0 as infoCount  FROM sm_sys_user
	  		UNION ALL
	  		SELECT 0 as projectCount,count(*) as collectionCount,0 as userCount,0 as advCount,0 as infoCount  FROM sm_photo_collection
			UNION ALL
	  		SELECT 0 as projectCount,0 as collectionCount,0 as userCount,count(*) as advCount,0 as infoCount  FROM sys_adv
			UNION ALL
	  		SELECT 0 as projectCount,0 as collectionCount,0 as userCount,0 as advCount,count(*) as  infoCount  FROM sys_info
		 ) count
	#end
#end
#namespace("sm_photo_collection")
	#sql("admin-paginate")
		SELECT c.id,c.createrId,c.title,u.name as creater,c.status,c.createDate,
		prc.projectIdListStr,prc.coverListStr,prc.titleListStr,prc.viewCountListStr,prc.commentCountListStr
		FROM sm_photo_collection c
		LEFT JOIN sm_sys_user u 
		on u.id=c.createrId 
		LEFT JOIN (
					SELECT pr.collectionId,GROUP_CONCAT(pr.id) AS projectIdListStr,GROUP_CONCAT(pr.cover) AS coverListStr,
					       GROUP_CONCAT(pr.title) AS titleListStr,GROUP_CONCAT(pr.viewCount) AS viewCountListStr,
					       GROUP_CONCAT(pr.commentCount) AS commentCountListStr
					FROM( 
					      SELECT p.id,p.cover,p.title,p.viewCount,p.commentCount,r.collectionId 
						  FROM sm_photo_collection_record r
						  LEFT JOIN sm_photo_project p ON r.projectId=p.id
					) pr
				    GROUP BY collectionId
		) prc
		ON prc.collectionId =c.id
		where c.isDelete=0
		#if(sk.notBlank(title))
			and c.title like "%"#p(title)"%"
		#end
		#if(sk.notBlank(author))
			and u.name like "%"#p(creater)"%"
		#end
	#end
#end

#namespace("sm_photo_comment")
	#sql("admin-paginate")
		select c.id,p.title as projectName,u.name as commentator,c.content,c.createDate from sm_photo_comment c
		left join sm_sys_user u on c.createrId=u.id
		left join sm_photo_project p on c.worksId=p.id
		where 1=1
		#if(sk.notBlank(title))
			and p.title like "%"#p(title)"%"
		#end
		#if(sk.notBlank(author))
			and u.name like "%"#p(commentator)"%"
		#end
	#end
#end

#namespace("sm_photo_field")
	#sql("admin-paginate")
		select f.id,f.fieldName,f.providerId,u.name as provider,f.status,f.createDate from sm_photo_field f
		left join sm_sys_user u on u.id=f.providerId
		where 1=1
		#if(sk.notBlank(fieldName))
			and f.fieldName like "%"#p(fieldName)"%"
		#end
		#if(sk.notBlank(provider))
			and u.name like "%"#p(provider)"%"
		#end
	#end
#end

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
		select i.*,a.name as adminName,f.fieldName from sys_info i
		left join sys_account a on i.adminId = a.id
		left join sm_photo_field f on f.id=i.fieldId
		where i.isDelete=0
		#if(sk.notBlank(title))
			and i.title = #p(title)
		#end
		#if(sk.notBlank(adminName))
			and a.name = #p(adminName)
		#end
		order by i.createDate
	#end
#end


#namespace("sys_adv")
	#sql("admin-paginate")
		select ad.*,a.name as adminName,f.fieldName from sys_adv ad 
		left join sys_account a on ad.adminId = a.id
		left join sm_photo_field f on f.id=ad.fieldId
		where ad.isDelete=0
		#if(sk.notBlank(title))
			and ad.title = #p(title)
		#end
		#if(sk.notBlank(advName))
			and ad.advName = #p(advName)
		#end
		order by ad.createDate
	#end
#end



#namespace("feedback")
	#sql("admin-paginate")
		select f.*,u.name as accountName,u.account from feedback  f
		left join sm_sys_user u on u.id=f.accountId
		where 1 = 1
		#if(sk.notBlank(feebackType))
			and f.type = #p(feebackType)
		#end
	#end
#end