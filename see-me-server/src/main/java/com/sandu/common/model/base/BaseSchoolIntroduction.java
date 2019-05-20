package com.sandu.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSchoolIntroduction<M extends BaseSchoolIntroduction<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setSchoolId(java.lang.Integer schoolId) {
		set("schoolId", schoolId);
		return (M)this;
	}
	
	public java.lang.Integer getSchoolId() {
		return getInt("schoolId");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public M setContent(java.lang.String content) {
		set("content", content);
		return (M)this;
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public M setUpdateAt(java.util.Date updateAt) {
		set("updateAt", updateAt);
		return (M)this;
	}
	
	public java.util.Date getUpdateAt() {
		return get("updateAt");
	}

}
