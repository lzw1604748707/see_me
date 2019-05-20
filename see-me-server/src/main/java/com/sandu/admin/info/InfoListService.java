package com.sandu.admin.info;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sandu.common.kit.JsoupKit;
import com.sandu.common.kit.RetKit;
import com.sandu.common.kit.UploadFileMoveKit;
import com.sandu.common.model.School;
import com.sandu.common.model.SysInfo;
import com.xiaoleilu.hutool.io.FileUtil;

public class InfoListService {

	public static final InfoListService me = new InfoListService();
	public static final String UPLOAD_PATH = "/upload/admin/info";

	static final SysInfo dao = SysInfo.dao;

	public Page<SysInfo> paginate(int pageNumber, int pageSize, Kv kv) {
		Page<SysInfo> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sys_info.admin-paginate", kv));
		for (SysInfo info : page.getList()) {
			info.put("extCoverList", info.getExtCover());
			info.put("schoolIdList", info.getPushSchool());
		}
		return page;
	}

	public boolean updateShelfStatus(Integer id, boolean flag) {
		return new SysInfo().setId(id).setStatus(flag).update();
	}

	public boolean remove(Integer id) {
		SysInfo info = dao.findById(id);
		String path = PathKit.getWebRootPath() + UPLOAD_PATH + "/" + info.getId();
		try {
			FileUtil.del(path);
		} catch (IOException e) {
			e.printStackTrace();
			LogKit.error(e.getMessage());
		}
		boolean succ = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				Db.update("delete from sys_info_push_school where infoId = ?", id);
				Db.update("delete from sys_info_ext_cover where infoId = ?", id);
				info.delete();
				return true;
			}
		});
		return succ;
	}

	public SysInfo findById(Integer id) {
		return dao.findById(id);
	}

	public List<Record> findSchoolListByArea(String area) {
		if (area.equals("0")) {
			return Db.find("select id,name from school where status = ? ", School.STATUS_OK);
		}
		return Db.find("select id,name from school where status = ? and area = ?", School.STATUS_OK, area);
	}

	public RetKit save(SysInfo info, List<String> extCoverList, List<String> schoolIds, String area) {
		if (info == null) {
			return RetKit.fail("上傳參數有誤");
		}
		info.setAccount("text");
		info.setAccountId(1);
		info.setCreateAt(new Date());
		info.setUpdateAt(new Date());

		boolean succ = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean succ = true;
				info.save();
				Integer infoId = info.getId();
				// 更新封面地址
				String cover = UploadFileMoveKit.move(info.getCover(), UPLOAD_PATH + "/" + infoId);
				if (StrKit.isBlank(cover)) {
					return false;
				}
				// Xss过滤
				String content = JsoupKit.fiterContent(info.getContent(), UPLOAD_PATH + "/" + infoId);
				succ = info.setCover(cover).setContent(content).update();
				// 插入要推送学校
				createPushSchool(infoId, area, schoolIds);
				// 插入配图地址
				for (String extCover : extCoverList) {
					String url = UploadFileMoveKit.move(extCover, UPLOAD_PATH + "/" + infoId);
					if (url == null) {
						succ = false;
						break;
					}
					Db.update("insert into sys_info_ext_cover (infoId,url,createAt ) VALUES (?,?,?)", infoId, url,
							new Date());
				}
				return succ;
			}
		});
		return succ ? RetKit.ok() : RetKit.fail();
	}

	public RetKit update(SysInfo info, List<String> extCoverList) {
		if (info == null) {
			return RetKit.fail("上傳參數有誤");
		}
		info.setAccount("text");
		info.setAccountId(1);
		info.setCreateAt(new Date());
		info.setUpdateAt(new Date());

		boolean succ = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean succ = true;
				Integer infoId = info.getId();
				// 删除配图
				Db.update("delete from sys_info_ext_cover where infoId = ?", infoId);
				// 更新封面地址
				String cover = UploadFileMoveKit.move(info.getCover(), UPLOAD_PATH + "/" + infoId);
				if (StrKit.isBlank(cover)) {
					return false;
				}
				// Xss过滤
				String content = JsoupKit.fiterContent(info.getContent(), UPLOAD_PATH + "/" + infoId);
				succ = info.setCover(cover).setContent(content).update();
				// 插入配图地址
				for (String extCover : extCoverList) {
					String url = UploadFileMoveKit.move(extCover, UPLOAD_PATH + "/" + infoId);
					if (url == null) {
						succ = false;
						break;
					}
					Db.update("insert into sys_info_ext_cover (infoId,url,createAt ) VALUES (?,?,?)", infoId, url,new Date());
				}
				return succ;
			}
		});
		return succ ? RetKit.ok() : RetKit.fail();
	}

	// 推送学校跟资讯关联
	private boolean createPushSchool(Integer id, String area, List<String> schoolIds) {
		if (StrKit.isBlank(area) || (StrKit.notBlank(area) && schoolIds.size() == 0)) {
			return false;
		}
		List<Record> recordList = new ArrayList<>();
		// 如果area为0则为推送全部
		if (area.equals("0")) {
			List<Integer> schoolIdList = Db.query("select id from school where status = ?", School.STATUS_OK);
			for (Integer tempSchoolId : schoolIdList) {
				Record re = new Record().set("infoId", id).set("schoolId", tempSchoolId).set("createAt", new Date());
				recordList.add(re);
			}
		} else {
			// 如果shcoolIds为0，则推送该区域全部
			if (schoolIds.size() == 1 && schoolIds.get(0).equals("0")) {
				List<Integer> schoolIdList = Db.query("select id from school where status = ? and area = ?",School.STATUS_OK, area);
				for (Integer tempSchoolId : schoolIdList) {
					Record re = new Record().set("infoId", id).set("schoolId", tempSchoolId).set("createAt",new Date());
					recordList.add(re);
				}
			} else {
				for (String tempSchoolId : schoolIds) {
					Record re = new Record().set("infoId", id).set("schoolId", tempSchoolId).set("createAt",new Date());
					recordList.add(re);
				}
				Db.batchSave("sys_info_push_school", recordList, 500);
			}
		}

		return true;
	}

}
