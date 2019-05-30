package com.anshuye.seeme.dashboard;

import java.util.Date;

import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.Feedback;
import com.anshuye.common.model.SysAdv;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

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
