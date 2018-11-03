package com.yangrui.micro.article.service;

import java.util.List;

import com.yangrui.micro.article.entity.Article;
import com.yangrui.micro.article.vo.ArticleVo;

public interface ArticleService {

	public void insert(Article article);
	
	public void update(Article article);
	
	public void deleteById(String id);
	
	public List<ArticleVo> findAll(int page,int size);
	
	public ArticleVo findById(String id);
}