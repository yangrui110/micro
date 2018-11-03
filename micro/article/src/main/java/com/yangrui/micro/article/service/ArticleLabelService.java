package com.yangrui.micro.article.service;

import com.yangrui.micro.article.entity.Article;
import com.yangrui.micro.article.entity.ArticleLabel;
import com.yangrui.micro.article.entity.Label;

public interface ArticleLabelService {

	public ArticleLabel keepRelation(Article article,Label label);
	
	public void deleteByArticleId(String articleId);
}
