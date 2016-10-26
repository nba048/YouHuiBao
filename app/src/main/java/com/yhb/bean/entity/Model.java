package com.yhb.bean.entity;

/**
 * 系统模块
 * 
 * @author 宋昌明
 *
 */
public class Model extends AEntity {
	/**
	 * 系统id String
	 */
	private String modelId;
	/**
	 * 系统名称 String
	 */
	private String modelName;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
