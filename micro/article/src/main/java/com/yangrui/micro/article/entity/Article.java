package com.yangrui.micro.article.entity;

import java.util.Date;

import com.yangrui.micro.image.entity.Image;

import lombok.Data;

@Data
public class Article {

	private String id;
	private String title;
	private String summary;
	private Image icon;
	private String content;
	private String labels;//label数组，用,分开 
	private String author;
	//@JSONField(serialize=false)
	private Date createTime;
	private Date updateTime;
	private Integer comment;
	
	private Integer sticky; //是否是置顶文章,0表示是普通文章，1表示是置顶文章，2表示是系统公告,3是说说类型
}
