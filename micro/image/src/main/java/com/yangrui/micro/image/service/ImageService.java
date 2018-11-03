package com.yangrui.micro.image.service;

import com.yangrui.micro.image.entity.Image;

public interface ImageService {

	public Image findById(String id);
	public Image insert(Image image);
	
	public Image update(Image image);
}
