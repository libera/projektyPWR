package com.project.form;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.data.Prowadzacy;
import com.project.service.LoginService;
import com.project.Utils.Encryption;
import com.project.Utils.RandomPassword;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	
	
	@RequestMapping("/")
	public String przelacz() {
		return "*/index";
	}
	
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody Integer addProwadzacy(@RequestParam(value="firstname", required=true)String imie,
    @RequestParam(value="surname", required=true)String nazwisko, 
    @RequestParam(value="email", required=true)String mail,
    @RequestParam(value="user", required=true)String login, Model model) {
    	
    	Date data = new Date();
    	Prowadzacy prowadzacy = new Prowadzacy();
    	prowadzacy.setImiona(imie);
    	prowadzacy.setNazwisko(nazwisko);
    	prowadzacy.setEmail(mail);
    	prowadzacy.setLogin(login);
    	prowadzacy.setDataDodania(data);
    	prowadzacy.setWaznosc(true);
    	prowadzacy.setAktywowany(false);
    	String haslo=RandomPassword.Random();
    	prowadzacy.setHaslo(Encryption.encrypt(haslo));
    	loginService.addProwadzacy(prowadzacy);
    	
    	List<Prowadzacy> registerlist = loginService.validateRegister(imie, nazwisko, mail, login);
    			if(registerlist.size()>0)
        		{
        			return 1;
        		}
        		else {
        			
        			return 0;
        		}
    	
    }

    
	@RequestMapping(value= "/login",  method = RequestMethod.POST)
    public Integer LoginAuthentication(HttpServletRequest req, @RequestParam(value="user", required=true)String login,
    		@RequestParam(value="pass", required=true)String haslo, Model model){
		
    		List<Prowadzacy> loginlist=loginService.validateLogin(login, haslo);
    
    		if(loginlist.size()>0)
    		{
        		boolean czyAktywowany=loginlist.get(0).isAktywowany();

    			if(czyAktywowany == false){
        			loginService.aktywacja(login, true);
    			}
    			else{
    				//nic nie rob
    			}
    			
    			return 1;
    		}
    		else {
    			
    			return 0;
    		}
    }
    
	/*@RequestMapping("/logout.html")
	public String Logout(HttpSession session){
	session.invalidate();
	return "login";

	}*/
}
    
 
