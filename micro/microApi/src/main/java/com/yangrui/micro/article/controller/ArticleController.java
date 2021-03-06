package com.yangrui.micro.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yangrui.micro.article.service.ArticleService;
import com.yangrui.util.ResultEntity;
import com.yangrui.util.ResultEnum;

@RestController
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private StringRedisTemplate templates;
	
	@Autowired
	private ArticleService articleService;
	
	@ResponseBody
	@GetMapping("findAll")
	public ResultEntity findAll(@RequestParam(name="page",required=false,defaultValue="1") int page,@RequestParam(name="limit",required=false,defaultValue="10") int limit) {
		return new ResultEntity(ResultEnum.OK, articleService.findAll(page-1,limit));
	}
	
	@ResponseBody
	@GetMapping("findById")
	public ResultEntity findById(@RequestParam("id")String id) {
		//每访问一篇文章就会增加一个阅读量
		Object read=templates.opsForValue().get(id);
		if(read!=null) {
			int readNum=Integer.parseInt((String) read);
			readNum=readNum+1;
			templates.opsForValue().set(id, ""+readNum);
		}else templates.opsForValue().set(id, ""+1);
		return new ResultEntity(ResultEnum.OK, articleService.findById(id));
	}


}
