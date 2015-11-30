package com.github.neiplz.entity;

public class LoginCheckResult {

	private int resultCode = 0;
	private String errorMsg;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "LoginCheck [resultCode=" + resultCode + ", errorMsg="
				+ errorMsg + "]";
	}

}
