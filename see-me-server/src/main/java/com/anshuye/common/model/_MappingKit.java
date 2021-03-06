package com.anshuye.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("feedback", "id", Feedback.class);
		arp.addMapping("sm_photo_collection", "id", SmPhotoCollection.class);
		arp.addMapping("sm_photo_collection_record", "id", SmPhotoCollectionRecord.class);
		arp.addMapping("sm_photo_comment", "id", SmPhotoComment.class);
		arp.addMapping("sm_photo_field", "id", SmPhotoField.class);
		arp.addMapping("sm_photo_praise", "id", SmPhotoPraise.class);
		arp.addMapping("sm_photo_project", "id", SmPhotoProject.class);
		arp.addMapping("sm_sys_meassage", "id", SmSysMeassage.class);
		arp.addMapping("sm_sys_user", "id", SmSysUser.class);
		arp.addMapping("sm_sys_user_attention", "id", SmSysUserAttention.class);
		arp.addMapping("sys_account", "id", SysAccount.class);
		arp.addMapping("sys_adv", "id", SysAdv.class);
		arp.addMapping("sys_info", "id", SysInfo.class);
		arp.addMapping("sys_menu", "id", SysMenu.class);
		arp.addMapping("sys_role", "id", SysRole.class);
		arp.addMapping("sys_role_menu", "id", SysRoleMenu.class);
		arp.addMapping("sys_session", "id", SysSession.class);
	}
}

