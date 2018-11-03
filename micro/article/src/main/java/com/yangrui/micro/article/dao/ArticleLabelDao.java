package com.yangrui.micro.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yangrui.micro.article.entity.Article;
import com.yangrui.micro.article.entity.ArticleLabel;
import com.yangrui.micro.article.entity.Label;

@Mapper
public interface ArticleLabelDao {

	@Select(value="select b.*  from article_label as a,label as b where a.article_id=#{articleId} and b.id=a.label_id")
	public List<Label> findLabelByArticleId(String articleId);
	
	@Select(value="select * from article_label as a ,article as b where a.label_id=#{labelId} and b.id = a.article_id")
	public List<Article> findArticleByLabelId(String labelId);
	
	@Insert(value="insert into article_label(id,article_id,label_id) values(#{id},#{article_id},#{label_id})")
	public void insert(ArticleLabel articleLabel);
	
	@Select(value="select * from article_label where article_id=#{articleId} and label_id=#{label_id}")
	public ArticleLabel findByArticleAndLabel(@Param("article_id")String articleId,@Param("label_id")String labelId);
	
	@Delete(value="delete from article_label where article_id=#{articleId}")
	public void delete(@Param("articleId")String articleId);
	
}
