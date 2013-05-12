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

@Controller
public class OperationStudentsController {

	@Autowired
	private AddGroupsService addGroupsService;

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

		// json tutaj
		INJsonMeetingId inJsonMeetingId = new INJsonMeetingId();
		// ----------

		// Tutaj mamy zabawe oczywiscie z drzewem Jsona
		// {'meetingid':'1',
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
			@RequestParam(value = "groupidzaj", required = true) int idZaj,
			Model model) {
		
		// pkt. 1
		addGroupsService.addStudents(idStudent, idGrupy, "brak", "2.0", " ");
		
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
		int iloscSpotkan = getMeeting.size();
		
		
		INStudentJsonMark inJsonStudentMark = new INStudentJsonMark();
		
		List<INStudentJsonMarksAndPresence> tmp1 = new ArrayList<INStudentJsonMarksAndPresence>();
		for(int i = 0; i<iloscSpotkan; i++){
			
			INStudentJsonMarksAndPresence instudent = new INStudentJsonMarksAndPresence();
			
			List<Obecnosc> obec = addGroupsService.getObecnosc(getStudProj.get(i).getIdStudenta().getIdStudenci(), getMeeting.get(i).getIdSpotkania());
			List<OcenyCzastkowe> ocenki = addGroupsService.getOcenki(getStudProj.get(i).getIdStudenta().getIdStudenci(), getMeeting.get(i).getIdSpotkania());
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
