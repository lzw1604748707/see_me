package com.sandu.admin.feeback;

import java.util.Date;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.Feedback;
import com.sandu.common.model.SysAdv;

public class FeebackListService {
	
	public static final	FeebackListService me = new FeebackListService();
	static final Feedback dao = Feedback.dao;

	public Page<Feedback> paginate(int pageNumber, int pageSize, Kv kv) {
		return dao.paginate(pageNumber, pageSize, dao.getSqlPara("feedback.admin-paginate", kv));
	}
	
	public RetKit save(Feedback feedback) {
		if (feedback == null ) {
			return RetKit.fail("非法参数");
		}
		feedback.setCreateDate(new Date());
		boolean success = feedback.save();
		return success ? RetKit.ok() : RetKit.fail();
	}
	
	public boolean deleteById(Integer id){
		return dao.deleteById(id);
	}
}
