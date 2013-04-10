package com.project.service;

import java.util.List;




import com.project.data.Prowadzacy;


public interface LoginService {
	public List<Prowadzacy> validateLogin(String user,String password);
	public void saveUser(Prowadzacy user);
}
