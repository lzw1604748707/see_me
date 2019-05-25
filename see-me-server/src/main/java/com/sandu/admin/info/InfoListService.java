package com.sandu.admin.info;

import java.util.Date;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sandu.common.kit.RetKit;

import com.sandu.common.model.SysAccount;
import com.sandu.common.model.SysInfo;


public class InfoListService {

	public static final InfoListService me = new InfoListService();
	public static final String UPLOAD_PATH = "/upload/admin/info";
	static final SysInfo dao = SysInfo.dao;
	private static final SysAccount accountDao = SysAccount.dao;

	public Page<SysInfo> paginate(int pageNumber, int pageSize, Kv kv) {
		Page<SysInfo> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sys_info.admin-paginate", kv));
		return page;
	}

	public RetKit remove(Integer id) {
		SysInfo removeInfo = findById(id);
		boolean success = removeInfo.setIsDelete(true).update();
		return success ? RetKit.ok() : RetKit.fail();
	}

	public SysInfo findById(Integer id) {
		return dao.findById(id);
	}


	public RetKit save(SysInfo info,String currentSessionId) {
		if (info == null ) {
			return RetKit.fail("非法参数");
		}
		info.setCreateDate(new Date());
		info.setUpdateDate(new Date());
		Record sessionInfo = new Record();
		sessionInfo=Db.findById("sys_session",currentSessionId);
		Integer adminId = sessionInfo.getInt("accountId");
		info.setAdminId(adminId);
		boolean success = info.save();
		return success ? RetKit.ok() : RetKit.fail();
	}

	public RetKit update(SysInfo info,String currentSessionId) {
		Record sessionInfo = new Record();
		sessionInfo=Db.findById("sys_session",currentSessionId);
		Integer adminId = sessionInfo.getInt("accountId");
		info.setAdminId(adminId);
		boolean success = info.setUpdateDate(new Date()).update();
		//更新缓存
		return success ? RetKit.ok() : RetKit.fail();
	}

}
