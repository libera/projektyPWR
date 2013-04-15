package com.project.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.data.Prowadzacy;

@Repository
public class LoginDAOImpl implements LoginDAO {

	 @Autowired
	    private SessionFactory sessionFactory;
	 	
	 
	 public void addProwadzacy(Prowadzacy prowadzacy) {
	        sessionFactory.getCurrentSession().save(prowadzacy);
	   
	    }
	 
	   public List<Prowadzacy> listProwadzacy() {
		   
	        return this.sessionFactory.getCurrentSession().createQuery("from Prowadzacy")
	                .list();
	    }
	   
		public List<Prowadzacy> validateLogin(String login, String haslo) {

	   return this.sessionFactory.getCurrentSession().createQuery("from Prowadzacy where login=:login and haslo=:haslo").setString("login", login).setString("haslo", haslo).list();
	   
		}
		
		public void aktywacja(String Login, boolean Aktywowany) {
			 this.sessionFactory.getCurrentSession().createQuery("UPDATE Prowadzacy SET Aktywowany=:Aktywowany WHERE Login=Login").setBoolean("Aktywowany", Aktywowany).setString("Login", Login).executeUpdate();
		}
		
		
	    public List<Prowadzacy> validateRegister(String imiona, String nazwisko, String email, String login) {
	    	return this.sessionFactory.getCurrentSession().createQuery("from Prowadzacy where imiona=:imiona and nazwisko=:nazwisko and email=:email and login=:login").setString("imiona", imiona).setString("nazwisko", nazwisko).setString("email", email).setString("login",login).list();
	    }


}
