package com.anshuye.seeme.info;


import java.util.Date;

import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SysAdv;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class AdvListService {

	public static final AdvListService me = new AdvListService();
	static final SysAdv dao = SysAdv.dao;

	public Page<SysAdv> paginate(int pageNumber, int pageSize, Kv kv) {
		Page<SysAdv> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sys_adv.admin-paginate", kv));
		return page;
	}

	public RetKit remove(Integer id) {
		SysAdv removeAdv = findById(id);
		boolean success = removeAdv.setIsDelete(1).update();
		return success ? RetKit.ok() : RetKit.fail();
	}

	public SysAdv findById(Integer id) {
		return dao.findById(id);
	}

	public RetKit save(SysAdv adv,String currentSessionId) {
		if (adv == null ) {
			return RetKit.fail("非法参数");
		}
		adv.setCreateDate(new Date());
		adv.setUpdateDate(new Date());
		Record sessionInfo = new Record();
		sessionInfo=Db.findById("sys_session",currentSessionId);
		Integer adminId = sessionInfo.getInt("accountId");
		adv.setAdminId(adminId);
		boolean success = adv.save();
		return success ? RetKit.ok() : RetKit.fail();
	}
	

	public RetKit update(SysAdv adv,String currentSessionId) {
		Record sessionInfo = new Record();
		sessionInfo=Db.findById("sys_session",currentSessionId);
		Integer adminId = sessionInfo.getInt("accountId");
		adv.setAdminId(adminId);
		boolean success = adv.setUpdateDate(new Date()).update();
		//更新缓存
		return success ? RetKit.ok() : RetKit.fail();
	}


	
}
