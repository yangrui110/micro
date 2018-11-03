package com.yangrui.util;

public enum ResultEnum {

	OK(200,"调用成功"),
	ERROR(500,"未知错误");
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	private int code;
	private String msg;
	ResultEnum(int code,String msg){
		this.code=code;
		this.msg=msg;
	}
	
}
