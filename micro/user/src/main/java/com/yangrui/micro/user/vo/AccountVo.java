package com.yangrui.micro.user.vo;

import lombok.Data;

@Data
public class AccountVo {
	private String id;

	private String name;
	private String password;
	
	private String token;
	private String prePath;
	
}
