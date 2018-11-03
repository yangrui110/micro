package com.yangrui.micro.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yangrui.micro.article.entity.Article;
import com.yangrui.micro.article.service.ArticleService;
import com.yangrui.micro.article.vo.ArticleVo;
import com.yangrui.util.ResultEntity;
import com.yangrui.util.ResultEnum;

@RestController
@RequestMapping("article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@ResponseBody
	@PostMapping("save")
	public ResultEntity insert(@RequestBody Article article) {
		if(StringUtils.isEmpty(article.getId()))
			articleService.insert(article);
		else articleService.update(article);
		return new ResultEntity(ResultEnum.OK, new ModelMap("result", true));
	}

	@ResponseBody
	@DeleteMapping("deleteById")
	public ResultEntity delete(@RequestParam("id")String id) {

		articleService.deleteById(id);
		return new ResultEntity(ResultEnum.OK, new ModelMap("result",true));
	}
	@ResponseBody
	@GetMapping("findAll")
	public ResultEntity findAll(@RequestParam(name="page",required=false,defaultValue="1") int page,@RequestParam(name="limit",required=false,defaultValue="10") int limit) {
		return new ResultEntity(ResultEnum.OK, articleService.findAll(page-1,limit));
	}
	
	@ResponseBody
	@GetMapping("findById")
	public ResultEntity findById(@RequestParam("id")String id) {
		return new ResultEntity(ResultEnum.OK, articleService.findById(id));
	}
	
	@ResponseBody
	@DeleteMapping("deleteSelect")
	public ResultEntity deleteSelect(@RequestBody List<Article> articles) {
		return new ResultEntity(ResultEnum.OK, new ModelMap("result",true) );
	}
	
	@GetMapping("articleEditor")
	public ModelAndView articleEditor(@RequestParam(value="id",required=false,defaultValue="")String id) {
		Map<String, ArticleVo> map=new HashMap<>();
		ArticleVo article=null;
		if(!StringUtils.isEmpty(id))
			article=articleService.findById(id);
		else {
			article=new ArticleVo();
		}
		map.put("article", article);
		return new ModelAndView("articleEditor",map);
	}
	
	@GetMapping("articleList")
	public ModelAndView articleList() {
		return new ModelAndView("articleList");
	}
}
