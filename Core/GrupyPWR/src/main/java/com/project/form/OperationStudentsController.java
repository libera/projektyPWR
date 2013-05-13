package com.project.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.GroupJson.INJsonMarksAndPresence;
import com.project.GroupJson.INJsonMeetingId;
import com.project.GroupJson.INStudentJsonMark;
import com.project.GroupJson.INStudentJsonMarksAndPresence;
import com.project.data.GrupyProjektowe;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Prowadzacy;
import com.project.data.Spotkania;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.service.AddGroupsService;
import com.project.service.PobierzGrupyService;
import com.project.service.PobranieGrupZajService;

@Controller
public class OperationStudentsController {

	@Autowired
	private AddGroupsService addGroupsService;
	@Autowired
	private PobranieGrupZajService pobierzGrupyZajService;

	// Usuń grupę
	// projektową
	@RequestMapping(value = "/removegroup", method = RequestMethod.POST)
	public @ResponseBody
	Integer usunGrupeProj(
			@RequestParam(value = "groupid", required = true) int idGrupy,
			Model model) {

		return 0;
	}

	// Zmień grupe
	// projektowa
	@RequestMapping(value = "/editgroup", method = RequestMethod.POST)
	public @ResponseBody
	Integer zmienGrupeProj(
			@RequestParam(value = "groupid", required = true) int idGrupy,
			@RequestParam(value = "name", required = true) String nazwa,
			@RequestParam(value = "subject", required = true) String przedmiot,
			@RequestParam(value = "repo", required = true) String repo,
			@RequestParam(value = "comment", required = true) String komentarz,
			Model model) {
		return 0;
	}

