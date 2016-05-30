package com.foxinmy.umeng4j.exception;

/**
 * 调用接口抛出的异常
 * 
 * @className UmengException
 * @author jinyu(foxinmy@gmail.com).hu
 * @date 2014年4月10日
 * @since JDK 1.7
 * @see
 */
public class UmengException extends Exception {

	private static final long serialVersionUID = 7148145661883468514L;

	private String errorCode;
	private String errorMsg;

	public UmengException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public UmengException(String errorMsg) {
		this.errorCode = "-1";
		this.errorMsg = errorMsg;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	@Override
	public String getMessage() {
		return this.errorCode + "," + this.errorMsg;
	}
}
