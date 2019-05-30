package com.anshuye.seeme.my;

import java.util.Date;

import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SysAccount;
import com.jfinal.kit.HashKit;

public class MyService {
	
	public static final	MyService me = new MyService();
	private static final SysAccount dao = SysAccount.dao;
	
	
	public RetKit resetPassword(String oldPass,String pass,String checkPass,SysAccount loginAccount){
		if (!pass.equals(checkPass)) {
			return RetKit.fail("兩次密碼不一致");
		}
		SysAccount account =  dao.findById(loginAccount.getId());
		oldPass = HashKit.sha256(account.getSalt() + oldPass);
		if (!oldPass.equals(account.getPassword())) {
			return RetKit.fail("原密碼錯誤");
		}
		String salt = HashKit.generateSaltForSha256();
		String password = HashKit.sha256(salt + pass);
		boolean succ = account.setSalt(salt).setPassword(password).setUpdateAt(new Date()).update();
		return succ ? RetKit.ok() : RetKit.fail();
	}
	
}
