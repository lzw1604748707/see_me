package com.sandu.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSmSysAdmin<M extends BaseSmSysAdmin<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setRoleId(java.lang.Integer roleId) {
		set("roleId", roleId);
		return (M)this;
	}
	
	public java.lang.Integer getRoleId() {
		return getInt("roleId");
	}

	public M setUsername(java.lang.String username) {
		set("username", username);
		return (M)this;
	}
	
	public java.lang.String getUsername() {
		return getStr("username");
	}

	public M setPassword(java.lang.String password) {
		set("password", password);
		return (M)this;
	}
	
	public java.lang.String getPassword() {
		return getStr("password");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public M setLastLoginAt(java.util.Date lastLoginAt) {
		set("lastLoginAt", lastLoginAt);
		return (M)this;
	}
	
	public java.util.Date getLastLoginAt() {
		return get("lastLoginAt");
	}

	public M setCreateAt(java.util.Date createAt) {
		set("createAt", createAt);
		return (M)this;
	}
	
	public java.util.Date getCreateAt() {
		return get("createAt");
	}

	public M setUpdateAt(java.util.Date updateAt) {
		set("updateAt", updateAt);
		return (M)this;
	}
	
	public java.util.Date getUpdateAt() {
		return get("updateAt");
	}

}