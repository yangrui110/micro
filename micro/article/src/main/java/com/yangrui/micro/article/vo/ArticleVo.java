package com.yangrui.micro.article.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.yangrui.micro.image.entity.Image;

import lombok.Data;

@Data
public class ArticleVo {
	private String id;
	private String title;
	private String summary;
	private Image icon;
	private String content;
	private String[] labels;//label数组，用,分开 
	private Integer readNum;
	private String author;
	@JSONField(serialize=false)
	private Date createTime;
	private Date updateTime;
	private Integer comment;
	private Integer sticky; //是否是置顶文章
}
