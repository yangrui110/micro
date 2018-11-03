package com.yangrui.micro.basic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yangrui.micro.user.convert.UserConvert;
import com.yangrui.micro.user.entity.Account;
import com.yangrui.micro.user.service.AccountService;
import com.yangrui.micro.user.vo.AccountVo;
import com.yangrui.util.BaseException;
import com.yangrui.util.MainUUID;
import com.yangrui.util.ResultEntity;
import com.yangrui.util.ResultEnum;

@Controller
public class LoginController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	
	@ResponseBody
	@PostMapping("login")
	public ResultEntity login(HttpServletRequest request,@RequestParam("vcode")String very,
			@RequestParam("username")String name,@RequestParam("password")String password) {
		String vcode=(String) request.getSession().getAttribute("vcode");
		if(!very.equalsIgnoreCase(vcode))
			throw new BaseException(300,"验证码填写不正确，请重新填写");
		Account account=accountService.login(name, password);
		if(account!=null) {
			String token=MainUUID.getMainId();
			String userInfo=JSONObject.toJSON(account).toString();
			stringRedisTemplate.opsForValue().set(token, userInfo);
			AccountVo accountVo=UserConvert.parseToVo(account);
			accountVo.setToken(token);
			Object prePath=request.getSession().getAttribute("prePath");
			if(prePath!=null)
				accountVo.setPrePath((String)prePath);
			return new ResultEntity(ResultEnum.OK, accountVo);
		}else throw new BaseException(300,"用户名或者密码填写错误");
	}
	
}
