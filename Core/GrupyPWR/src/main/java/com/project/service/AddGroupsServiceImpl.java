package com.project.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.AddGroupsDAO;
import com.project.data.GrupyProjektowe;

import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.StudenciDoGrupZajeciowych;

import com.project.data.Spotkania;
import com.project.data.StudenciDoGrupProjektowych;

@Service
public class AddGroupsServiceImpl implements AddGroupsService {

	@Autowired
	private AddGroupsDAO addGroupsDAO;

	@Transactional
	public List<Spotkania> getSpotkaniaByGroup(int idGrupy) {
		return addGroupsDAO.getSpotkaniaByGroup(idGrupy);
	}
	@Transactional
	public List<StudenciDoGrupProjektowych> getStudByGroup(int idGrupy){
		return addGroupsDAO.getStudByGroup(idGrupy);
	}

	@Transactional
	public void addStudents(Integer idStudent, Integer idGrupZaj,
			String stanowisko, String ocena, String komentarz) {
		addGroupsDAO.addStudents(idStudent, idGrupZaj, stanowisko, ocena,
				komentarz);
	}

	@Transactional
	public List<Obecnosc> getIdObec(int idObec) {
		return addGroupsDAO.getIdObec(idObec);
	}
	
	@Transactional
	public List<StudenciDoGrupZajeciowych> getStudGroupZaj(int idStudent) {
		return addGroupsDAO.getStudGroupZaj(idStudent);
	}

	@Transactional
	public void updateObecnosci(int idObec, boolean stan, Date data_mod) {
		addGroupsDAO.updateObecnosci(idObec, stan, data_mod);
	}
	
	public List<Spotkania> getIdSpotkania(int idSpot) {
		return addGroupsDAO.getIdSpotkania(idSpot);
	}
	public void updateSpotkania(int idSpotkania, String nazwa, Date data,
			int waga) {
		addGroupsDAO.updateSpotkania(idSpotkania, nazwa, data, waga);
		
	}

	@Transactional
	public List<OcenyCzastkowe> getidOcen(int idOcen){
		return addGroupsDAO.getidOcen(idOcen);
	}


	@Transactional
	public void updateOcenki(int idOcen, String wart, Date data_mod) {
		addGroupsDAO.updateOcenki(idOcen, wart, data_mod);
	}

	@Transactional
	public void addSpotkania(Integer idGrupyProj, Date dataSpotkania,
			String nazwa, Integer waga) {
		addGroupsDAO.addSpotkania(idGrupyProj, dataSpotkania, nazwa, waga);
	}

	@Transactional
	public void deleteGroupProj(int idGrupyZaj) {
		addGroupsDAO.deleteGroupProj(idGrupyZaj);
	}
	@Transactional
	public void deleteStudents(Integer idStudent) {
		addGroupsDAO.deleteStudents(idStudent);
	}

	@Transactional
	public List<GrupyProjektowe> getIdGrupZaj(int idGrupyProj) {
		return addGroupsDAO.getIdGrupZaj(idGrupyProj);
	}

	@Transactional
	public void updateStudZaj(int idStud, int idGrupyStatic, int idGrupyChange) {
		addGroupsDAO.updateStudZaj(idStud, idGrupyStatic, idGrupyChange);
	}

	@Transactional
	public void updateGrupZaj(int idGrupy, String nazwa, String temat,
			String repository, String comment) {
		addGroupsDAO.updateGrupZaj(idGrupy, nazwa, temat, repository, comment);
	}

	@Transactional
	public List<OcenyCzastkowe> getOcenki(int idStudenta, int idSpotkania) {
		return addGroupsDAO.getOcenki(idStudenta, idSpotkania);
	}

	@Transactional
	public List<Obecnosc> getObecnosc(int idStudenta, int idSpotkania) {
		return addGroupsDAO.getObecnosc(idStudenta, idSpotkania);
	}

	@Transactional
	public List<StudenciDoGrupProjektowych> getStudent(int idStudent) {
		return addGroupsDAO.getStudent(idStudent);
	}

	@Transactional
	public List<Spotkania> getSpotByGroupId(int idGrupyProj) {
		return addGroupsDAO.getSpotByGroupId(idGrupyProj);
	}

}
