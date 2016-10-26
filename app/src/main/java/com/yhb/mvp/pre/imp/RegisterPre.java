package com.yhb.mvp.pre.imp;

import com.yhb.mvp.pre.i.IRegisterPre;
import com.yhb.mvp.view.i.IRegisterView;

public class RegisterPre implements IRegisterPre {
	private IRegisterView mView;

	public RegisterPre(IRegisterView view) {
		if (view == null) {
			throw new RuntimeException("IRegestView 不能为null!");
		}
		mView = view;
	}

	@Override
	public void requestCode() {
		// CIAService.startVerification(mView.getNumberByCode(), new
		// cn.ciaapp.sdk.VerificationListener() {
		// @Override
		// public void onStateChange(int status, String msg, String transId) {
		// switch (status) {
		// case CIAService.VERIFICATION_SUCCESS: // 验证成功
		// mView.onPhoneNumberSuccess(CIAService.getRealSecurityCode());
		// break;
		// case CIAService.SECURITY_CODE_MODE: // 验证码模式
		// // 进入输入验证码的页面，并提示用户输入验证码
		// mView.onPhoneNumberCodeMode(CIAService.getSecurityCode());
		// break;
		// case CIAService.VERIFICATION_FAIL:
		// mView.onPhoneNumberFail(msg);
		// break;
		// default:
		// mView.onPhoneNumberException(msg);
		// }
		// }
		// });
	}

	@Override
	public void requestCodeMsg(String code) {
		// CIAService.verifySecurityCode(code, new
		// cn.ciaapp.sdk.VerificationListener() {
		// @Override
		// public void onStateChange(int status, String msg, String transId) {
		// switch (status) {
		// case CIAService.VERIFICATION_SUCCESS: // 验证成功
		// mView.onPhoneNumberSuccess(CIAService.getRealSecurityCode());
		// break;
		// case CIAService.SECURITY_CODE_WRONG: // 验证码输入错误
		// mView.onPhoneNumberError(msg);
		// break;
		// case CIAService.SECURITY_CODE_EXPIRED: // 验证码失效，需要重新验证
		// mView.onPhoneNumberExpired(msg);
		// break;
		// case CIAService.SECURITY_CODE_EXPIRED_INPUT_OVERRUN: //
		// 验证码输入错误次数过多(3次)，需要重新验证
		// mView.onPhoneNumberInputOver(msg);
		// break;
		// }
		// }
		// });

	}

	@Override
	public void onDestroy() {
		// CIAService.cancelVerification();
	}

}
