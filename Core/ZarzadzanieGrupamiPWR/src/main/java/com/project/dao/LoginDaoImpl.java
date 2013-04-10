package com.project.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.data.Prowadzacy;


@Repository("loginDao")
public class LoginDaoImpl implements LoginDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional(readOnly = true)
	public List<Prowadzacy> validateLogin(String user, String password) {
	return sessionFactory.getCurrentSession().createQuery(
	"from Prowadzacy where login=:user and haslo=:password")
	.setString("user", user).setString("password",password).list();
	}
	
	@Override
	@Transactional
	public void saveUser(Prowadzacy user) {
	sessionFactory.getCurrentSession().save(user);
	}

}