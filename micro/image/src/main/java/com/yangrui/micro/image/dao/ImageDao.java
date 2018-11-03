package com.yangrui.micro.image.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yangrui.micro.image.entity.Image;

@Mapper
public interface ImageDao {

	@Select(value="select * from image where id= #{id}")
	@Results({
		@Result(column="src",property="src")
		})
	public Image findById(String id);
	@Insert(value="insert into image(id,src) values(#{id},#{src})")
	public void insert(Image image);
}