	// Dodaj spotkanie
	@RequestMapping(value = "/addmeeting", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	INJsonMeetingId addMeeting(
			@RequestParam(value = "groupid", required = true) int idGrupy,
			Model model) {
		INJsonMeetingId inJsonMeetingId = new INJsonMeetingId();
		Date date = new Date();
		//pkt. 1
		addGroupsService.addSpotkania(idGrupy, date, date.toString(), 0);
		//pkt. 2
		List<Spotkania> spotkaniabyid = addGroupsService.getSpotByGroupId(idGrupy);
		int rozmiarSpotk = 0;
		
		rozmiarSpotk = spotkaniabyid.size()-1;
		
		 Spotkania lastSpotkania =  spotkaniabyid.get(rozmiarSpotk);
		 int idSpotkania = lastSpotkania.getIdSpotkania();
		 
		 
		
		//pkt. 3
		 List<StudenciDoGrupProjektowych> studencidogrup = pobierzGrupyZajService.pobierzStudentowZgrupy(idGrupy);
		 inJsonMeetingId.setDate(lastSpotkania.getDataSpotkania());
		 inJsonMeetingId.setMeetingid(lastSpotkania.getIdSpotkania());
		 
		 List<INJsonMarksAndPresence>marksandpresence = new ArrayList<INJsonMarksAndPresence>();
		 
		 for(StudenciDoGrupProjektowych sdg : studencidogrup) {
			 INJsonMarksAndPresence jsonTMP = new INJsonMarksAndPresence();
			 
			 List<Obecnosc> obec = pobierzGrupyZajService.pobierzObecnosci(idSpotkania,sdg.getIdStudenta().getIdStudenci());
			 List<OcenyCzastkowe> ocenki = pobierzGrupyZajService.pobierzOcenyCzastkowe(idSpotkania,sdg.getIdStudenta().getIdStudenci());  
			
			 jsonTMP.setStudentid(sdg.getIdStudenta().getIdStudenci());
			 jsonTMP.setMarkid(ocenki.get(0).getIdOcenyCzastkowe());
			 jsonTMP.setPresenceid(obec.get(0).getIdObecnosc());
			 
			 marksandpresence.add(jsonTMP);
		 }
		 
		 inJsonMeetingId.setMarksandpresence(marksandpresence);
		 
		//pkt. 4 
		// json tutaj
		
		// ----------

		// Tutaj mamy zabawe oczywiscie z drzewem Jsona
		// {'meetingid':'1',
		//  'date':'data',
		// 'marksandpresence':
		// [{'studentid':'1', 'presenceid':'3',markid: '3'}]
		// }
		return inJsonMeetingId;
	}

	// Dodanie studenta
	// z nieprzypisanych
	// do grupy projektowej
	@RequestMapping(value = "/addstudentgroup", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	INStudentJsonMark addStudentGroup(
			@RequestParam(value = "studentid", required = true) int idStudent,
			@RequestParam(value = "groupid", required = true) int idGrupy,
			@RequestParam(value = "courseid", required = true) int idZaj,
			Model model) {
		
		// pkt. 1
		addGroupsService.addStudents(idStudent, idGrupy, "brak", " ", " ");
		
		// pkt. 2
		List<GrupyProjektowe> listgrupzaj = addGroupsService.getIdGrupZaj(idGrupy);
		
		int idGZajeciowe=listgrupzaj.get(0).getIdGrupyZajeciowe().getIdGrupyZajeciowe();
		//System.out.println(idGZajeciowe);
		// pkt. 3
		//w tym miejscu brak idGrupyZajeciowej danego studenta
		
		
		
		
		//---------------------->W tym miejscu recznie wprowadzamy idGrupy Zajeciowej prosze uważać<----------------------
		//idZaj = 3;
		//----------------------------------------------------------------------------------------------------------------
		addGroupsService.updateStudZaj(idStudent, idZaj, idGZajeciowe);
		
		// pkt 4
		List <Spotkania> getMeeting = addGroupsService.getSpotkaniaByGroup(idGrupy);
		List <StudenciDoGrupProjektowych> getStudProj = addGroupsService.getStudent(idStudent);
		
		
		// pkt 5
		int iloscSpotkan = getMeeting.size()-1;
		
		
		INStudentJsonMark inJsonStudentMark = new INStudentJsonMark();
		
		List<INStudentJsonMarksAndPresence> tmp1 = new ArrayList<INStudentJsonMarksAndPresence>();
		for(int i = 0; i<=iloscSpotkan; i++){
			
			INStudentJsonMarksAndPresence instudent = new INStudentJsonMarksAndPresence();
			
			List<Obecnosc> obec = addGroupsService.getObecnosc(getStudProj.get(0).getIdStudenta().getIdStudenci(), getMeeting.get(i).getIdSpotkania());
			List<OcenyCzastkowe> ocenki = addGroupsService.getOcenki(getStudProj.get(0).getIdStudenta().getIdStudenci(), getMeeting.get(i).getIdSpotkania());
			instudent.setMeetingid(getMeeting.get(i).getIdSpotkania());
			instudent.setPresenceid(obec.get(0).getIdObecnosc());
			instudent.setMarkid(ocenki.get(0).getIdOcenyCzastkowe());
			tmp1.add(instudent);
		}
		inJsonStudentMark.setMarksandpresence(tmp1);
		System.out.println("Test wydruk"+ inJsonStudentMark);
		return inJsonStudentMark;
	}

	// Usuwanie studenta
	// z grupy
	// projektowej
	@RequestMapping(value = "/removestudentgroup", method = RequestMethod.POST)
	public @ResponseBody
	Integer removeStudentGroup(
			@RequestParam(value = "studentid", required = true) int idStudent,
			Model model) {
		return 0;
	}

	// Zmiana obecnosci
	// na danym
	// spotkaniu
	@RequestMapping(value = "/setpresence", method = RequestMethod.POST)
	public @ResponseBody
	Integer setPresence(
			@RequestParam(value = "presenceid", required = true) int idObecnosc,
			@RequestParam(value = "present", required = true) boolean stan) {
		return 0;
	}

	// Zmiana oceny na
	// dane spotkanie
	@RequestMapping(value = "/setmark", method = RequestMethod.POST)
	public @ResponseBody
	Integer setMark(
			@RequestParam(value = "markid", required = true) int markid,
			@RequestParam(value = "value", required = true) String wOcena) {
		return 0;
	}

	// Zmiana danych
	// spotkania
	// (uruchamiania
	// zawsze po lost
	// focus nazwy, daty
	// lub wagi oceny)
	@RequestMapping(value = "setmeeting", method = RequestMethod.POST)
	public @ResponseBody
	Integer setMeeting(
			@RequestParam(value = "meetingid", required = true) int idSpotkanie,
			@RequestParam(value = "name", required = true) String nazwa,
			@RequestParam(value = "date", required = true) Date data,
			@RequestParam(value = "weight", required = true) int waga) {
		return 0;
	}
}
