package com.anshuye.seeme.admin;

import java.util.Date;
import java.util.List;

import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SysAccount;
import com.anshuye.common.model.SysRole;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

public class AccountListService {

	public static final	AccountListService me = new AccountListService();
	static final SysAccount dao = SysAccount.dao;
	
	public Page<SysAccount> paginate(int pageNumber, int pageSize, Kv kv) {
		Page<SysAccount> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sys_account.admin-paginate", kv));
		for (SysAccount account : page.getList()) {
			if (account.isSysAdmin()) {
				account.put("roleName", "超级管理员");
			}
		}
		return page;
	}
	
	public boolean isExistAccount(String username){
		return Db.queryInt("select id from sys_account where username = ? limit 1",username) != null;
	}
	
	public SysAccount findById(Integer id){
		return dao.findById(id);
	}
	
	public RetKit save(SysAccount account){
		if (account == null || account.getRoleId() <= 0) {
			return RetKit.fail("非法參數");
		}
		if (isExistAccount(account.getUsername().trim())) {
			return RetKit.fail("登陸賬號已存在");
		}
		// 密码加盐 hash
		String salt = HashKit.generateSaltForSha256();
		String deafultPassword = "123456";
		String password = HashKit.sha256(salt + deafultPassword);
		account.setUsername(account.getUsername().trim());
		account.setSalt(salt);
		account.setPassword(password);
		account.setCreateAt(new Date());
		account.setUpdateAt(new Date());
		boolean succ = account.save();
		return succ ? RetKit.ok() : RetKit.fail();
	}
	
	public RetKit update(SysAccount account){
		SysAccount tempAccount = findById(account.getId());
		//如果改了登陆账号
		if (!tempAccount.getUsername().equals(account.getUsername())) {
			if (tempAccount.isSysAdmin()) {
				return RetKit.fail("系統管理員賬號不允許更改");
			}
			if (isExistAccount(account.getUsername().trim())) {
				return RetKit.fail("登陸賬號已存在");
			}
		}
		
		if(tempAccount.isSysAdmin() && account.isSysAdmin() == false){
			return RetKit.fail("超級管理員不允許更改權限組");
		}
		
		if(tempAccount.isSysAdmin() == false && account.isSysAdmin()){
			return RetKit.fail("超級管理員只允許存在一個");
		}
		
		boolean succ = account.setUpdateAt(new Date()).update();
		//更新缓存
		
		return succ ? RetKit.ok() : RetKit.fail();
	}

	public RetKit remove(Integer id,SysAccount loginAccount) {
		SysAccount account = findById(id);
		if (account.isSysAdmin() ) {
			return RetKit.fail("不允許刪除系統管理員");
		}
		if (loginAccount.getId() == id) {
			return RetKit.fail("不允許自己刪除自己");
		}
		boolean succ = account.delete();
		return succ ? RetKit.ok() : RetKit.fail();
	}
	
	public List<SysRole> getRoleList(){
		return RoleService.me.list();
	}

	
	
}
