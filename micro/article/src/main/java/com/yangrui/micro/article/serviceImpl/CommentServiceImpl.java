package com.yangrui.micro.article.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yangrui.micro.article.dao.CommentDao;
import com.yangrui.micro.article.entity.Comment;
import com.yangrui.micro.article.service.CommentService;
import com.yangrui.util.MainUUID;

public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public Comment add(Comment comment) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(comment.getId()))
			comment.setId(MainUUID.getMainId());
		commentDao.insert(comment);
		return comment;
	}

}
