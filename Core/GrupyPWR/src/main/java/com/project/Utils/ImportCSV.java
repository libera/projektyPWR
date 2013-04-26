package com.project.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;
import com.project.data.Prowadzacy;
import com.project.data.Studenci;
import com.project.service.LoginService;

public class ImportCSV {

	@Autowired
	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void do_import(String data, int userid) {

		try {
			int iterator = 0;
			BufferedReader reader = new BufferedReader(new StringReader(data));
			String line = "";
			String fields[];
			String rokAkad = null, infoPwr = null, infoEdu = null, komentarz = null, rok = null;
			String semestr = null, name_student = null, nazwisko_student = null, nrInd_student = null, kod_grupy = null;
			String kod_kursu = null, nazwa_kursu = null, termin = null, prowadzacy_id = null, tytul = null, imie = null;
			String nazwisko = null, przedmiot_ksztalcenia = null, login = null, haslo = null;
			String typKalendarz = null, dwusemestr = null;
			Integer rokI = 0, semestrI = 0;
			String email;
			// System.out.println("Dane" + data);
			while ((line = reader.readLine()) != null) {
				fields = line.split(";", -1);

				System.err.println("fields.length:" + fields.length);

				if (fields[0].equals("Politechnika Wroc³awska")) {
					infoPwr = fields[0];
					continue;
				} else if (fields[0].equals("Rok akademicki")) {
					rokAkad = fields[0] + ";" + fields[1];
					continue;
				} else if (fields[0].equals("Typ klendarza")) {
					typKalendarz = fields[0] + ";" + fields[1];
					continue;
				} else if (fields[0].equals("Semestr")
						&& (fields[1].equals("Letni") || fields[1]
								.equals("Zimowy"))) {
					if (fields[1].equals("Letni")) {
						dwusemestr = fields[0] + ";" + fields[1];
					} else if (fields[1].equals("Zimowy")) {
						dwusemestr = fields[0] + ";" + fields[1];
					}
					continue;
				} else if (fields[0].equals("Kod grupy")) {
					kod_grupy = fields[1];
					continue;
				} else if (fields[0].equals("Kod kursu")) {
					kod_kursu = fields[1];
					continue;
				} else if (fields[0].equals("Termin")) {
					termin = fields[1];
					continue;
				} else if (fields[0].equals("Nazwa kursu")) {
					nazwa_kursu = fields[1];
					// Dodanie do tabeli kursy danych.

					loginService.addKursy(kod_kursu, nazwa_kursu);
					System.out.println("Dodawanie kodow kursów");
					continue;
				} else if (fields[0].equals("Prowadzacy")) {
					fields[1] = fields[1].trim().replaceAll(" +", " ");
					fields = fields[1].split(" ");
					for (int i = 0; i < fields.length - 1; i++) {
						if (fields[i].equalsIgnoreCase("prof.")
								|| fields[i].equalsIgnoreCase("dr")
								|| fields[i].equalsIgnoreCase("in¿.")
								|| fields[i].equalsIgnoreCase("hab.")
								|| fields[i].equalsIgnoreCase("mgr")
								|| fields[i].equalsIgnoreCase("mgr.")) {
							if (!tytul.isEmpty()) {
								tytul += " ";
							}
							tytul += fields[i];
						} else {
							if (!imie.isEmpty()) {
								imie += " ";
							}
							imie += fields[i];
							if (prowadzacy_id == null) {
								prowadzacy_id = imie;
							}
						}
					}
					nazwisko = fields[fields.length - 1];
					prowadzacy_id += "." + nazwisko;
					continue;
				} else if (fields[0].matches("[0-9]+")) {
					nrInd_student = fields[1].substring(4);
					name_student = fields[3];
					rok = fields[4];
					semestr = fields[5];
					przedmiot_ksztalcenia = fields[6];
					komentarz = fields[9];
					rokI = Integer.parseInt(rok);
					semestrI = Integer.parseInt(semestr);
					nazwisko_student = fields[2];
					infoEdu = "\n" + infoPwr + "\n" + rokAkad + "\n"
							+ typKalendarz + "\n" + dwusemestr + "\n"
							+ "Kod grupy;" + kod_grupy + "\n" + "Kod kursu;"
							+ kod_kursu + "\n" + "Nazwa kursu;" + nazwa_kursu
							+ "\n" + "Termin;" + termin + "\n" + "Prowadzacy"
							+ prowadzacy_id + "\n";
					email = nrInd_student + "@student.pwr.wroc.pl";
					login = nrInd_student;
					haslo = nrInd_student;

					loginService.addStudenci(name_student, nazwisko_student,
							nrInd_student, email, rokI, semestrI,
							przedmiot_ksztalcenia, login, haslo);
				} else
					continue;
				System.out.println("field[0]" + fields[0]);
				int sIdKursu = 0;
				// loginService.addKursy(kod_kursu, nazwa_kursu);

				List<Kursy> klistKursies = loginService.validateKursy(
						kod_kursu, nazwa_kursu);

				List<Studenci> slistList = loginService
						.validateSname(nrInd_student);
				List<GrupyZajeciowe> glist = loginService.validateGrupyza(
						kod_grupy, termin);

				System.out.println("rozmiarklistkURSIES: "
						+ klistKursies.size());

				if (klistKursies.size() == 1 && iterator == 0) {
					sIdKursu = klistKursies.get(0).getIdKursy();
					loginService.addGrupyZajeciowe(kod_grupy, infoEdu, userid,
							sIdKursu, nazwa_kursu, termin, komentarz);
					iterator++;
				}

				// Tutaj pobieramy id studenta ktory jest w tabeli studenci
				int sIdStudent = slistList.get(0).getIdStudenci();
				System.out
						.println("Id Studencika, którego chcemy przypisac do grup zjeciowych: "
								+ sIdStudent);
				
				if (glist.size() > 0) {
					System.out
					.println("Id grupy zajeciowej, ktora nalezy przypisac do encji student do zajec (TUTAJ SIE WYWALA!!!): "
							+ glist.get(0).getIdGrupyZajeciowe());
					int sIdGrupyZaj = glist.get(0).getIdGrupyZajeciowe();
					loginService.addStudenciDoGrupZajeciowych(sIdStudent,
							sIdGrupyZaj, sIdGrupyZaj);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
