package com.yangrui.micro.article.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yangrui.micro.article.service.LabelService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangrui.util.ResultEntity;
import com.yangrui.util.ResultEnum;

@Controller
@RequestMapping("label")
public class LabelController {

	@Autowired
	private LabelService labelService;
	
	@ResponseBody
	@GetMapping("findAll")
	public ResultEntity findAll() {
		
		return new ResultEntity(ResultEnum.OK,labelService.findAll());
	}
	
}
