package com.yangrui.util;

import lombok.Data;

@Data
public class ExceptionEntity {

	private int code;
	private String msg;
	private String path;
	
	public ExceptionEntity(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public ExceptionEntity(ResultEnum enum1) {
		this.code=enum1.getCode();
		this.msg=enum1.getMsg();
	}
	
}
