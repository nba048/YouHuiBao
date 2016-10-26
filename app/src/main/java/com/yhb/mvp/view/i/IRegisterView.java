package com.yhb.mvp.view.i;

public interface IRegisterView {
	/**
	 * 返回需要请求注册的电话号码
	 * 
	 * @return
	 */
	String getNumberByCode();

	/**
	 * 当手机号验证成功
	 * 
	 * @param string
	 */
	void onPhoneNumberSuccess(String msg);

	/**
	 * 当手机号验证失败
	 * 
	 * @param string
	 */
	void onPhoneNumberFail(String msg);

	/**
	 * 当手机号请求异常
	 * 
	 * @param msg
	 */
	void onPhoneNumberException(String msg);

	/**
	 * 请求验证码模式
	 */
	void onPhoneNumberCodeMode(String msg);

	/**
	 * 验证码错误
	 * 
	 * @param msg
	 */
	void onPhoneNumberError(String msg);

	/**
	 * 验证码失效，请重新验证
	 * 
	 * @param msg
	 */
	void onPhoneNumberExpired(String msg);

	/**
	 * 验证码输入错误超过3次，请重新验证
	 * 
	 * @param msg
	 */
	void onPhoneNumberInputOver(String msg);

	
}
