package com.yangrui.micro.basic.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yangrui.util.BaseException;
import com.yangrui.util.prePath.PrePathUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private StringRedisTemplate redis;


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String path=request.getServletPath()+","+request.getMethod();
		System.out.println("路径="+path);
		String info=request.getPathInfo()==null?request.getServletPath():request.getPathInfo();
		String[] ss= {".js",".css",".png",".jpg",".jpeg",".json",".ttf",".woff",".woff2",".html",".icon",".gif",".ico"};
		if(request.getMethod().equalsIgnoreCase("options"))
			return true;
		if(matchOther(ss, info)) {
			return true;
			}
		if(!match(info)) {
			String token=request.getHeader("token")==null?(String) request.getSession().getAttribute("token"):request.getHeader("token");
			if(StringUtils.isEmpty(token)) {
				/**先存储之前的访问路径*/
				PrePathUtil.setPath(request);
				throw new BaseException(401, "当前用户未登录，请先登录");
			}
			String rs=redis.opsForValue().get(token);
			if(StringUtils.isEmpty(rs)) {
				PrePathUtil.setPath(request);
				throw new BaseException(401, "token权限不正确，请重新登录");
			}
			PrePathUtil.setPath(request);
		}
		return true;
	}
	

	/**根据白名单匹配访问路径
	 * @throws IOException 
	 * @throws FileNotFoundException */
	private boolean match(String path) throws FileNotFoundException, IOException {
		Resource resource = new ClassPathResource("white");
		BufferedInputStream inputStream=new BufferedInputStream(new FileInputStream(resource.getFile()));
		ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		int len=-1;
		byte[] bys=new byte[1024];
		while ((len=inputStream.read(bys))!=-1) {
			outputStream.write(bys, 0, len);
		}
		String white=new String(outputStream.toByteArray());
		
		inputStream.close();
		outputStream.close();
		boolean s=white.contains(path);
		return s;
	}
	/**
	 * 匹配静态文件
	 * */
	private boolean matchOther(String[] ss,String fix) {
		fix=fix.toLowerCase();
		for (String string : ss) {
			if(fix.endsWith(string)) 
				return true;
		}
		return false;
	}

}
