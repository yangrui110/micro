package com.yangrui.micro.article.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yangrui.micro.article.dao.LabelDao;
import com.yangrui.micro.article.entity.Label;
import com.yangrui.micro.article.service.LabelService;
import com.yangrui.util.MainUUID;

@Service
public class LabelServiceImpl implements LabelService{

	@Autowired
	private LabelDao labelDao;
	
	@Override
	public Label insert(Label label) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(label.getId())) {
			String id=MainUUID.getMainId();
			label.setId(id);
			labelDao.insert(label);
		}
		return null;
	}

	@Override
	public List<Label> findAll() {
		// TODO Auto-generated method stub
		return labelDao.findAll();
	}

}
