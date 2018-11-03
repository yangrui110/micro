package com.yangrui.micro.article.convert;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.yangrui.micro.article.entity.Article;
import com.yangrui.micro.article.vo.ArticleVo;

public class ArticleConvert {

	
	public static List<ArticleVo> convertArticleList(List<Article> lists){
		List<ArticleVo> vos=new ArrayList<ArticleVo>();
		for (Article article : lists) {
			vos.add(convertArticleOne(article));
		}
		return vos;
	}
	
	public static ArticleVo convertArticleOne(Article article) {
		ArticleVo articleVo=new ArticleVo();
		BeanUtils.copyProperties(article, articleVo);
		String labels=article.getLabels();
		String res[]=labels.split(",");
		articleVo.setLabels(res);
		if(articleVo.getComment()==null)
			articleVo.setComment(0);
		return articleVo;
	}
	
	public static List<Article> convertArticleVoList(List<ArticleVo> lists){
		List<Article> articles=new ArrayList<>();
		for (ArticleVo articleVo : lists) {
			articles.add(convertArticleVoOne(articleVo));
		}
		return articles;
	}
	
	public static Article convertArticleVoOne(ArticleVo articleVo) {
		Article article=new Article();
		BeanUtils.copyProperties(articleVo, article);
		String[] label=articleVo.getLabels();
		StringBuilder builder=new StringBuilder();
		for (String string : label) {
			builder.append(string);
		}
		article.setLabels(builder.toString());
		return article;
	}
}