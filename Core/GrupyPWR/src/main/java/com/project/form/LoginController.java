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

import com.project.dao.PobierzGrupyDAO;
import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;
import com.project.data.Notatki;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Prowadzacy;
import com.project.data.Spotkania;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;
import com.project.service.LoginService;
import com.project.service.PobierzGrupyService;
import com.project.service.PobranieGrupZajService;
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
	private PobierzGrupyService pobierzGrupyService;

	@Autowired
	private PobranieGrupZajService pobierzGrupyZajService;

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

		System.out.println("HasĹ‚o: " + haslo + " Login: " + login);
		System.out.println("\nRozmiar loginlist: " + loginlist.size());

		if (loginlist.size() > 0) {
			if(true == loginlist.get(0).isWaznosc())
			{
				if(false == loginlist.get(0).isAktywowany())
				{
					try{
					loginService.aktywacja(loginlist.get(0).getLogin(), true);
					}
					catch(Exception ex){
						;
					}
				}
				
				return loginlist.get(0).getIdProwadzacy().toString();
			}
			
			return "-1";

		} else {
			return "-1";
		}
	}

	@RequestMapping(value = "/getgroups", method = RequestMethod.POST, produces="application/json")
	public @ResponseBody
	JsonGroupWyzej wyslijGrupy(
			@RequestParam(value = "dateid", required = true) int idGrupyZaj,
			Model model) {

		JsonMarksAndPresence jsonmarks = new JsonMarksAndPresence();

		JsonStudents jsonstudent = new JsonStudents();
		JsonSpotkania jsonspotkania = new JsonSpotkania();
		JsonNotes jsonnotatki = new JsonNotes();

		
		JsonNieWGrupie jsonniewgrupie = new JsonNieWGrupie();

		JsonGroupZajeciowe jsonGroupZajeciowe = new JsonGroupZajeciowe();
		List<JsonGroupZajeciowe> dates = new ArrayList<JsonGroupZajeciowe>();
		JsonGroupWyzej jsonGroupWyzej = new JsonGroupWyzej();
		
		List<GrupyZajeciowe> grupzajeciowe = pobierzGrupyZajService.pobierzGrupyZajeciowe(idGrupyZaj);
		List<Integer> studenciWProjektach = new ArrayList<Integer>();
		
		jsonGroupZajeciowe.setId(grupzajeciowe.get(0).getIdGrupyZajeciowe());
		jsonGroupZajeciowe.setCode(grupzajeciowe.get(0).getKodGrupy());
		jsonGroupZajeciowe.setName(grupzajeciowe.get(0).getTermin());
		
		int idgrupyZaj = grupzajeciowe.get(0).getIdGrupyZajeciowe();
		
		List<GrupyProjektowe> grupaprojektowa = pobierzGrupyZajService.pobierzGrupyProjektowe(idGrupyZaj);
		
		List<JsonGrupyProjektowe> groupsProjArray = new ArrayList<JsonGrupyProjektowe>();
		
		for(int i=0; i<grupaprojektowa.size(); i++)
		{
			JsonGrupyProjektowe tmpProjektowaJson = new  JsonGrupyProjektowe();
			tmpProjektowaJson.setId(grupaprojektowa.get(i).getIdGrupyProjektowe());
			tmpProjektowaJson.setName(grupaprojektowa.get(i).getNazwa());
			tmpProjektowaJson.setSubject(grupaprojektowa.get(i).getTemat());
			tmpProjektowaJson.setRepo(grupaprojektowa.get(i).getResositoryLink());
			
			List<JsonStudents> students = new ArrayList<JsonStudents>();
			
			//pobieranie studentów
			List<StudenciDoGrupProjektowych> studencidogrup = pobierzGrupyZajService.pobierzStudentowZgrupy(grupaprojektowa.get(i).getIdGrupyProjektowe());
			
			//dla każdego studenta pobieram jego dane
			for(int j=0; j<studencidogrup.size(); j++ )
			{
				//zapisanie id studentów którzy są w jakiś projektach
				studenciWProjektach.add(studencidogrup.get(j).getIdStudenta().getIdStudenci());
				
				JsonStudents tmpstud = new JsonStudents();
				tmpstud.setId(studencidogrup.get(j).getIdStudenta().getIdStudenci());
				tmpstud.setFirstname(studencidogrup.get(j).getIdStudenta().getImie());
				tmpstud.setSurname(studencidogrup.get(j).getIdStudenta().getNazwisko());
				tmpstud.setIndex(studencidogrup.get(j).getIdStudenta().getNrIndeksu());
				tmpstud.setFinalmark(studencidogrup.get(j).getOcena());
				tmpstud.setPosition(studencidogrup.get(j).getStanowiskoW_Grupie());
				tmpstud.setmail(studencidogrup.get(j).getIdStudenta().getEmail());
				
				List<JsonMarksAndPresence> marksandpresence = new ArrayList<JsonMarksAndPresence>();
				
				List<Spotkania> spotkania = pobierzGrupyZajService.pobierzSpotkania(studencidogrup.get(j).getIdGrupyProjektowej().getIdGrupyProjektowe());
				
				for(int k=0; k<spotkania.size(); k++)
				{
					JsonMarksAndPresence tmpOcenaObecnos = new JsonMarksAndPresence();
					
					List<Obecnosc> obec = pobierzGrupyZajService.pobierzObecnosci(spotkania.get(k).getIdSpotkania(),studencidogrup.get(j).getIdStudenta().getIdStudenci());
					List<OcenyCzastkowe> ocenki = pobierzGrupyZajService.pobierzOcenyCzastkowe(spotkania.get(k).getIdSpotkania(),studencidogrup.get(j).getIdStudenta().getIdStudenci());  
					
					tmpOcenaObecnos.setMeetingid(spotkania.get(k).getIdSpotkania());
					if(true == obec.get(0).isStan())
					{
						tmpOcenaObecnos.setPresent(1);
					}
					else
					{
						tmpOcenaObecnos.setPresent(0);
					}
					tmpOcenaObecnos.setPresenceid(obec.get(0).getIdObecnosc());
					
					tmpOcenaObecnos.setMark(ocenki.get(0).getOcena());
					tmpOcenaObecnos.setMarkid(ocenki.get(0).getIdOcenyCzastkowe());
					
					marksandpresence.add(tmpOcenaObecnos);
				}
				
				tmpstud.setMarksandpresence(marksandpresence);
				
				students.add(tmpstud);
								
				
			}
			
			tmpProjektowaJson.setStudents(students);
			
			List<Spotkania> spotkania = pobierzGrupyZajService.pobierzSpotkania(grupaprojektowa.get(i).getIdGrupyProjektowe());
			
			List<JsonSpotkania> meetings = new ArrayList<JsonSpotkania>();
			for(int k=0; k<spotkania.size(); k++)
			{
				JsonSpotkania tmpspot = new JsonSpotkania();
				
				tmpspot.setId(spotkania.get(k).getIdSpotkania());
				tmpspot.setDate(spotkania.get(k).getDataSpotkania().toString());
				tmpspot.setName(spotkania.get(k).getNazwa());
				tmpspot.setWeight(spotkania.get(k).getWagaOceny());
				
				meetings.add(tmpspot);
			}
			
			tmpProjektowaJson.setMeetings(meetings);
			
			List<Notatki> notki = pobierzGrupyZajService.pobierzNotatki(grupaprojektowa.get(i).getIdGrupyProjektowe());
			
			List<JsonNotes> notes = new ArrayList<JsonNotes>();

			for(int k=0; k<notki.size(); k++)
			{
				JsonNotes tmpn = new JsonNotes();
				
				tmpn.setId(notki.get(k).getIdNotatki());
				tmpn.setContent(notki.get(k).getTresc());
				tmpn.setFile("Brak pliku");
				
				notes.add(tmpn);
			}
			
			tmpProjektowaJson.setNotes(notes);
			
			groupsProjArray.add(tmpProjektowaJson);
		}
		jsonGroupZajeciowe.setGroups(groupsProjArray);
		
		//NIEPRZYPISANI
		List<JsonNieWGrupie> nieprzypisani = new ArrayList<JsonNieWGrupie>();
		
		List<StudenciDoGrupZajeciowych> dogrup = pobierzGrupyZajService.pobierzStudGrup(idgrupyZaj);
		Boolean bylWProjekcie = false;   
		for(int k=0; k<dogrup.size(); k++)
		{
			bylWProjekcie=false;
			
			for(int m=0; m<studenciWProjektach.size(); m++)
			{
				if(dogrup.get(k).getIdStudenta().getIdStudenci() == studenciWProjektach.get(m))
				{
					bylWProjekcie=true;
					break;					
				}
			}
			
			if(false == bylWProjekcie)
			{
				JsonNieWGrupie tmpNG = new JsonNieWGrupie();
				
				tmpNG.setId(dogrup.get(k).getIdStudenta().getIdStudenci());
				tmpNG.setFirstname(dogrup.get(k).getIdStudenta().getImie());
				tmpNG.setSurname(dogrup.get(k).getIdStudenta().getNazwisko());
				tmpNG.setIndex(dogrup.get(k).getIdStudenta().getNrIndeksu());
				tmpNG.setMail(dogrup.get(k).getIdStudenta().getEmail());
				
				nieprzypisani.add(tmpNG);
			}
		}
		
		
		jsonGroupZajeciowe.setNotingroup(nieprzypisani);
	
		
		dates.add(jsonGroupZajeciowe);
		
		
		jsonGroupWyzej.setDates(dates);
		return jsonGroupWyzej;
		/*
		 * List<GrupyZajeciowe> grupyzajeciowe = pobierzGrupyService
		 * .pobierzGrupyZajeciowe(login);
		 */
		/*
		 * int idKursu ; int i; JsonKursy jsonKursy = new JsonKursy(); JsonGrupy
		 * jsonGrupy = new JsonGrupy(); JsonGrupyZajeciowe jsonGrupyZajeciowe =
		 * new JsonGrupyZajeciowe(); List<JsonGrupy> courses = new
		 * ArrayList<JsonGrupy>(); 
		 * for( i = 0; i<= grupyzajeciowe.size()-1;
		 * i++){ 
		 * idKursu = grupyzajeciowe.get(i).getIdKursu().getIdKursy();
		 * 
		 * List<Kursy> kurslist = pobierzGrupyService.pobierzKursy(idKursu);
		 * 
		 * for(Kursy kurs : kurslist) { List<JsonGrupyZajeciowe> name = new
		 * ArrayList<JsonGrupyZajeciowe>(); for(GrupyZajeciowe grupa :
		 * grupyzajeciowe) { // set dla JsonGrupyZajeciowe
		 * jsonGrupyZajeciowe.setId(grupa.getIdKursu().getIdKursy());
		 * jsonGrupyZajeciowe.setCode(grupa.getKodGrupy());
		 * jsonGrupyZajeciowe.setName(grupa.getNazwa()); }
		 * jsonGrupy.setName(kurs.getNazwaKursu());
		 * jsonGrupy.setId(kurs.getIdKursy()); jsonGrupy.setDates(name); }
		 * jsonKursy.setCourses(courses); }
		 * 
		 * return jsonKursy;
		 */

	}

	@RequestMapping(value = "/getcourses", method = RequestMethod.POST,  produces="application/json")
	public @ResponseBody
	JsonKursy wyslijKursy(
			@RequestParam(value = "userid", required = true) int login,
			Model model) {

		List<GrupyZajeciowe> grupyzajeciowe = pobierzGrupyService
				.pobierzGrupyZajeciowe(login);

		int i;
		List<Integer> idKursow = new ArrayList<Integer>();
		JsonKursy jsonKursy = new JsonKursy();
		
		for (i = 0; i < grupyzajeciowe.size(); i++) {
			int tmpIdKusru = grupyzajeciowe.get(i).getIdKursu().getIdKursy();
			
			if( false == idKursow.contains(tmpIdKusru))
			{
				idKursow.add(tmpIdKusru);
			}
			
		}
		List<JsonGrupy> courses2 = new ArrayList<JsonGrupy>();
		for( int j=0; j<idKursow.size(); j++)
		{
			
			List<JsonGrupyZajeciowe> name = new ArrayList<JsonGrupyZajeciowe>();
			
			
			for (i = 0; i < grupyzajeciowe.size(); i++)
			{
				
				if(idKursow.get(j) != grupyzajeciowe.get(i).getIdKursu().getIdKursy())
				{
					continue;
				}
				System.out.println(idKursow.get(j).toString() +"==?"+ grupyzajeciowe.get(i).getIdKursu().getIdKursy().toString());
				
				JsonGrupyZajeciowe jsonGrupyZajeciowe2 = new JsonGrupyZajeciowe();
				jsonGrupyZajeciowe2.setId(grupyzajeciowe.get(i).getIdGrupyZajeciowe());
				jsonGrupyZajeciowe2.setCode(grupyzajeciowe.get(i).getKodGrupy());
				jsonGrupyZajeciowe2.setName(grupyzajeciowe.get(i).getTermin());
				
				name.add(jsonGrupyZajeciowe2);
			}
			
				JsonGrupy jsonGrupy2 = new JsonGrupy();
				
				List<Kursy> kurslist = pobierzGrupyService.pobierzKursy(idKursow.get(j));
				
				jsonGrupy2.setName(kurslist.get(0).getNazwaKursu());
				jsonGrupy2.setId(kurslist.get(0).getIdKursy());
				jsonGrupy2.setDates(name);
				
				courses2.add(jsonGrupy2);
	
			
		}
		jsonKursy.setCourses(courses2);
		System.out.println("Test wydruk"+ jsonKursy);
		/*
		for (i = 0; i < grupyzajeciowe.size(); i++) {
			idKursu = grupyzajeciowe.get(i).getIdKursu().getIdKursy();

			List<Kursy> kurslist = pobierzGrupyService.pobierzKursy(idKursu);

			for (Kursy kurs : kurslist) {
				List<JsonGrupyZajeciowe> name = new ArrayList<JsonGrupyZajeciowe>();
			
				for(GrupyZajeciowe grupa : grupyzajeciowe) {				
					//  set dla JsonGrupyZajeciowe
					
					jsonGrupyZajeciowe.setId(grupa.getIdKursu().getIdKursy());
					jsonGrupyZajeciowe.setCode(grupa.getKodGrupy());
					jsonGrupyZajeciowe.setName(grupa.getTermin());
				}
				name.add(jsonGrupyZajeciowe);
				jsonGrupy.setName(kurs.getNazwaKursu());
				jsonGrupy.setId(kurs.getIdKursy());
				jsonGrupy.setDates(name);
			}
			courses.add(jsonGrupy);
			jsonKursy.setCourses(courses);
			System.out.println("Test wydruk"+ jsonKursy);
		}
*/
		return jsonKursy;
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
	
	@RequestMapping(value = "/addgroup", method = RequestMethod.POST)
	public @ResponseBody
	String dodajGrupe(@RequestParam(value = "courseid", required = true) int idGrupyZajeciowej,
			@RequestParam(value = "name", required = true)String nazwa,
			@RequestParam(value = "subject", required = true)String temat,
			@RequestParam(value = "repo", required = true)String repo,
			@RequestParam(value = "comment", required = true)String komentarz
			) {

		
		
		pobierzGrupyService.addGrupyProj(idGrupyZajeciowej, nazwa, temat, repo, komentarz);
			
		List<GrupyProjektowe> grupki = pobierzGrupyService.pobierzGrupke(idGrupyZajeciowej);
		
		/*for(int i = 0; i < grupki.size(); i++) {
			if(grupki.get(i).getNazwa() == nazwa) {
				return grupki.get(i).getIdGrupyProjektowe().toString();
			}
		}*/
		
		return grupki.get(grupki.size()-1).getIdGrupyProjektowe().toString();
	
	}

	@RequestMapping(value = "/changepass", method = RequestMethod.POST)
	public @ResponseBody
	String zmien_haslo(
			@RequestParam(value = "userid", required = true) int login,
			@RequestParam(value = "oldpass", required = true) String stareHaslo,
			@RequestParam(value = "newpass", required = true) String noweHaslo,
			Model model) {
		
		List<Prowadzacy> passlist = loginService.validatePass(login, stareHaslo);
		
		if (passlist.size() > 0) {
			loginService.zmienPass(login, noweHaslo);
				
			return "1";
		}
		return "0";
	}

	@RequestMapping(value = "/importcsv", method = RequestMethod.POST)
	public @ResponseBody
	String upload(
			@RequestParam(value = "filecontent", required = false) CommonsMultipartFile file,
			@RequestParam(value = "userid", required = true) int login,
			Model model) {

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

}
