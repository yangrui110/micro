package com.yangrui.micro.banner.entity;


import com.yangrui.micro.image.entity.Image;

import lombok.Data;

@Data
public class Banner {

	private String id;
	private Integer sort;
	private Image image;
}
