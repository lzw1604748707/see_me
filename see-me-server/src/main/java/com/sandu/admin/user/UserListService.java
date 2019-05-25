package com.sandu.admin.user;

import java.util.Date;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SmSysUser;

public class UserListService {

	public static final	UserListService me = new UserListService();
	static final SmSysUser dao = SmSysUser.dao;
	
	public Page<SmSysUser> list(int pageNumber, int pageSize, Kv kv) {
		Page<SmSysUser> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sm_sys_user.admin-paginate", kv));
		return page;
	}
	public Page<SmSysUser> followerslist(int pageNumber, int pageSize, Kv kv) {
		Page<SmSysUser> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sm_sys_user.admin-paginate-followers", kv));
		return page;
	}
	public Page<SmSysUser> followingslist(int pageNumber, int pageSize, Kv kv) {
		Page<SmSysUser> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sm_sys_user.admin-paginate-followings", kv));
		return page;
	}
	
	public boolean isExistAccount(String account){
		return Db.queryInt("select id from sm_sys_user where account = ? limit 1",account) != null;
	}
	
	public SmSysUser findById(Integer id){
		return dao.findById(id);
	}
	
	public RetKit save(SmSysUser user){
		if (user == null ) {
			return RetKit.fail("非法参数");
		}
		if (isExistAccount(user.getAccount().trim())) {
			return RetKit.fail("登录账户已存在");
		}
		// 密码加盐 hash
		String salt = HashKit.generateSaltForSha256();
		String realPassword = user.getPassword().trim();
		String password = HashKit.sha256(salt + realPassword);
		user.setAccount(user.getAccount().trim());
		user.setSalt(salt);
		user.setPassword(password);
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		boolean success = user.save();
		return success ? RetKit.ok() : RetKit.fail();
	}
	
	public RetKit update(SmSysUser user){
		SmSysUser tempUser = findById(user.getId());
		//如果改了登陆账号
		if (!tempUser.getAccount().equals(user.getAccount())) {
			if (isExistAccount(user.getAccount().trim())) {
				return RetKit.fail("登陆账户已存在");
			}
		}
		
		boolean success = user.setUpdateDate(new Date()).update();
		//更新缓存
		
		return success ? RetKit.ok() : RetKit.fail();
	}
	//逻辑删除
	public RetKit remove(Integer id) {
		SmSysUser removeUser = findById(id);
		boolean success = removeUser.setIsDelete(1).update();
		return success ? RetKit.ok() : RetKit.fail();
	}	
	
	public RetKit changeFreeze(Integer id,Integer status) {
		SmSysUser user = findById(id);
		boolean success = user.setStatus(status).update();
		return success ? RetKit.ok() : RetKit.fail();
	}	
	public RetKit changeStop(Integer id,Integer status) {
		SmSysUser user = findById(id);
		boolean success = user.setStatus(status).update();
		return success ? RetKit.ok() : RetKit.fail();
	}	
}
