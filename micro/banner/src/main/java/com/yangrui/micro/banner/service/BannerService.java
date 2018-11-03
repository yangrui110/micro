package com.yangrui.micro.banner.service;

import java.util.List;

import com.yangrui.micro.banner.entity.Banner;

public interface BannerService {

	public Banner findById(String id);
	
	public void update(Banner banner);
	
	public void insert(Banner banner);
	
	public void delete(String id);
	
	public List<Banner> findAll();
	
	public void deleteSelect(List<Banner> lists);
}
