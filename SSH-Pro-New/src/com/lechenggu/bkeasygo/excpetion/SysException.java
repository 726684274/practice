package com.lechenggu.bkeasygo.excpetion;

public class SysException extends Exception {
	private int errCode;
	private String errMsg;
	public SysException(int errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}