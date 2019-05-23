package com.sandu.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSysUser<M extends BaseSysUser<M>> extends Model<M> implements IBean {

	public M setId(java.lang.String id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.String getId() {
		return getStr("id");
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
		set("user_type", userType);
		return (M)this;
	}
	
	public java.lang.String getUserType() {
		return getStr("user_type");
	}

	public M setPwdQuestion(java.lang.String pwdQuestion) {
		set("pwd_question", pwdQuestion);
		return (M)this;
	}
	
	public java.lang.String getPwdQuestion() {
		return getStr("pwd_question");
	}

	public M setPwdQuestionAnswer(java.lang.String pwdQuestionAnswer) {
		set("pwd_question_answer", pwdQuestionAnswer);
		return (M)this;
	}
	
	public java.lang.String getPwdQuestionAnswer() {
		return getStr("pwd_question_answer");
	}

	public M setFreezeDate(java.util.Date freezeDate) {
		set("freeze_date", freezeDate);
		return (M)this;
	}
	
	public java.util.Date getFreezeDate() {
		return get("freeze_date");
	}

	public M setFreezeCause(java.lang.String freezeCause) {
		set("freeze_cause", freezeCause);
		return (M)this;
	}
	
	public java.lang.String getFreezeCause() {
		return getStr("freeze_cause");
	}

	public M setStatus(java.lang.String status) {
		set("status", status);
		return (M)this;
	}
	
	public java.lang.String getStatus() {
		return getStr("status");
	}

	public M setCreateDate(java.util.Date createDate) {
		set("create_date", createDate);
		return (M)this;
	}
	
	public java.util.Date getCreateDate() {
		return get("create_date");
	}

	public M setFollowedCount(java.math.BigDecimal followedCount) {
		set("followed_count", followedCount);
		return (M)this;
	}
	
	public java.math.BigDecimal getFollowedCount() {
		return get("followed_count");
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

}