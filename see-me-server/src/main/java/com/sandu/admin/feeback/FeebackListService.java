package com.sandu.admin.feeback;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Page;
import com.sandu.common.model.Feedback;

public class FeebackListService {
	
	public static final	FeebackListService me = new FeebackListService();
	static final Feedback dao = Feedback.dao;

	public Page<Feedback> paginate(int pageNumber, int pageSize, Kv kv) {
		return dao.paginate(pageNumber, pageSize, dao.getSqlPara("feedback.admin-paginate", kv));
	}
	
	public boolean deleteById(Integer id){
		return dao.deleteById(id);
	}
}
