package com.yangrui.micro.banner.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangrui.micro.banner.entity.Banner;
import com.yangrui.micro.banner.service.BannerService;
import com.yangrui.util.BaseException;
import com.yangrui.util.ExceptionEntity;
import com.yangrui.util.ResultEntity;
import com.yangrui.util.ResultEnum;

@Controller
@RequestMapping("banner")
public class BannerController {

	@Autowired
	private BannerService bannerService;
	
	@ResponseBody
	@GetMapping("findAll")
	public ResultEntity findAll() {
		List<Banner> lists=bannerService.findAll();
		return new ResultEntity(ResultEnum.OK,lists,lists.size());
	}
	@ResponseBody
	@GetMapping("findById")
	public ResultEntity getOne(@RequestParam("id")String id) {
		Banner banner=bannerService.findById(id);
		return new ResultEntity(ResultEnum.OK,banner,1);
	}
	@ResponseBody
	@PostMapping("save")
	public ResultEntity save(@RequestBody @Validated Banner banner,BindingResult result) {
		if(result.hasErrors()) {
			ExceptionEntity entity=new ExceptionEntity(ResultEnum.ERROR);
			entity.setMsg(result.getFieldError().getDefaultMessage());
			throw new BaseException(entity);
		}
		if(StringUtils.isEmpty(banner.getId()))
			bannerService.insert(banner);
		else bannerService.update(banner);
		Map<String, Boolean> map=new HashMap<>();
		map.put("result", true);
		return new ResultEntity(ResultEnum.OK,map);
	}
}
