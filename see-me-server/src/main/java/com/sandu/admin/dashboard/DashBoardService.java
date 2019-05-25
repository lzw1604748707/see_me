package com.sandu.admin.dashboard;

import java.util.Date;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.Feedback;
import com.sandu.common.model.SysAdv;

public class DashBoardService {
	
	public static final	DashBoardService me = new DashBoardService();
	public Record platFormCount() {
		String sql= Db.getSql("dash-board.platform");
		Record platFormcountRecord=Db.findFirst(sql);
		return platFormcountRecord;
	}
	
	public Record historyOnline() {
		Record platFormcountRecord=Db.findFirst("platform.admin-paginate");
		return platFormcountRecord;
	}
	
}
