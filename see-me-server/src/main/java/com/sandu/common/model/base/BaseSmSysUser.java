package com.sandu.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSmSysUser<M extends BaseSmSysUser<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Integer getId() {
		return getInt("id");
	}

	public M setAccount(java.lang.String account) {
		set("account", account);
		return (M)this;
	}
	
	public java.lang.String getAccount() {
		return getStr("account");
	}

	public M setPassword(java.lang.String password) {
		set("password", password);
		return (M)this;
	}
	
	public java.lang.String getPassword() {
		return getStr("password");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setEmail(java.lang.String email) {
		set("email", email);
		return (M)this;
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public M setMobile(java.lang.String mobile) {
		set("mobile", mobile);
		return (M)this;
	}
	
	public java.lang.String getMobile() {
		return getStr("mobile");
	}

	public M setSex(java.lang.String sex) {
		set("sex", sex);
		return (M)this;
	}
	
	public java.lang.String getSex() {
		return getStr("sex");
	}

	public M setAvatar(java.lang.String avatar) {
		set("avatar", avatar);
		return (M)this;
	}
	
	public java.lang.String getAvatar() {
		return getStr("avatar");
	}

	public M setSign(java.lang.String sign) {
		set("sign", sign);
		return (M)this;
	}
	
	public java.lang.String getSign() {
		return getStr("sign");
	}

	public M setUserType(java.lang.String userType) {
		set("userType", userType);
		return (M)this;
	}
	
	public java.lang.String getUserType() {
		return getStr("userType");
	}

	public M setPwdQuestion(java.lang.String pwdQuestion) {
		set("pwdQuestion", pwdQuestion);
		return (M)this;
	}
	
	public java.lang.String getPwdQuestion() {
		return getStr("pwdQuestion");
	}

	public M setPwdQuestionAnswer(java.lang.String pwdQuestionAnswer) {
		set("pwdQuestionAnswer", pwdQuestionAnswer);
		return (M)this;
	}
	
	public java.lang.String getPwdQuestionAnswer() {
		return getStr("pwdQuestionAnswer");
	}

	public M setFreezeDate(java.util.Date freezeDate) {
		set("freezeDate", freezeDate);
		return (M)this;
	}
	
	public java.util.Date getFreezeDate() {
		return get("freezeDate");
	}

	public M setFreezeCause(java.lang.String freezeCause) {
		set("freezeCause", freezeCause);
		return (M)this;
	}
	
	public java.lang.String getFreezeCause() {
		return getStr("freezeCause");
	}

	public M setStatus(java.lang.String status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.String getStatus() {
		return getStr("status");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("createDate", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("createDate");
	}

	public M setArea(java.lang.String area) {
		set("area", area);
		return (M)this;
	}
	
	public java.lang.String getArea() {
		return getStr("area");
	}

	public M setWebsite(java.lang.String website) {
		set("website", website);
		return (M)this;
	}
	
	public java.lang.String getWebsite() {
		return getStr("website");
	}

	public M setCareer(java.lang.String career) {
		set("career", career);
		return (M)this;
	}
	
	public java.lang.String getCareer() {
		return getStr("career");
	}

	public M setLastLoginDate(java.util.Date lastLoginDate) {
		set("lastLoginDate", lastLoginDate);
		return (M)this;
	}
	
	public java.util.Date getLastLoginDate() {
		return get("lastLoginDate");
	}

	public M setSalt(java.lang.String salt) {
		set("salt", salt);
		return (M)this;
	}
	
	public java.lang.String getSalt() {
		return getStr("salt");
	}

	public M setUpdateDate(java.util.Date updateDate) {
		set("updateDate", updateDate);
		return (M)this;
	}
	
	public java.util.Date getUpdateDate() {
		return get("updateDate");
	}

	public M setIsDelete(java.lang.Integer isDelete) {
		set("isDelete", isDelete);
		return (M)this;
	}
	
	public java.lang.Integer getIsDelete() {
		return getInt("isDelete");
	}

}
