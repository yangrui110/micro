package com.yangrui.micro.basic.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangrui.util.code.Valicode;

@Controller
public class VerifyCode {

	@GetMapping("verify")
	@ResponseBody
	public void code(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
        
		String code=Valicode.generateVerifyCode(4);
		request.getSession().setAttribute("vcode", code);
		int w=140,h=40;
		Valicode.outputImage(w, h, response.getOutputStream(), code);
	}
}
