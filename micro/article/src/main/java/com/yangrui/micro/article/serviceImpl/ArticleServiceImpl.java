package com.yangrui.micro.article.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yangrui.micro.article.convert.ArticleConvert;
import com.yangrui.micro.article.dao.ArticleDao;
import com.yangrui.micro.article.entity.Article;
import com.yangrui.micro.article.service.ArticleService;
import com.yangrui.micro.article.vo.ArticleVo;
import com.yangrui.micro.image.entity.Image;
import com.yangrui.micro.image.service.ImageService;
import com.yangrui.util.MainUUID;

@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ImageService imageService;
	@Autowired
	private StringRedisTemplate temlates;
	
	@Transactional
	@Override
	public void insert(Article article) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(article.getId()))
			article.setId(MainUUID.getMainId());
		if(article.getIcon()!=null) {			
			Image image=imageService.insert(article.getIcon());
			article.setIcon(image);
		}
		if(article.getSticky()==null)
			article.setSticky(0);
		articleDao.insert(article);
		
	}

	@Override
	public void update(Article article) {
		// TODO Auto-generated method stub
		Article article2=articleDao.findById(article.getId());
		if(article.getContent()!=null)
			article2.setContent(article.getContent());
		if(article.getTitle()!=null)
			article2.setTitle(article.getTitle());
		if(article.getIcon()!=null)
			article2.setIcon(article.getIcon());
		if(article.getLabels()!=null) {
			article2.setLabels(article.getLabels());
		}
		articleDao.update(article2);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		articleDao.deleteById(id);
	}

	@Override
	public List<ArticleVo> findAll(int page,int size) {
		// TODO Auto-generated method stub
		
		List<ArticleVo> vos= ArticleConvert.convertArticleList(articleDao.findAll(page, size));
		for (ArticleVo articleVo : vos) {
			int readNum=getReadNum(articleVo.getId());
			articleVo.setReadNum(readNum);
		}
		return vos;
	}

	private int getReadNum(String articleId) {
		int readNum=0;
		Object store=temlates.opsForValue().get(articleId);
		if(store!=null){
			readNum=Integer.parseInt((String) store);
		}
		return readNum;
	}
	
	@Override
	public ArticleVo findById(String id) {
		// TODO Auto-generated method stub
		ArticleVo vo=ArticleConvert.convertArticleOne(articleDao.findById(id));
		vo.setReadNum(getReadNum(id));
		return vo;
	}

}
