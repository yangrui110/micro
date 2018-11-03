package com.yangrui.micro.image.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yangrui.micro.image.dao.ImageDao;
import com.yangrui.micro.image.entity.Image;
import com.yangrui.micro.image.service.ImageService;
import com.yangrui.util.MainUUID;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	private ImageDao imageDao;
	
	@Override
	public Image findById(String id) {
		// TODO Auto-generated method stub
		return imageDao.findById(id);
	}

	@Override
	public Image insert(Image image) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(image.getId()))
			image.setId(MainUUID.getMainId());
		imageDao.insert(image);
		return image;
	}

	@Override
	public Image update(Image image) {
		// TODO Auto-generated method stub
		return image;
	}

}
