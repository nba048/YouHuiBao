package com.yhb.bean.response;

import java.util.List;

import com.yhb.bean.entity.Model;

import cn.bmob.v3.BmobUser;

/**
 * 当前用户 用户登录
 * 
 * @author 宋昌明
 */
public class User extends BmobUser {
	/**
	 * 响应状态码 Integer 系统常量，具体数值意义见code码表。
	 */
	private String code;
	/**
	 * 用户类型标识 Integer 具体意思见用户类型码表
	 */
	private String type;
	/**
	 * 服务器返回登录标识 String 后台用于判断当前请求是否为有效请求。
	 */
	private String sessionId;
	/**
	 * 系统模块列表
	 */
	private List<Model> models;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<Model> getmModels() {
		return models;
	}

	public void setmModels(List<Model> mModels) {
		this.models = mModels;
	}

	@Override
	public String toString() {
		return "User [type=" + type + ", sessionId=" + sessionId + "]";
	}

}
