package com.tut.pow;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import java.util.Date; 
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import com.project.Utils.Encryption;
import com.project.service.LoginService;
import com.project.data.Prowadzacy;

@Controller
public class LoginController {
 
	@Autowired
	LoginService loginService;
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
 
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = user.getUsername();
	
		model.addAttribute("username", name);
		model.addAttribute("message", "Spring Security login + database example");
		return "hello";
 
	}
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(@RequestParam("user") String user, ModelMap model) {
		return "login";
 
	}
	
	
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login";
 
	}
	
	/*@RequestMapping("logout.html")
	public String Logout(HttpSession session){
	session.invalidate();
	return "login";

	}*/
	
	/*@RequestMapping("login.html")
	public String LoginAuthentication(HttpServletRequest req){
	String username=req.getParameter("user");
	String password=req.getParameter("pass");
	List<Prowadzacy> loginlist=loginService.validateLogin(username, Encryption.encrypt(password));
	
	return "login";
	
	}*/
	
	@RequestMapping("registrationform.html")
	public String registrationform() {
		return "registration";
	}
	
	@RequestMapping("registration.html")
	public String registration(HttpServletRequest req){
		String imiona=req.getParameter("imiona");
		String nazwisko=req.getParameter("nazwisko");
		String email=req.getParameter("Email");
		String username=req.getParameter("UserName");
		String password=req.getParameter("Password");
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		Prowadzacy prow = new Prowadzacy();
		prow.setImiona(imiona);
		prow.setNazwisko(nazwisko);
		prow.setEmail(email);
		prow.setLogin(username);
		prow.setHaslo(Encryption.encrypt(password));
		prow.setWaznosc(true);
		prow.setDataDodania(date);
		prow.setAktywowany(false);		
		loginService.saveUser(prow);

		return "login";
	}
	
	
}