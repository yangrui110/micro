package com.yangrui.micro.banner.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.yangrui.micro.banner.dao.BannerDao;
import com.yangrui.micro.banner.entity.Banner;
import com.yangrui.micro.banner.service.BannerService;
import com.yangrui.micro.image.service.ImageService;
import com.yangrui.util.BaseException;
import com.yangrui.util.MainUUID;

@Service
public class BannerServiceImpl implements BannerService {


	@Autowired
	private BannerDao bannerDao;
	
	@Autowired
	private ImageService imageService;
	
	@Override
	public Banner findById(String id) {
		// TODO Auto-generated method stub
		return bannerDao.findById(id);
	}

	@Override
	public void update(Banner banner) {
		// TODO Auto-generated method stub
		Banner ne=bannerDao.findById(banner.getId());
		if(ne==null) throw new BaseException(300, "[BannerServiceImpl.class]  update方法中未找到ID为"+banner.getId()+"的记录值");
		if(banner.getSort()!=null) 
			ne.setSort(banner.getSort());
		if(banner.getImage()!=null) 
			ne.setImage(banner.getImage());
		bannerDao.update(ne);
	}

	@Transactional
	@Override
	public void insert(Banner banner) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(banner.getId()))
			banner.setId(MainUUID.getMainId());
		if(banner.getImage()!=null) {
			String imgId=MainUUID.getMainId();
			banner.getImage().setId(imgId);
			imageService.insert(banner.getImage());
		}
		bannerDao.insert(banner);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		bannerDao.delete(id);
	}

	@Override
	public List<Banner> findAll() {
		// TODO Auto-generated method stub
		return bannerDao.findBySort();
	}
	@Override
	public void deleteSelect(List<Banner> lists) {
		// TODO Auto-generated method stub
		for (Banner banner : lists) {
			bannerDao.delete(banner.getId());
		}
	}
}
