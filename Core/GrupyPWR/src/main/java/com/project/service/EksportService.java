package com.project.service;

import java.util.List;

import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Studenci;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;

public interface EksportService {

	public  List<GrupyZajeciowe> getGroupsZaj(int idGrupyZaj);
	public List<StudenciDoGrupZajeciowych> getStudZaj(int idGrupy);
	public List<GrupyProjektowe> getGroupsProj(int idGrupy);
	public List<StudenciDoGrupProjektowych> studProj(int idStud, int idGrupy) ;
	public List<Studenci> getStudents(int idStudent);

}
