package com.project.dao;

import java.util.List;

import com.project.data.Prowadzacy;



public interface LoginDao {

	public List<Prowadzacy> validateLogin(String user, String password);
	public void saveUser(Prowadzacy user);
}

