package com.yangrui.micro.banner.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	@DeleteMapping("deleteById")
	public ResultEntity delete(@RequestParam("id")String id) {
		bannerService.delete(id);
		return new ResultEntity(ResultEnum.OK, null);
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
	
	@ResponseBody
	@DeleteMapping("deleteSelect")
	public ResultEntity deleteSelect(@RequestBody List<Banner> lists) {
		bannerService.deleteSelect(lists);
		return new ResultEntity(ResultEnum.OK, new ModelMap("result", true));
	}
	
	@GetMapping("bannerEditor")
	public ModelAndView eitor(@RequestParam(value="id",required=false,defaultValue="")String id) {
		Map<String, Banner> map=new HashMap<>();
		Banner banner=null;
		if(!StringUtils.isEmpty(id))
			banner=bannerService.findById(id);
		else {
			banner=new Banner();
		}
		map.put("banner", banner);
		return new ModelAndView("bannerEditor",map);
	}
	@GetMapping("bannerList")
	public ModelAndView bannerList() {
		
		return new ModelAndView("bannerList");
	}
}
