package com.sandu.admin.user;

import java.sql.SQLException;
import java.util.Date;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.sandu.common.constant.CacheConstant;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.School;
import com.sandu.common.model.SchoolAccount;
import com.xiaoleilu.hutool.util.RandomUtil;
/**
 * 学校列表相关业务
 * @ClassName:SchoolListService   
 * @author:Seven
 * @date:2017年12月14日 上午10:59:50
 */
public class SchoolListService {
	
	public static final	SchoolListService me = new SchoolListService();
	private static final School dao = School.dao;
	private static final SchoolAccount accountDao = SchoolAccount.dao;
	
	
	public School findById(Integer id){
		return dao.findById(id);
	}
	
	/**
	 * 逻辑删除
	 */
	public boolean deleteById(Integer id){
		return Db.update("update school set status = ? where id = ?",School.STATUS_DELETE,id) > 0;
	}

	public Page<School> paginate(int pageNumber,int pageSize,Kv kv){
		return dao.paginate(pageNumber, pageSize, dao.getSqlPara("school.admin-paginate", kv));
	}

	public boolean update(School school) {
		return school.setUpdateAt(new Date()).update();
	}

	public RetKit save(School school) {
		RetKit ret = new RetKit();
		boolean succ = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				school.setJoinAt(new Date()).setUpdateAt(new Date()).setCreateAt(new Date()).save();
				//判断手机号有没有被注册，如果有，返回失败
				if (accountDao.findFirst("select id from school_account where username = ? limit 1",school.getContact())!=null) {
					ret.setFail("該手機已經被註冊！");
					return false;
				}
				String password = "123456";
				String salt = HashKit.generateSaltForSha256();
				password = HashKit.sha256(salt + password);
				SchoolAccount account = new SchoolAccount();
				account.setIsAdmin(true);
				account.setName(school.getLeader());
				account.setUsername(school.getContact());
				account.setPassword(password);
				account.setSalt(salt);
				account.setRoleId(0);
				account.setSchoolId(school.getId());
				account.setUpdateAt(new Date());
				account.setCreateAt(new Date());
				account.save();
				//学校信息表需要回填adminId
				school.setAdminId(account.getId()).setUpdateAt(new Date()).update();
				
				return true;
			}
		});
		return succ ? RetKit.ok():ret;
	}

	public boolean stopById(Integer id) {
    	//这个业务场景极少发生，学校后台web缓存量少，全清避免更多逻辑操作
		CacheKit.removeAll(CacheConstant.SCHOOL);
		return Db.update("update school set status = ? where id = ?",School.STATUS_STOP,id) > 0;
	}
	
	public boolean enableById(Integer id) {
		//这个业务场景极少发生，学校后台web缓存量少，全清避免更多逻辑操作
		CacheKit.removeAll(CacheConstant.SCHOOL);
		return Db.update("update school set status = ? where id = ?",School.STATUS_OK,id) > 0;
	}

	public RetKit resetLeaderPhone(Integer id) {
		School school = findById(id);
		if (school.isStop() || school.isDelete()) {
			return RetKit.fail("重置失敗，該學校已經被停用或刪除");
		}
		SchoolAccount account = accountDao.findById(school.getAdminId());
		String newPassword = RandomUtil.randomNumbers(6);
		String salt = HashKit.generateSaltForSha256();
		newPassword = HashKit.sha256(salt + newPassword);
	    boolean succ = account.setSalt(salt).setPassword(newPassword).update();
	    if (succ) {
	    	//这个业务场景极少发生，学校后台web缓存量少，全清避免更多逻辑操作
			CacheKit.removeAll(CacheConstant.SCHOOL_ACCOUNT);
		}
	    return succ ? RetKit.ok("password",newPassword) : RetKit.fail("重置失敗");
	}

	
	
}
