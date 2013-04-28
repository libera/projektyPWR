package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.PobierzGrupyZajDAO;
import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Spotkania;
import com.project.data.Studenci;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;

@Service
public class PobranieGrupZajServiceImpl implements PobranieGrupZajService {

	@Autowired
	private PobierzGrupyZajDAO pobierzGrupyZajDAO;

	@Transactional
	public List<GrupyZajeciowe> pobierzGrupyZajeciowe(int idGrupy) {
		return pobierzGrupyZajDAO.pobierzGrupyZajeciowe(idGrupy);
	}
	
	@Transactional
	public List<GrupyProjektowe> pobierzGrupyProjektowe(int idGrupy) {
		return pobierzGrupyZajDAO.pobierzGrupyProjektowe(idGrupy);
	}

	@Transactional
	public List<StudenciDoGrupProjektowych> pobierzStudentowZgrupy(int idGrupyProjektowej) {
		return pobierzGrupyZajDAO.pobierzStudentowZgrupy(idGrupyProjektowej);
	}
	
	

	@Transactional
	public List<Studenci> pobierzStudentow(int idStudenci) {
		return pobierzGrupyZajDAO.pobierzStudentow(idStudenci);
	}
	
	@Transactional
	public List<Obecnosc> pobierzObecnosci(int idSpotkania, int idStudenta){
		return pobierzGrupyZajDAO.pobierzObecnosci(idSpotkania,idStudenta );
	}
	@Transactional
	public List<OcenyCzastkowe> pobierzOcenyCzastkowe(int idSpotkania, int idStudenta){
		return pobierzGrupyZajDAO.pobierzOcenyCzastkowe(idSpotkania, idStudenta);
	}
	
	@Transactional
	public List<Spotkania> pobierzSpotkania(int idGrupyZajeciowe) {
		return pobierzGrupyZajDAO.pobierzSpotkania(idGrupyZajeciowe);
	}

	@Transactional
	public List<StudenciDoGrupZajeciowych> pobierzStudGrup(int idGrupyChodzacej) {

		return pobierzGrupyZajDAO.pobierzStudGrup(idGrupyChodzacej);
	}

}
