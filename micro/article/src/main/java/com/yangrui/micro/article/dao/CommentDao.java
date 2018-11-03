package com.yangrui.micro.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.yangrui.micro.article.entity.Comment;

@Mapper
public interface CommentDao {

	@Insert(value="insert into comment(id,img_id,nickName,content) values(#{id},#{icon.id},#{nickName},#{content})")
	public void insert(Comment comment);
	@Update(value="update comment set img_id=#{icon.id},nickName=#{nickName},content=#{content} where id=#{id}")
	public void update(Comment comment);
	@Select(value="select * from comment where article_id=#{articleId}")
	@Results(id="comment",value= {
			@Result(column="id",property="id"),
			@Result(column="nick_name",property="nickName"),
			@Result(column="content",property="content"),
			@Result(column="img_id",property="icon",javaType=com.yangrui.micro.image.entity.Image.class, jdbcType=JdbcType.VARCHAR,one=@One(select="com.yangrui.micro.image.dao.ImageDao.findById"))
	})
	public List<Comment> findByArticleId(String articleId);
	
	@Select(value="select count(*) from comment where article_id= #{articleId}")
	public int countArticleComments(String articleId);
	@Delete(value="delete from comment where id=#{id}")
	public void delete(String id);
}
