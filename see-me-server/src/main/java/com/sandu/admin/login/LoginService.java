package com.sandu.admin.login;

import java.util.Date;
import java.util.List;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.sandu.admin.admin.RoleService;
import com.sandu.common.constant.CacheConstant;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SysAccount;
import com.sandu.common.model.SysSession;

public class LoginService {
	
	public static final LoginService me = new LoginService();
	static final SysAccount accountDao = SysAccount.dao;
	
	public RetKit doLogin(String username,String password,String loginIp){
		if (StrKit.isBlank(username) || StrKit.isBlank(password)) {
			return RetKit.fail("用户名或者密码不能为空");
		}
		username = username.trim();
		password = password.trim();
		
		SysAccount account = accountDao.findFirst("select * from sys_account where username = ? limit 1", username);
		if (account == null) {
			return RetKit.fail("账户不存在");
		}
		if(account.isStatusLockId()){
			return RetKit.fail("账户被停用");
		}
		String salt = account.getSalt();
		String hashPassword = HashKit.sha256(salt + password);
		if (hashPassword.equals(account.getPassword())) {
			// 过期时间为7天，跟前端保持一致
			long liveSeconds =  7 * 24 * 60 * 60;
			// expireAt 用于设置 session 的过期时间点，需要转换成毫秒
			long expireAt = System.currentTimeMillis() + (liveSeconds * 1000);
			// 生成sessionId
			String sessionId = HashKit.sha256(StrKit.getRandomUUID());
			// 保存登录 session 到数据库
			SysSession session = new SysSession();
			session.setId(sessionId);
			session.setAccountId(account.getId());
			session.setExpireAt(expireAt);
			session.setCareateAt(new Date());
			session.save();
			// 移除 password 与 salt 属性值
			account.removeSensitiveInfo();            
			// 往Account放一份sessionId
			account.put("sessionId", sessionId);
			//菜单数据
			account.put("menus", RoleService.me.findMenuNamesByRole(account.getRoleId()));
			//放进缓存
			CacheKit.put(CacheConstant.SYS_ACCOUNT, sessionId, account);
			//生成登陆日志
			createLoginLog(account.getId(), loginIp);
			account.setLastLoginAt(new Date()).update();
			return RetKit.ok("account", account);
		} else {
			return RetKit.fail("密码错误");
		}
	}

	
	public void logout(String sessionId){
		if (StrKit.notBlank(sessionId)) {
			CacheKit.remove(CacheConstant.SYS_ACCOUNT, sessionId);
			SysSession.dao.deleteById(sessionId);
		}
	}
	
	
	/**
	 * 根据accountId 清楚账号缓存
	 * 用于系统管理员操作账号
	 */
	public void clearCacheByAccountId(Integer accountId){
		List<SysSession> list = SysSession.dao.find("select * from sys_session where accountId = ?",accountId);
		for (SysSession sysSession : list) {
			if (sysSession.isExpired()) {
				sysSession.delete();
			}
			CacheKit.remove(CacheConstant.SYS_ACCOUNT, sysSession.getId());
		}
	}
	
	public SysAccount getAccountCacheWithSessionId(String sessionId) {
		return CacheKit.get(CacheConstant.SYS_ACCOUNT, sessionId);
	}
	
	
	public SysAccount loginWithSessionId(String sessionId, String loginIp) {
		SysSession session = SysSession.dao.findById(sessionId);
		if (session == null) {      
			return null;
		}
		// 被动式删除过期数据，此外还需要定时线程来主动清除过期数据
		if (session.isExpired()) { 
			session.delete();		
			return null;
		}
		SysAccount loginSysAccount = accountDao.findById(session.getAccountId());
		// 找到 loginSysAccount 并且 是正常状态 才允许登录
		if (loginSysAccount != null && loginSysAccount.isStatusOk()) {
			loginSysAccount.removeSensitiveInfo();      
			// 往Account放一份sessionId
			loginSysAccount.put("sessionId", sessionId);
			//菜单数据
			loginSysAccount.put("menus", RoleService.me.findMenuNamesByRole(loginSysAccount.getRoleId()));
			CacheKit.put(CacheConstant.SYS_ACCOUNT, sessionId, loginSysAccount);
			createLoginLog(loginSysAccount.getId(), loginIp);
			return loginSysAccount;
		}
		return null;
	}

	/**
	 * 创建登录日志
	 */
	private void createLoginLog(Integer accountId, String loginIp) {
		Record loginLog = new Record().set("accountId", accountId).set("ip", loginIp).set("loginAt", new Date());
		Db.save("sys_login_log", loginLog);
	}

	public static void main(String[] args) {
		System.out.println("ca775bb614faa39648c154cbf9d48ee214f0dcda83e52a8b2fb866b4cd94fb11".length());
	}
	
	
	
}
