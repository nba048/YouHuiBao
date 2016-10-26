package com.yhb.mvp.pre.i;

public interface IRegisterPre {
	/**
	 * 请求注册验证码
	 */
	void requestCode();

	/**
	 * @param string
	 */
	void requestCodeMsg(String string);

	/**
	 * 不解释
	 */
	void onDestroy();

}
