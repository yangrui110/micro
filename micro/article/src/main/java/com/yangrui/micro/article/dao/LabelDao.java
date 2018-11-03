package com.yangrui.micro.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangrui.micro.article.entity.Label;

@Mapper
public interface LabelDao {

	@Insert(value="insert into label(id,label)values(#{id},#{label})")
	public void insert(Label label);
	
	@Update(value="update label set label=#{label} where id=#{id}")
	public void update(Label label);
	
	@Select(value="select * from label where id=#{id}")
	public Label findById(String id);
	
	@Select(value="select * from label order by create_time")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="label",property="label")
	})
	public List<Label> findAll();
	
	@Delete(value="delete from label where id =#{id}")
	public void deleteById(String id);
}
