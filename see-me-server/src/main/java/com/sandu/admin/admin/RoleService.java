package com.sandu.admin.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.ehcache.CacheKit;
import com.sandu.common.constant.CacheConstant;
import com.sandu.common.kit.RetKit;
import com.sandu.common.model.SysMenu;
import com.sandu.common.model.SysRole;
import com.sandu.common.model.SysRoleMenu;

public class RoleService {

	public static final RoleService me = new RoleService();
	static final SysMenu menuDao = SysMenu.dao;
	static final SysRoleMenu roleMenuDao = SysRoleMenu.dao;
	static final SysRole roleDao = SysRole.dao;

	public Object[] findMenuIdsByRole(Integer roleId) {
		List<SysMenu> menuList = new ArrayList<>();
		if (roleId == 0) {
			menuList = menuDao.find("select * from sys_menu where status = ?  order by level,sort", SysMenu.STATUS_OK);
		} else {
			menuList = menuDao.find(
					"select * from sys_menu where status = ? and id in (select menuId from sys_role_menu where roleId = ? ) order by level,sort",
					SysMenu.STATUS_OK, roleId);
		}
		List<Integer> menuIdList = new ArrayList<>();
		for (SysMenu sysMenu : menuList) {
			menuIdList.add(sysMenu.getId());
		}
		return menuIdList.toArray();
	}

	public Object[] findMenuNamesByRole(Integer roleId) {
		List<SysMenu> menuList = new ArrayList<>();
		// 如果是超管
		if (roleId == 0) {
			menuList = menuDao.find("select name from sys_menu where status = ? order by level,sort",
					SysMenu.STATUS_OK);
		} else {
			menuList = menuDao.find(
					"select name from sys_menu where status = ? and id in (select menuId from sys_role_menu where roleId = ? ) order by level,sort",
					SysMenu.STATUS_OK, roleId);
		}
		return menuList.toArray();
	}

	public List<SysMenu> findMenuListByRoleId(Integer roleId) {
		List<SysMenu> menuList = CacheKit.get(CacheConstant.SYS_ROLE_MENU, roleId);
		if (menuList == null) {
			if (roleId == 0) {
				menuList = menuDao.find("select * from sys_menu where status = ?  order by level,sort",
						SysMenu.STATUS_OK);
			} else {
				menuList = menuDao.find(
						"select * from sys_menu where status = ? and id in (select menuId from sys_role_menu where roleId = ? ) order by level,sort",
						SysMenu.STATUS_OK, roleId);
			}
			CacheKit.put(CacheConstant.SYS_ROLE_MENU, roleId,menuList);
		}
		return menuList;
	}
	
	public List<Kv> findMenus() {
		List<SysMenu> menuList = menuDao.find("select * from sys_menu where status = ? order by level,sort",
				SysMenu.STATUS_OK);
		return getChildrenMenu(menuList, 0);
	}

	public List<Kv> getChildrenMenu(List<SysMenu> list, Integer parentId) {
		List<Kv> kvList = new ArrayList<>();
		for (SysMenu sysMenu : list) {
			if (parentId > 0) {
				Kv kv = new Kv();
				if (sysMenu.getParentId() == parentId) {
					kv.set("id", sysMenu.getId());
					kv.set("label", sysMenu.getName());
					if (sysMenu.getIsParent()) {
						kv.set("children", getChildrenMenu(list, sysMenu.getId()));
					}
					kvList.add(kv);
				}
			} else if (sysMenu.getIsParent()) {
				Kv kv = new Kv();
				kv.set("id", sysMenu.getId());
				kv.set("label", sysMenu.getName());
				kv.set("children", getChildrenMenu(list, sysMenu.getId()));
				kvList.add(kv);
			}
		}
		return kvList;
	}

	public List<SysRole> list() {
		List<SysRole> list = roleDao.find("select a.*,(select count(*) from sys_account b where a.id = b.roleId ) as roleAccount from sys_role a order by createAt desc");
		for (SysRole role : list) {
			role.put("menus", findMenuIdsByRole(role.getId()));
		}
		return list;
	}

	public RetKit save(List<String> menus, String name) {
		if (isExistName(name)) {
			return RetKit.fail("权限名称已存在");
		}
		boolean succ = Db.tx(new IAtom() {
			@Override
			public boolean run() throws SQLException {
				SysRole role = new SysRole().setName(name).setCreateAt(new Date()).setUpdateAt(new Date());
				boolean succ = role.save();
				if (!succ) {
					return false;
				}
				List<SysRoleMenu> list = new ArrayList<>();
				for (String menuId : menus) {
					SysRoleMenu roleMenu = new SysRoleMenu().setMenuId(Integer.valueOf(menuId)).setRoleId(role.getId())
							.setCreateAt(new Date());
					list.add(roleMenu);
				}
				Db.batchSave(list, list.size());
				return true;
			}
		});
		return succ ? RetKit.ok() : RetKit.fail();

	}

	public RetKit update(List<String> menus, SysRole role) {
		if (role.getId() == null || role.getId() == 0) {
			return RetKit.fail("参数错误");
		}
		if (isExistName(role.getName(),role.getId())) {
			return RetKit.fail("权限组已存在");
		}
		boolean succ = role.setUpdateAt(new Date()).update();
		// 先删除再插入
		deleteRoleMenuByRoleId(role.getId());
		List<SysRoleMenu> list = new ArrayList<>();
		for (String menuId : menus) {
			SysRoleMenu roleMenu = new SysRoleMenu().setMenuId(Integer.parseInt(menuId)).setRoleId(role.getId()).setCreateAt(new Date());
			list.add(roleMenu);
		}
		Db.batchSave(list, list.size());
		//更新缓存
		CacheKit.remove(CacheConstant.SYS_ROLE_MENU, role.getId());
		return succ ? RetKit.ok() : RetKit.fail();
	}

	private boolean deleteRoleMenuByRoleId(long id) {
		return Db.update("delete from sys_role_menu where roleId = ?", id) > 1;
	}

	public RetKit remove(Integer id) {
		if (isExistAccountRole(id)) {
			return RetKit.fail("该权限组被使用中，不能刪除");
		}
		boolean succ = roleDao.deleteById(id);
		deleteRoleMenuByRoleId(id);
		//更新缓存
		CacheKit.remove(CacheConstant.SYS_ROLE_MENU, id);
		return succ ? RetKit.ok() : RetKit.fail();
	}

	public boolean isExistAccountRole(Integer id) {
		return Db.queryInt("select id from sys_account where roleId = ? limit 1", id) != null;
	}
	
	public boolean isExistName(String name) {
		return Db.queryInt("select id from sys_account where name = ? limit 1", name.trim()) != null;
	}

	public boolean isExistName(String name,Integer id) {
		return Db.queryInt("select id from sys_account where name = ? and id != ? limit 1", name.trim(),id) != null;
	}
}
