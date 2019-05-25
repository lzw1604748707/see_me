package com.sandu.admin.feeback;

import java.util.Date;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.Feedback;

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
	
	public Feedback findById(Integer id){
		return dao.findById(id);
	}
	//改变当前状态
	public RetKit changeStatus(Integer id,Integer status) {
		Feedback feedback = findById(id);
		boolean success = 	feedback.setStatus(status).update();
		return success ? RetKit.ok() : RetKit.fail();
	}
}
