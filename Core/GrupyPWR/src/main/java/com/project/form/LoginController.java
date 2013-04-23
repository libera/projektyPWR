package com.project.form;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
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

import com.project.Json.*;

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
		String subject = "Przes³anie has³a do logowania!";
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
		System.out.println("HasÅ‚o: " + haslo + " Login: " + login);
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

	@RequestMapping(value = "/getcurses", method = RequestMethod.POST)
	public @ResponseBody
	JsonKursy wyslijKursy(
			@RequestParam(value = "userid", required = true) int login,
			Model model) {

		JsonKursy jsonkursy = new JsonKursy();
		JsonGrupy jsongrupy = new JsonGrupy();
		JsonGrupyZajeciowe jsonzjecia = new JsonGrupyZajeciowe();
		List<JsonGrupy> courses = new ArrayList<JsonGrupy>();
		/*
		for (KursVOIF kurs : kursy) {
			List<JsonGrupyZajeciowe> name = new ArrayList<JsonGrupyZajeciowe>();
			List<GrupaVOIF> grupy = service.getGrupy();

			for (GrupaVOIF grupa : grupy) {
				// set dla JsonGrupyZajeciowe
				jsonzjecia.setCode(code);
				jsonzjecia.setId(id);
				jsonzjecia.setName(name);
			}
			// jsongrupy.s
			// jsonGrupy.setList(name);
			// set dla JsonGrupy
			jsongrupy.setId(id);
			jsongrupy.setName(name);
			jsongrupy.setDates(dates);

		}
		jsonkursy.setCourses(courses);
		*/
		return jsonkursy;
		// courses.add();
		// curses.ad
		/*
		 * for(Object o : courses) { String element = (String) o; for(Object p :
		 * name) { String pelement = (String) p; } }
		 */
		// jsonkursy.setCourses(courses);

		// taki ma byc generwany response do klienta
		// Jeszcze nalezy dodac, ¿e sprawdzamy w tabeli grupy_zajeciowe czy
		// przeslany postem
		// id_ usera jest taki jaki chcemy wyœwietliæ przyporz¹dkowane do niego
		// grupy(przesylane POSTEM)
		/*
		 * Czyli tutaj korzystamy z encji Kursy która wyswietla nam(teraz
		 * wypisuje wszystkie pola z kursów): - nazwa_kursu - kod_kursu
		 * Nastêpnie w danych kursach mamy wiêcej grup i teraz robi¹ siê checa(
		 * czyli w tym momencie wyswietlamy zawartoœæ encji grupy_zjeciowe,
		 * odpowiednio: - idGrupy_zajeciowej - Kod_grupy - Termin
		 */

		/*
		 * "{'courses': [ {'name':'Informatyka w gospodarce', 'id':'1', 'dates':
		 * [ {'id':'1', 'code':'bla1', 'name':'PN 13.15'}, {'id':'2',
		 * 'code':'bla2', 'name':'WT 11.15'}, {'id':'3', 'code':'bla3',
		 * 'name':'PT 19.55'}, ] }, {'name':'In¿ynieria oprogramowania',
		 * 'id':'2', 'dates': [ {'id':'4', 'code':'bla4', 'name':'PN 13.15'},
		 * {'id':'5', 'code':'bla5', 'name':'WT 11.15'}, {'id':'6',
		 * 'code':'bla6', 'name':'PT 19.55'}, ] } ] }"
		 */

	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody
	int logout(@RequestParam(value = "userid", required = true) int login,
			Model model) {
		List<Prowadzacy> loglist = loginService.logout(login);
		if (loglist.size() > 0) {
			return 1;
		}
		return 0;
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public @ResponseBody
	int zmien_haslo(
			@RequestParam(value = "userid", required = true) int login,
			@RequestParam(value = "oldpass", required = true) String stareHaslo,
			@RequestParam(value = "newpass", required = true) String noweHaslo,
			Model model) {
		List<Prowadzacy> passlist = loginService
				.validatePass(login, stareHaslo);
		String zmienHaslo;
		if (passlist.size() > 0) {
			zmienHaslo = passlist.get(0).getHaslo();
			if (zmienHaslo == stareHaslo) {
				loginService.zmienPass(login, noweHaslo);
				return 1;
			}
		}
		return 0;
	}

	@RequestMapping(value = "/importcsv", method = RequestMethod.POST)
	public @ResponseBody
	String upload(
			@RequestParam(value = "filecontent", required = false) CommonsMultipartFile file,
			@RequestParam(value = "userid", required = true) int login,
			Model model) /* throws Exception */{

		InputStream is = null;
		StringBuilder sb = new StringBuilder();
		try {
			is = file.getFileItem().getInputStream();
			Reader reader = new InputStreamReader(is);
			int data = reader.read();
			while (data != -1) {
				char theChar = (char) data;
				sb.append(theChar);
				data = reader.read();
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(file.getFileItem().getFieldName());

		if (file.isEmpty()) {
			return "0";
		} else {
			importcsv.do_import(sb.toString(), login);
			return "1";
		}
	}

	// public void do_import(File plik) {}
}
