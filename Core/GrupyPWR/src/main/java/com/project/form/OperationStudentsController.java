package com.project.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.project.data.GrupyZajeciowe;
import com.project.data.Notatki;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Prowadzacy;
import com.project.data.Spotkania;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;
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
		List<StudenciDoGrupProjektowych> lisZaj = addGroupsService
				.getStudByGroup(idGrupy);
		int idStudent = 0;
		for (StudenciDoGrupProjektowych sdg : lisZaj) {
			// int idStudent = lisZaj.get(0).getIdStudenta().getIdStudenci();
			idStudent = sdg.getIdStudenta().getIdStudenci();
			List<StudenciDoGrupZajeciowych> listStudZaj = addGroupsService
					.getStudGroupZaj(idStudent);
			int idGrupyOryg = listStudZaj.get(0).getIdGrupyOryginalnej()
					.getIdGrupyZajeciowe();
			addGroupsService.updateStudZaj(idStudent, idGrupyOryg, idGrupyOryg);
			addGroupsService.deleteStudents(idStudent);
		}
		List<GrupyProjektowe> czysagrupki = addGroupsService
				.getIdGrupZaj(idGrupy);
		System.out
				.println("Czy w bazie jest dana grupa zajeciowa(0 - nie; ~~0 -tak)"
						+ " = [" + czysagrupki.size() + "]");
		if (czysagrupki.size() > 0) {
			addGroupsService.deleteGroupProj(idGrupy);
			return 1;
		} else {
			return 0;
		}
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

		List<GrupyProjektowe> czyjestid = addGroupsService
				.getIdGrupZaj(idGrupy);
		System.out
				.println("Czy w bazie jest dana grupa zajeciowa(0 - nie; ~~0 -tak)"
						+ " = [" + czyjestid.size() + "]");
		if (czyjestid.size() > 0) {
			addGroupsService.updateGrupZaj(idGrupy, nazwa, przedmiot, repo,
					komentarz);
			return 1;
		} else {
			return 0;
		}
	}

	// Dodaj spotkanie
	@RequestMapping(value = "/addmeeting", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	INJsonMeetingId addMeeting(
			@RequestParam(value = "groupid", required = true) int idGrupy,
			Model model) {
		INJsonMeetingId inJsonMeetingId = new INJsonMeetingId();
		Date date = new Date();
		// pkt. 1
		addGroupsService.addSpotkania(idGrupy, date, date.toString(), 0);
		// pkt. 2
		List<Spotkania> spotkaniabyid = addGroupsService
				.getSpotByGroupId(idGrupy);
		int rozmiarSpotk = 0;

		rozmiarSpotk = spotkaniabyid.size() - 1;

		Spotkania lastSpotkania = spotkaniabyid.get(rozmiarSpotk);
		int idSpotkania = lastSpotkania.getIdSpotkania();

		// pkt. 3
		List<StudenciDoGrupProjektowych> studencidogrup = pobierzGrupyZajService
				.pobierzStudentowZgrupy(idGrupy);
		inJsonMeetingId.setDate(lastSpotkania.getDataSpotkania());
		inJsonMeetingId.setMeetingid(lastSpotkania.getIdSpotkania());

		List<INJsonMarksAndPresence> marksandpresence = new ArrayList<INJsonMarksAndPresence>();

		for (StudenciDoGrupProjektowych sdg : studencidogrup) {
			INJsonMarksAndPresence jsonTMP = new INJsonMarksAndPresence();

			List<Obecnosc> obec = pobierzGrupyZajService.pobierzObecnosci(
					idSpotkania, sdg.getIdStudenta().getIdStudenci());
			List<OcenyCzastkowe> ocenki = pobierzGrupyZajService
					.pobierzOcenyCzastkowe(idSpotkania, sdg.getIdStudenta()
							.getIdStudenci());

			jsonTMP.setStudentid(sdg.getIdStudenta().getIdStudenci());
			jsonTMP.setMarkid(ocenki.get(0).getIdOcenyCzastkowe());
			jsonTMP.setPresenceid(obec.get(0).getIdObecnosc());

			marksandpresence.add(jsonTMP);
		}

		inJsonMeetingId.setMarksandpresence(marksandpresence);

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

		addGroupsService.addStudents(idStudent, idGrupy, "brak", " ", " ");

		List<GrupyProjektowe> listgrupzaj = addGroupsService
				.getIdGrupZaj(idGrupy);

		int idGZajeciowe = listgrupzaj.get(0).getIdGrupyZajeciowe()
				.getIdGrupyZajeciowe();
		addGroupsService.updateStudZaj(idStudent, idZaj, idGZajeciowe);

		List<Spotkania> getMeeting = addGroupsService
				.getSpotkaniaByGroup(idGrupy);
		List<StudenciDoGrupProjektowych> getStudProj = addGroupsService
				.getStudent(idStudent);

		int iloscSpotkan = getMeeting.size() - 1;

		INStudentJsonMark inJsonStudentMark = new INStudentJsonMark();

		List<INStudentJsonMarksAndPresence> tmp1 = new ArrayList<INStudentJsonMarksAndPresence>();
		for (int i = 0; i <= iloscSpotkan; i++) {

			INStudentJsonMarksAndPresence instudent = new INStudentJsonMarksAndPresence();

			List<Obecnosc> obec = addGroupsService.getObecnosc(
					getStudProj.get(0).getIdStudenta().getIdStudenci(),
					getMeeting.get(i).getIdSpotkania());
			List<OcenyCzastkowe> ocenki = addGroupsService.getOcenki(
					getStudProj.get(0).getIdStudenta().getIdStudenci(),
					getMeeting.get(i).getIdSpotkania());
			instudent.setMeetingid(getMeeting.get(i).getIdSpotkania());
			instudent.setPresenceid(obec.get(0).getIdObecnosc());
			instudent.setMarkid(ocenki.get(0).getIdOcenyCzastkowe());
			tmp1.add(instudent);
		}
		inJsonStudentMark.setMarksandpresence(tmp1);
		// System.out.println("Test wydruk" + inJsonStudentMark);
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

		List<StudenciDoGrupZajeciowych> listStudZaj = addGroupsService
				.getStudGroupZaj(idStudent);
		int idGrupyOryg = listStudZaj.get(0).getIdGrupyOryginalnej()
				.getIdGrupyZajeciowe();
		// int idGrupyChodz =
		// listStudZaj.get(0).getIdGrupyChodzacej().getIdGrupyZajeciowe();
		addGroupsService.updateStudZaj(idStudent, idGrupyOryg, idGrupyOryg);
		addGroupsService.deleteStudents(idStudent);
		int idGrupResult = 0;
		List<StudenciDoGrupZajeciowych> listStudZaj2 = addGroupsService
				.getStudGroupZaj(idStudent);

		idGrupResult = listStudZaj2.get(0).getIdGrupyOryginalnej()
				.getIdGrupyZajeciowe();

		return idGrupResult;

	}

	// Zmiana obecnosci
	// na danym
	// spotkaniu
	@RequestMapping(value = "/setpresence", method = RequestMethod.POST)
	public @ResponseBody
	Integer setPresence(
			@RequestParam(value = "presenceid", required = true) int idObecnosc,
			@RequestParam(value = "present", required = true) boolean stan) {

		Date data_mod = new Date();
		List<Obecnosc> obec = addGroupsService.getIdObec(idObecnosc);
		if (obec.size() > 0) {
			// System.out.println("Stan obecności : " +"["+stan+"]");
			addGroupsService.updateObecnosci(idObecnosc, stan, data_mod);
			return 1;
		} else {
			return 0;
		}

	}

	@RequestMapping(value = "/setpresence", method = RequestMethod.POST)
	public @ResponseBody
	Integer setNote(
			@RequestParam(value = "noteid", required = true) int idNotatki,
			@RequestParam(value = "value", required = true) String wartosc) {

		List<Notatki> note = addGroupsService.getNotes(idNotatki);
		if (note.size() > 0) {

			addGroupsService.updateNotakti(idNotatki, wartosc);
			return 1;
		} else {
			return 0;
		}

	}

	// Zmiana oceny na
	// dane spotkanie
	@RequestMapping(value = "/setmark", method = RequestMethod.POST)
	public @ResponseBody
	Integer setMark(
			@RequestParam(value = "markid", required = true) int markid,
			@RequestParam(value = "value", required = true) String wOcena) {
		Date data_mod = new Date();
		List<OcenyCzastkowe> ocenki = addGroupsService.getidOcen(markid);
		if (ocenki.size() > 0) {
			addGroupsService.updateOcenki(markid, wOcena, data_mod);
			return 1;
		} else {
			return 0;
		}

	}

	// Zmiana danych
	// spotkania
	// (uruchamiania
	// zawsze po lost
	// focus nazwy, daty
	// lub wagi oceny)
	@RequestMapping(value = "/setmeeting", method = RequestMethod.POST)
	public @ResponseBody
	Integer setMeeting(

	@RequestParam(value = "meetingid", required = true) int idSpotkanie,
			@RequestParam(value = "name", required = true) String nazwa,
			@RequestParam(value = "date", required = true) String data,
			@RequestParam(value = "weight", required = true) int waga) {
		List<Spotkania> tmpspot = addGroupsService.getIdSpotkania(idSpotkanie);

		// System.out
		// .println("Format daty jest nastepujacy : " + "[" + data + "]");

		if (tmpspot.size() > 0) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date result;

			try {
				result = df.parse(data);
				// System.out
				// .println("(format Date)Jak wyglada data po sparsowaniu: "
				// + "[" + result + "]");
				addGroupsService.updateSpotkania(idSpotkanie, nazwa, result,
						waga);
				// System.out.println("(format stringu)Jak wyglada data po sparsowaniu: "+
				// "[" + df.format(result) + "]");
			} catch (ParseException e) {
				;
			}

			return 1;
		} else {
			return 0;
		}

	}

	// Usuń grupę zajęciową
	@RequestMapping(value = "/removedate", method = RequestMethod.POST)
	public @ResponseBody
	Integer removeGroupDate(
			@RequestParam(value = "dateid", required = true) int idGrupyZaj) {

		List<GrupyZajeciowe> groupZaj = addGroupsService
				.getGroupZaj(idGrupyZaj);
		if (groupZaj.size() > 0) {
			addGroupsService.deleteGroupZaj(idGrupyZaj);
			return 1;
		} else {
			return 0;
		}
	}

	// Edytuj studenta
	@RequestMapping(value = "/editstudent", method = RequestMethod.POST)
	public @ResponseBody
	Integer editStudents(
			@RequestParam(value = "studentid", required = true) int idStudent,
			@RequestParam(value = "groupid", required = true) int idGrupy,
			@RequestParam(value = "finalmark", required = true) String ocena,
			@RequestParam(value = "position", required = true) String pozycja) {

		List<StudenciDoGrupProjektowych> studSize = addGroupsService
				.getStudentsProj(idStudent, idGrupy);
		if (studSize.size() > 0) {
			addGroupsService.updateStudenci(idGrupy, idStudent, pozycja, ocena);
			return 1;
		} else {
			return 0;
		}

		// return 0;
	}

	// Dodaj notatkę
	@RequestMapping(value = "/addnote", method = RequestMethod.POST)
	public @ResponseBody
	Integer addNotes(
			@RequestParam(value = "groupid", required = true) int idGrupyProj,
			@RequestParam(value = "userid", required = true) int idProwadzacego) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		int plik = 0;
		int idNotes = 0;
		String tresc = "_";

		addGroupsService.addNote(idProwadzacego, idGrupyProj, plik, tresc,
				date, date);

		List<Notatki> notes = addGroupsService.getNote(idProwadzacego,
				idGrupyProj);

		if (notes.size() > 0) {
			idNotes = notes.get(notes.size() - 1).getIdNotatki();
			return idNotes;
		} else {
			return -1;
		}
	}

}
