package com.project.service;

import java.util.List;

import com.project.dao.LoginDao;
import com.project.data.Prowadzacy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public List<Prowadzacy> validateLogin(String user,String password){
		return loginDao.validateLogin(user, password);
	}
	
	@Override
	public void saveUser(Prowadzacy user) {
		loginDao.saveUser(user);
	}

}
