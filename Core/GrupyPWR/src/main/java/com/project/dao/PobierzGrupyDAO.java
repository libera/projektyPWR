package com.project.dao;


import java.util.List;

import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;
import com.project.data.Prowadzacy;

public interface PobierzGrupyDAO {

	public List<Kursy> pobierzKursy(int idKursu );
	
	public List<GrupyZajeciowe> pobierzGrupyZajeciowe(int idProwadzacego);
}
