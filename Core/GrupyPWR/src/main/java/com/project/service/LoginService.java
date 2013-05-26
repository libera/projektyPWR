package com.project.service;

import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;
import com.project.data.Prowadzacy;
import com.project.data.Studenci;

import java.sql.SQLException;
import java.util.List;

public interface LoginService {
	public void addProwadzacy(Prowadzacy prowadzacy);

	public void addKursy(String kodkursu, String nazwakursu);

	public Integer addStudenci(String imie, String nazwisko, String nrIndeksu,
			String email, Integer rok, Integer semestr, String przedmiot,
			String login, String haslo);

	public void addStudenciDoGrupZajeciowych(Integer idStudenta,
			Integer idGrupyOryginalnej, Integer idGrupyChodzacej);

	public void addGrupyZajeciowe(String kodGrupy, String infoEdu,
			Integer idProwadzacego, Integer idKursu, String nazwa,
			String termin, String komentarz);

	public List<Studenci> validateSname(String nr_indeksu);

	public List<GrupyZajeciowe> validateGrupyza(String kod_grupy, String termin);

	public List<Prowadzacy> validatePname(String imiona, String nazwisko);

	public List<Kursy> validateKursy(String kodKursu, String nazwaKursu);

	public List<Prowadzacy> listProwadzacy();

	public List<Kursy> listKursy();

	public List<GrupyZajeciowe> listGrupyZajeciowe();

	public List<Studenci> listStudenci();

	public List<Prowadzacy> validateLogin(String login, String haslo);

	public List<Prowadzacy> validatePass(Integer userid, String haslo);

	public List<Prowadzacy> logout(Integer userid);

	public List<Prowadzacy> validateRegister(String imiona, String nazwisko,
			String email, String login);

	public void aktywacja(String login, boolean aktywowany);

	public void zmienPass(Integer iduser, String haslo);

}