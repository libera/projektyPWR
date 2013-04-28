package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.PobierzGrupyDAO;
import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;

@Service
public class PobierzGrupyServiceImpl implements PobierzGrupyService {

	@Autowired
	private PobierzGrupyDAO pobierzGrupyDAO;

	@Transactional
	public List<Kursy> pobierzKursy(int idKursu) {
		return pobierzGrupyDAO.pobierzKursy(idKursu);
	}

	@Transactional
	public List<GrupyZajeciowe> pobierzGrupyZajeciowe(int idProwadzacego) {
		return pobierzGrupyDAO.pobierzGrupyZajeciowe(idProwadzacego);
	}

	@Transactional
	public List<GrupyProjektowe> pobierzGrupke(int idGrupyZajeciowe) {
		return pobierzGrupyDAO.pobierzGrupke(idGrupyZajeciowe);
	}

	@Transactional
	public void addGrupyProj(int idGrupyZajeciowe, String nazwa, String temat,
			String resositoryLink, String komentarz) {
		pobierzGrupyDAO.addGrupyProj(idGrupyZajeciowe, nazwa, temat,
				resositoryLink, komentarz);

	}
}
