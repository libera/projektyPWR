package com.project.service;

import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;
import com.project.data.Prowadzacy;
import com.project.data.Studenci;

import java.util.List;

public interface LoginService {
	public void addProwadzacy(Prowadzacy prowadzacy);

	public void addKursy(String kodkursu, String nazwakursu);

	public void addStudenci(String imie, String nazwisko, String nrIndeksu,
			String email, Integer rok, Integer semestr, String przedmiot,
			String login, String haslo);

	public void addStudenciDoGrupZajeciowych(Integer idStudenta,
			Integer idGrupyOryginalnej, Integer idGrupyChodzacej);

	public void addGrupyZajeciowe(String kodGrupy, String infoEdu,
			Integer idProwadzacego, Integer idKursu, String nazwa,
			String termin, String komentarz);

	public List<Studenci> validateSname(String nr_indeksu);

	public List<GrupyZajeciowe> validateGrupyza(String kod_grupy);

	public List<Prowadzacy> validatePname(String imiona, String nazwisko);

	public List<Kursy> validateKursy(String kod_kursu, String nazwa_kursu);

	public List<Prowadzacy> listProwadzacy();

	public List<Kursy> listKursy();

	public List<GrupyZajeciowe> listGrupyZajeciowe();

	public List<Studenci> listStudenci();

	public List<Prowadzacy> validateLogin(String login, String haslo);

	public List<Prowadzacy> validateRegister(String imiona, String nazwisko,
			String email, String login);

	public void aktywacja(String login, boolean aktywowany);

}