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
import com.sandu.common.kit.RetKit;
import com.sandu.common.kit.UploadFileMoveKit;
import com.sandu.common.model.School;
import com.sandu.common.model.SysAdv;
import com.xiaoleilu.hutool.io.FileUtil;

public class AdvListService {

	public static final AdvListService me = new AdvListService();
	public static final String UPLOAD_PATH = "/upload/admin/adv";

	static final SysAdv dao = SysAdv.dao;

	public Page<SysAdv> paginate(int pageNumber, int pageSize, Kv kv) {
		Page<SysAdv> page = dao.paginate(pageNumber, pageSize, dao.getSqlPara("sys_adv.admin-paginate", kv));
		return page;
	}

	public boolean updateShelfStatus(Integer id, boolean flag) {
		return new SysAdv().setId(id).setStatus(flag).update();
	}

	public boolean remove(Integer id) {
		SysAdv adv = dao.findById(id);
		String path = PathKit.getWebRootPath() + UPLOAD_PATH + "/" + adv.getId();
		try {
			FileUtil.del(path);
		} catch (IOException e) {
			e.printStackTrace();
			LogKit.error(e.getMessage());
		}
		boolean succ = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				Db.update("delete from sys_adv_push_school where advId = ?",id);
				adv.delete();
				return true;
			}
		});
		return succ;
	}

	public SysAdv findById(Integer id) {
		return dao.findById(id);
	}

	public List<Record> findSchoolListByArea(String area) {
		return Db.find("select id,name from school where status = ? and area = ?", School.STATUS_OK, area);
	}

	public RetKit save(SysAdv adv, List<String> schoolIds, String area) {
		if (adv == null) {
			return RetKit.fail("參數有誤");
		}
		adv.setAccount("text");
		adv.setAccountId(1);
		adv.setCreateAt(new Date());
		adv.setUpdateAt(new Date());

		boolean succ = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				boolean succ = true;
				adv.save();
				Integer advId = adv.getId();
				// 更新封面地址
				String cover = UploadFileMoveKit.move(adv.getCover(), UPLOAD_PATH + "/" + advId);
				if (StrKit.isBlank(cover)) {
					return false;
				}
				adv.setCover(cover).update();

				createPushSchool(advId, area, schoolIds);	
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
					Db.batchSave("sys_adv_push_school", recordList, 500);
				}
			}

			return true;
		}

		public RetKit update(SysAdv adv) {
			if (adv == null) {
				return RetKit.fail("參數有誤");
			}
			adv.setAccount("text");
			adv.setAccountId(1);
			adv.setCreateAt(new Date());
			adv.setUpdateAt(new Date());

			boolean succ = Db.tx(new IAtom() {
				@Override
				public boolean run() throws SQLException {
					boolean succ = true;
					Integer advId = adv.getId();
					// 更新封面地址
					String cover = UploadFileMoveKit.move(adv.getCover(), UPLOAD_PATH + "/" + advId);
					if (StrKit.isBlank(cover)) {
						return false;
					}
					adv.setCover(cover).update();

					return succ;
				}
			});
			return succ ? RetKit.ok() : RetKit.fail();
		}

	
}
