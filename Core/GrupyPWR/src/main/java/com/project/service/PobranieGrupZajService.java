package com.project.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Notatki;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Spotkania;
import com.project.data.Studenci;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;

public interface PobranieGrupZajService {
	public List<GrupyZajeciowe> pobierzGrupyZajeciowe(int idGrupy);
	public List<GrupyProjektowe> pobierzGrupyProjektowe(int idGrupy) ;
	public List<StudenciDoGrupProjektowych> pobierzStudentowZgrupy(int idGrupyProjektowej);
	public List<Studenci> pobierzStudentow(int idStudenci) ;
	
	public List<Obecnosc> pobierzObecnosci(int idSpotkania, int idStudenta);
	public List<OcenyCzastkowe> pobierzOcenyCzastkowe(int idSpotkania, int idStudenta);
	public List<Spotkania> pobierzSpotkania(int idGrupyZajeciowe);
	public List<StudenciDoGrupZajeciowych> pobierzStudGrup(int idGrupyChodzacej);
	
	public List<Notatki> pobierzNotatki(int idGrupyProjektowej);
}
