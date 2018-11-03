package com.yangrui.micro.basic.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yangrui.util.MainUUID;
import com.yangrui.util.ResultEntity;
import com.yangrui.util.ResultEnum;
import com.yangrui.util.file.PathUtil;

@Controller
public class FileController {

	@ResponseBody
	@PostMapping("upload")
	public ResultEntity uploadFile(@RequestParam("file")CommonsMultipartFile file) throws Exception {
		
		String originName=file.getOriginalFilename();
		String fileName=ensureFile(originName);
		
		BufferedInputStream inputStream=new BufferedInputStream(file.getInputStream());
		BufferedOutputStream  outputStream=new BufferedOutputStream(new FileOutputStream(new File(PathUtil.systemPath()+fileName)));
		byte[] bys=new byte[1024];
		int len=-1;
		while((len=inputStream.read(bys))!=-1) {
			outputStream.write(bys,0,len);
		}
		outputStream.close();
		inputStream.close();
		Map<String,Object> map=new HashMap<>();
		map.put("path", "/upload/"+fileName);
		return new ResultEntity(ResultEnum.OK,map);
	}
	
	/**保证文件目录以及文件存在
	 * @throws Exception */
	private String ensureFile(String originName) throws Exception {
		int a=originName.lastIndexOf(".");
		String sufix=originName.substring(a);
		sufix=MainUUID.getMainId()+sufix;
		String basePath=PathUtil.systemPath();
		File base=new File(basePath);
		if(!base.exists())
			base.mkdirs();
		String fileName=basePath+sufix;
		File files=new File(fileName);
		if(!files.exists())
			files.createNewFile();
		return sufix;
	}
}
