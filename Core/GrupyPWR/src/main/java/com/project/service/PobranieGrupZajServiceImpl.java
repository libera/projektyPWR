package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PobierzGrupyZajDAO;
import com.project.data.GrupyZajeciowe;

@Service
public class PobranieGrupZajServiceImpl implements PobranieGrupZajService {

	@Autowired
	private PobierzGrupyZajDAO pobierzGrupyZajDAO;

	public List<GrupyZajeciowe> pobierzGrupyZajeciowe(int idGrupy) {
		return pobierzGrupyZajDAO.pobierzGrupyZajeciowe(idGrupy);
	}

}
