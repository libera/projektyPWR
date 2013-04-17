package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.LoginDAO;
import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;
import com.project.data.Prowadzacy;
import com.project.data.Studenci;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDAO loginDAO;

	@Transactional
	public void addProwadzacy(Prowadzacy prowadzacy) {
		loginDAO.addProwadzacy(prowadzacy);

	}

	@Transactional
	public void aktywacja(String login, boolean aktywowany) {
		loginDAO.aktywacja(login, aktywowany);
	}

	@Transactional
	public List<Prowadzacy> listProwadzacy() {
		return loginDAO.listProwadzacy();
	}

	@Transactional
	public List<Kursy> listKursy() {
		return loginDAO.listKursy();
	}

	@Transactional
	public List<GrupyZajeciowe> listGrupyZajeciowe() {
		return loginDAO.listGrupyZajeciowe();
	}

	@Transactional
	public List<Studenci> listStudenci() {
		return loginDAO.listStudenci();
	}

	@Transactional
	public List<Prowadzacy> validateLogin(String login, String haslo) {
		return loginDAO.validateLogin(login, haslo);
	}
	@Transactional
	public List<Studenci> validateSname(String nr_indeksu) {
		return loginDAO.validateSname(nr_indeksu);
	}
	@Transactional
	public List<GrupyZajeciowe> validateGrupyza(String kod_grupy) {
		return loginDAO.validateGrupyza(kod_grupy);
	}
	@Transactional
	public List<Prowadzacy> validatePname(String imiona, String nazwisko){
		return loginDAO.validatePname(imiona, nazwisko);
	}
	@Transactional
	public List<Kursy> validateKursy(String kod_kursu, String nazwa_kursu){
		return loginDAO.validateKursy(kod_kursu, nazwa_kursu);
	}

	@Transactional
	public List<Prowadzacy> validateRegister(String imiona, String nazwisko,
			String email, String login) {
		return loginDAO.validateRegister(imiona, nazwisko, email, login);
	}

	@Transactional
	public void addKursy(String kodkursu, String nazwakursu) {
		loginDAO.addKursy(kodkursu, nazwakursu);
	}

	@Transactional
	public void addStudenci(String imie, String nazwisko, String nrIndeksu,
			String email, Integer rok, Integer semestr, String przedmiot,
			String login, String haslo) {
		loginDAO.addStudenci(imie, nazwisko, nrIndeksu, email, rok, semestr,
				przedmiot, login, haslo);
	}

	@Transactional
	public void addStudenciDoGrupZajeciowych(Integer idStudenta,
			Integer idGrupyOryginalnej, Integer idGrupyChodzacej) {
		loginDAO.addStudenciDoGrupZajeciowych(idStudenta, idGrupyOryginalnej,
				idGrupyChodzacej);
	}

	@Transactional
	public void addGrupyZajeciowe(String kodGrupy, String infoEdu,
			Integer idProwadzacego, Integer idKursu, String nazwa,
			String termin, String komentarz) {
		loginDAO.addGrupyZajeciowe(kodGrupy, infoEdu, idProwadzacego, idKursu,
				nazwa, termin, komentarz);
	}

}