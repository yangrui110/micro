package com.yangrui.micro.user.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangrui.micro.user.entity.Account;

@Mapper
public interface AccountDao {

	@Select(value="select * from account where name = #{name} and password = #{password}")
	public Account findByNameAndPassword(@Param("name") String name,@Param("password") String password);
	
	@Update(value="update account set name= #{name},password = #{password}")
	public void update(Account account);
	
	@Select(value="select * from account where id = #{id}")
	public Account findById(String id);
}
