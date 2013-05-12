package com.project.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.AddGroupsDAO;
import com.project.data.GrupyProjektowe;
import com.project.data.Kursy;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Prowadzacy;
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
	public void addStudents(Integer idStudent, Integer idGrupZaj, String stanowisko, String ocena, String komentarz){
		addGroupsDAO.addStudents(idStudent, idGrupZaj, stanowisko, ocena, komentarz);
	}
	
	@Transactional
	public List<GrupyProjektowe> getIdGrupZaj(int idGrupyProj) {
		return addGroupsDAO.getIdGrupZaj(idGrupyProj);
	}
	
	@Transactional
	public void updateStudZaj(int idStud, int idGrupyStatic, int idGrupyChange){
		addGroupsDAO.updateStudZaj(idStud, idGrupyStatic, idGrupyChange);
	}
	
	@Transactional
	public List<OcenyCzastkowe> getOcenki(int idStudenta, int idSpotkania){
		return addGroupsDAO.getOcenki(idStudenta, idSpotkania);
	}
	
	@Transactional
	public List<Obecnosc> getObecnosc(int idStudenta, int idSpotkania){
		return addGroupsDAO.getObecnosc(idStudenta, idSpotkania);
	}
	
	@Transactional
	public List<StudenciDoGrupProjektowych> getStudent(int idStudent){
		return addGroupsDAO.getStudent(idStudent);
	}

}
