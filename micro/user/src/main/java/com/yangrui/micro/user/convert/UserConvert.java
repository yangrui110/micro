package com.yangrui.micro.user.convert;

import org.springframework.beans.BeanUtils;

import com.yangrui.micro.user.entity.Account;
import com.yangrui.micro.user.vo.AccountVo;

public class UserConvert {

	public static AccountVo parseToVo(Account account) {
		AccountVo accountVo=new AccountVo();
		BeanUtils.copyProperties(account, accountVo);
		return accountVo;
	}
}
