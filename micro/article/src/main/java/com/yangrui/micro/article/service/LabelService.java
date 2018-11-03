package com.yangrui.micro.article.service;

import java.util.List;

import com.yangrui.micro.article.entity.Label;

public interface LabelService {

	public Label insert(Label label);
	
	public List<Label> findAll();
}
