package com.yangrui.micro.article.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangrui.micro.article.dao.ArticleLabelDao;
import com.yangrui.micro.article.entity.Article;
import com.yangrui.micro.article.entity.ArticleLabel;
import com.yangrui.micro.article.entity.Label;
import com.yangrui.micro.article.service.ArticleLabelService;
import com.yangrui.util.MainUUID;

@Service
public class ArticleLabelServiceImpl implements ArticleLabelService {

	@Autowired
	private ArticleLabelDao articleLabelDao;
	
	@Override
	public ArticleLabel keepRelation(Article article, Label label) {
		// TODO Auto-generated method stub
		ArticleLabel articleLabel=new ArticleLabel();
		articleLabel.setArticleId(article.getId());
		articleLabel.setLabelId(label.getId());
		articleLabel.setId(MainUUID.getMainId());
		articleLabelDao.insert(articleLabel);
		return articleLabel;
	}

	@Override
	public void deleteByArticleId(String articleId) {
		// TODO Auto-generated method stub
		articleLabelDao.delete(articleId);
	}

}
