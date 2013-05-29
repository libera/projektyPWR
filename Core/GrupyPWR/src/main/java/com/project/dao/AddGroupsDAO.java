package com.project.dao;

import java.util.Date;
import java.util.List;

import com.project.data.GrupyProjektowe;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Spotkania;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;

public interface AddGroupsDAO {

	public List<Spotkania> getSpotkaniaByGroup(int idGrupy);

	public void updateStudZaj(int idStud, int idGrupyStatic, int idGrupyChange);

	public List<GrupyProjektowe> getIdGrupZaj(int idGrupyProj);

	public void addStudents(Integer idStudent, Integer idGrupZaj,
			String stanowisko, String ocena, String komentarz);

	public List<OcenyCzastkowe> getOcenki(int idStudenta, int idSpotkania);

	public List<Obecnosc> getObecnosc(int idStudenta, int idSpotkania);

	public List<StudenciDoGrupProjektowych> getStudent(int idStudent);

	public void addSpotkania(Integer idGrupyProj, Date dataSpotkania,
			String nazwa, Integer waga);

	public List<Spotkania> getSpotByGroupId(int idGrupyProj);

	public void deleteGroupProj(int idGrupyZaj);

	public void updateGrupZaj(int idGrupy, String nazwa, String temat,
			String repository, String comment);

	public List<Obecnosc> getIdObec(int idObec);

	public void updateObecnosci(int idObec, boolean stan, Date data_mod);

	public List<OcenyCzastkowe> getidOcen(int idOcen);

	public void updateOcenki(int idOcen, String wart, Date data_mod);
	
	public List<Spotkania> getIdSpotkania(int idSpot);
	public void updateSpotkania(int idSpotkania, String nazwa, Date data,
			int waga);
	
	public void deleteStudents(Integer idStudent);
	
	public List<StudenciDoGrupZajeciowych> getStudGroupZaj(int idStudent);
}
