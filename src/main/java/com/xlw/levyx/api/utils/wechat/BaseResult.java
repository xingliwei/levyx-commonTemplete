package com.xlw.levyx.api.utils.wechat;

/**
 * 微信请求状态数据
* @author bmwm.cn
 *
 */
public class BaseResult {

	private String errcode;
	private String errmsg;

	public BaseResult() {
	}

	public BaseResult(String errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
