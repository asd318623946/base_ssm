package com.libinyn.base.been;

import java.io.Serializable;

/**
 * ajax调用返回参数对象
 * @author bin.li01
 *
 */
public class ResponseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4661494594151843953L;

	/**
	 * 是否返回成功
	 */
	private boolean success;
	
	/**
	 * 错误信息
	 */
	private String erroMsg;
	
	/**
	 * 返回的数据
	 */
	private Object data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErroMsg() {
		return erroMsg;
	}

	public void setErroMsg(String erroMsg) {
		this.erroMsg = erroMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public ResponseVO(boolean success) {
		super();
		this.success = success;
	}

	public ResponseVO() {
		super();
	}

	public ResponseVO(boolean success, String erroMsg, Object data) {
		super();
		this.success = success;
		this.erroMsg = erroMsg;
		this.data = data;
	}
}
