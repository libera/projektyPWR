package com.project.dao;

import java.util.Date;
import java.util.List;

import com.project.data.GrupyProjektowe;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Spotkania;
import com.project.data.StudenciDoGrupProjektowych;

public interface AddGroupsDAO {

	public List<Spotkania> getSpotkaniaByGroup(int idGrupy);
	public void updateStudZaj(int idStud, int idGrupyStatic, int idGrupyChange);
	public List<GrupyProjektowe> getIdGrupZaj(int idGrupyProj);
	public void addStudents(Integer idStudent, Integer idGrupZaj, String stanowisko, String ocena, String komentarz);
	public List<OcenyCzastkowe> getOcenki(int idStudenta, int idSpotkania);
	public List<Obecnosc> getObecnosc(int idStudenta, int idSpotkania);
	public List<StudenciDoGrupProjektowych> getStudent(int idStudent);
	public void addSpotkania(Integer idGrupyProj, Date dataSpotkania, String nazwa, Integer waga);
	public List<Spotkania> getSpotByGroupId(int idGrupyProj);
}
