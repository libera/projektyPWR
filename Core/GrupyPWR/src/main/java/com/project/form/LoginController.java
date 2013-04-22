package com.project.form;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.project.data.Prowadzacy;
import com.project.service.LoginService;
import com.project.Utils.Encryption;
import com.project.Utils.ImportCSV;
import com.project.Utils.RandomPassword;
import com.project.Utils.SendMail;

@Controller
public class LoginController extends SendMail {

	@Autowired
	private LoginService loginService;

	@Autowired
	private HttpServletRequest context;

	@Autowired
	private ImportCSV importcsv;
	
	@RequestMapping("/")
	public String przelacz() {
		return "*/index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody
	Integer addProwadzacy(
			@RequestParam(value = "firstname", required = true) String imie,
			@RequestParam(value = "surname", required = true) String nazwisko,
			@RequestParam(value = "email", required = true) String mail,
			@RequestParam(value = "user", required = true) String login,
			Model model) {
		String from = "grupy.pwr.wroc@gmail.com";
		String subject = "Przesłanie hasła do logowania!";
		Date data = new Date();
		Prowadzacy prowadzacy = new Prowadzacy();
		prowadzacy.setImiona(imie);
		prowadzacy.setNazwisko(nazwisko);
		prowadzacy.setEmail(mail);
		prowadzacy.setLogin(login);
		prowadzacy.setDataDodania(data);
		prowadzacy.setWaznosc(true);
		prowadzacy.setAktywowany(false);
		String haslo = RandomPassword.Random();
		prowadzacy.setHaslo(Encryption.encrypt(haslo));
		loginService.addProwadzacy(prowadzacy);
		// SendMail sendMail = new SendMail();
		Wyslij_maila(mail, subject, haslo, from);
		List<Prowadzacy> registerlist = loginService.validateRegister(imie,
				nazwisko, mail, login);
		if (registerlist.size() > 0) {

			return 1;
		} else {

			return 0;
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody
	String loginAuthentication(
			@RequestParam(value = "user", required = true) String login,
			@RequestParam(value = "pass", required = true) String haslo,
			Model model) {

		List<Prowadzacy> loginlist = loginService.validateLogin(login, haslo);
		/*
		 * boolean czyAktywowany=loginlist.get(0).isAktywowany();
		 */
		// Integer idUser = loginlist.get(0).getIdProwadzacy();
		// String id_User = idUser.toString();
		System.out.println("Hasło: " + haslo + " Login: " + login);
		// System.out.println("\nCzy aktywowany: "+czyAktywowany);
		System.out.println("\nRozmiar loginlist: " + loginlist.size());

		if (loginlist.size() > 0) {
			/*
			 * if(czyAktywowany ==false) { loginService.aktywacja(login, true);
			 * }
			 */
			return loginlist.get(0).getIdProwadzacy().toString();

		} else {
			return "-1";
		}
	}

	@RequestMapping(value = "/importcsv", method = RequestMethod.POST)
	public @ResponseBody
	String upload(
			@RequestParam(value = "filecontent", required = false) CommonsMultipartFile file,
			@RequestParam(value = "userid", required = true) int login,
			Model model) /* throws IOException */{

		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		try {
			is =file.getFileItem().getInputStream();
			Reader reader = new InputStreamReader(is);
			int data = reader.read();
			while(data != -1){
			    char theChar = (char) data;
			    sb.append(theChar);
			    data = reader.read();
			}
			reader.close();  
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(file.getFileItem().getFieldName());
		
		if(file.isEmpty()){
			return "Error";
		}
		else{
			importcsv.do_import(sb.toString(), login);
			return "Success";
		}


	}

	// public void do_import(File plik) {}
}
