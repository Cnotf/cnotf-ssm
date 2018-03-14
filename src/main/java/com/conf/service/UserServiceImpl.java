package com.conf.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.conf.dao.UserMapper;
import com.conf.model.User;

@Service
public class UserServiceImpl implements IUserService{

	@Resource
	private UserMapper usermapper;
	
	public User getUser (Integer id) {
		return usermapper.selectByPrimaryKey(id);
	}
}
