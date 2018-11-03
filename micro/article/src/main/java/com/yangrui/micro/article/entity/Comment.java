package com.yangrui.micro.article.entity;

import java.util.Date;

import com.yangrui.micro.image.entity.Image;

import lombok.Data;

@Data
public class Comment {

	private String id;
	/**ͷ��*/
	private Image icon;
	private String nickName;
	private String content;
	private String articleId;
	private Date createTime;
}
