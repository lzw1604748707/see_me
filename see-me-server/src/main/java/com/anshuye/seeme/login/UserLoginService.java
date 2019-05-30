package com.anshuye.seeme.login;

import java.util.Date;
import java.util.List;

import com.anshuye.common.constant.CacheConstant;
import com.anshuye.common.kit.RetKit;
import com.anshuye.common.model.SmSysUser;
import com.anshuye.common.model.SysAccount;
import com.anshuye.common.model.SysSession;
import com.anshuye.seeme.admin.RoleService;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;

public class UserLoginService {
	
	public static final UserLoginService me = new UserLoginService();
	static final SmSysUser userDao = SmSysUser.dao;
	
	public RetKit doLogin(String account,String password,String loginIp){
		if (StrKit.isBlank(account) || StrKit.isBlank(password)) {
			return RetKit.fail("用户名或者密码不能为空");
		}
		account = account.trim();
		password = password.trim();
		
		SmSysUser user = userDao.findFirst("select * from sm_sys_user where account = ? limit 1", account);
		if (user == null) {
			return RetKit.fail("账户不存在");
		}
		if(user.getStatus()==1){
			return RetKit.fail("账户被停用");
		}
		String salt = user.getSalt();
		String hashPassword = HashKit.sha256(salt + password);
		if (hashPassword.equals(user.getPassword())) {
			// 过期时间为7天，跟前端保持一致
			long liveSeconds =  7 * 24 * 60 * 60;
			// expireAt 用于设置 session 的过期时间点，需要转换成毫秒
			long expireAt = System.currentTimeMillis() + (liveSeconds * 1000);
			// 生成sessionId
			String sessionId = HashKit.sha256(StrKit.getRandomUUID());
			// 保存登录 session 到数据库
			SysSession session = new SysSession();
			session.setId(sessionId);
			session.setUserId(user.getId());
			session.setExpireAt(expireAt);
			session.setCareateAt(new Date());
			session.save();
			// 移除 password 与 salt 属性值
			user.removeSensitiveInfo();            
			// 往Account放一份sessionId
			user.put("token", sessionId);
			//放进缓存
			CacheKit.put(CacheConstant.SYS_USER, sessionId, user);
			//生成登陆日志
			createLoginLog(user.getId(), loginIp);
			user.setLastLoginDate(new Date()).update();
			return RetKit.ok("user", user);
		} else {
			return RetKit.fail("密码错误");
		}
	}

	
	public void logout(String sessionId){
		if (StrKit.notBlank(sessionId)) {
			CacheKit.remove(CacheConstant.SYS_USER, sessionId);
			SysSession.dao.deleteById(sessionId);
		}
	}
	
	
	/**
	 * 根据userId 清楚账号缓存
	 * 禁止多地登录
	 */
	public void clearCacheByAccountId(Integer userId){
		List<SysSession> list = SysSession.dao.find("select * from sys_session where userId = ?",userId);
		for (SysSession sysSession : list) {
			if (sysSession.isExpired()) {
				sysSession.delete();
			}
			CacheKit.remove(CacheConstant.SYS_USER, sysSession.getId());
		}
	}
	
	public SmSysUser getAccountCacheWithSessionId(String sessionId) {
		return CacheKit.get(CacheConstant.SYS_USER, sessionId);
	}
	
	
	public SmSysUser loginWithSessionId(String sessionId, String loginIp) {
		SysSession session = SysSession.dao.findById(sessionId);
		if (session == null) {      
			return null;
		}
//		// 被动式删除过期数据，此外还需要定时线程来主动清除过期数据
//		if (session.isExpired()) { 
//			session.delete();		
//			return null;
//		}
		SmSysUser loginSmUser = userDao.findById(session.getUserId());
		// 找到 loginSmUser 并且 是正常状态 才允许登录
		if (loginSmUser != null && loginSmUser.getStatus()!=1) {
			loginSmUser.removeSensitiveInfo();      
			// 往Account放一份sessionId
			loginSmUser.put("sessionId", sessionId);
			CacheKit.put(CacheConstant.SYS_USER, sessionId, loginSmUser);
			createLoginLog(loginSmUser.getId(), loginIp);
			return loginSmUser;
		}
		return null;
	}

	/**
	 * 创建登录日志
	 */
	private void createLoginLog(Integer userId, String loginIp) {
		Record loginLog = new Record().set("userId", userId).set("ip", loginIp).set("loginAt", new Date());
		Db.save("sys_login_log", loginLog);
	}
	
	
}
