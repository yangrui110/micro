package com.yangrui.micro.user.service;

import com.yangrui.micro.user.entity.Account;

public interface AccountService {

	public Account login(String name,String password);
	
	public void update(Account account);
	
}
