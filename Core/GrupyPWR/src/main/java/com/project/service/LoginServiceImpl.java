package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.LoginDAO;
import com.project.data.Prowadzacy;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;
	
	@Transactional
    public void addProwadzacy(Prowadzacy prowadzacy){
		loginDAO.addProwadzacy(prowadzacy);
    	
    }
	@Transactional
    public  void aktywacja(String login, boolean aktywowany) {
		loginDAO.aktywacja(login, aktywowany);
	}

	
	@Transactional
    public List<Prowadzacy> listProwadzacy() {
    	return loginDAO.listProwadzacy();
    }
	
	@Transactional
	public List<Prowadzacy> validateLogin(String login, String haslo) {
		return loginDAO.validateLogin(login, haslo);
	}
	@Transactional
    public List<Prowadzacy> validateRegister(String imiona, String nazwisko, String email, String login) {
		return loginDAO.validateRegister(imiona, nazwisko, email, login);
	}
	

    
}
