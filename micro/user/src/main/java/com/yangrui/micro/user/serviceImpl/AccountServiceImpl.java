package com.yangrui.micro.user.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangrui.micro.user.dao.AccountDao;
import com.yangrui.micro.user.entity.Account;
import com.yangrui.micro.user.service.AccountService;
import com.yangrui.util.BaseException;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public Account login(String name, String password) {
		// TODO Auto-generated method stub
		Account account=accountDao.findByNameAndPassword(name, password);
		if(account==null) return null;
		return account;
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		Account account2=accountDao.findById(account.getId());
		if(account2==null) throw new BaseException(300,"[com.yangrui.micro.user.serviceImpl.AccountServiceImpl] update方法 执行时，查到的account2为 null");
		if(account.getName()!=null) {
			account2.setName(account.getName());
		}
		if(account.getPassword()!=null)
			account2.setPassword(account.getPassword());
		accountDao.update(account2);
	}

}
