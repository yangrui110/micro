package com.yangrui.micro.banner.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import com.yangrui.micro.banner.entity.Banner;

@Mapper
public interface BannerDao {

	@Select(value="select * from banner where id=#{id}")
	@Results(id="result",value={
		@Result(property="id",column="id"),
		@Result(property="sort",column="sort"),
		@Result(property="image",column="img_id",one=@One(select="com.yangrui.micro.image.dao.ImageDao.findById",fetchType=FetchType.LAZY))
	})
	public Banner findById(String id);
	
	@Select(value= {"<script>select * from banner order by sort asc</script>"})
	@ResultMap(value= {"result"})
	public List<Banner> findBySort();
	
	@Update(value="update banner as a left join image as b "
			+ "on a.img_id=b.id set a.sort=#{sort},a.img_id =#{image.id},b.src=#{image.src} where a.id=#{id}")
	public void update(Banner banner);
	
	@Insert(value="insert into banner(id,sort,img_id) values(#{id},#{sort},#{image.id})")
	public void insert(Banner banner);
	
	@Delete(value="delete from banner where id=#{id}")
	public void delete(String id);
}
