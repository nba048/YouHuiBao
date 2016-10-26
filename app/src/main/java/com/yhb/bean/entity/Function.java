package com.yhb.bean.entity;

/**
 * 用户对应的功能列表
 * 
 * @author 宋昌明
 *
 */
public class Function extends AEntity{
	/**
	 * 功能名称
	 */
	private String name;
	/**
	 * 请求接口地址
	 */
	private String url;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Function [name=" + name + ", url=" + url + "]";
	}
	
	
}
