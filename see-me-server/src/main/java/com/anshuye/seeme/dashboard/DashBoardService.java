package com.anshuye.seeme.dashboard;

import java.util.Date;
import java.util.List;

import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.Feedback;
import com.anshuye.common.model.SmPhotoCollection;
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
	
	public List<Record> historyOnline(int dayNumber) {		
		List<Record> onlineList = Db.find("SELECT date(loginAt) as date, COUNT(*) as  personNumber FROM sys_login_log where DATE_SUB(CURDATE(), INTERVAL ? DAY) <= date(NOW()) and userId!='' GROUP BY date ORDER BY date",dayNumber);
		return onlineList;
	}
	
}
