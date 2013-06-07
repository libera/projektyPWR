package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.EksportDAO;
import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Studenci;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;

@Service
public class EksportServiceImpl implements EksportService {

	@Autowired
	private EksportDAO eksportdao;

	@Transactional
	public List<GrupyZajeciowe> getGroupsZaj(int idGrupyZaj) {
		return eksportdao.getGroupsZaj(idGrupyZaj);
	}

	@Transactional
	public List<StudenciDoGrupZajeciowych> getStudZaj(int idGrupy) {
		return eksportdao.getStudZaj(idGrupy);
	}

	@Transactional
	public List<GrupyProjektowe> getGroupsProj(int idGrupy) {
		return eksportdao.getGroupsProj(idGrupy);
	}

	@Transactional
	public List<StudenciDoGrupProjektowych> studProj(int idStud, int idGrupy) {
		return eksportdao.studProj(idStud, idGrupy);
	}
	@Transactional
	public List<Studenci> getStudents(int idStudent){
		return eksportdao.getStudents(idStudent);
	}

}
