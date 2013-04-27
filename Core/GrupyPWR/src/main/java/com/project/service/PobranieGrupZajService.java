package com.project.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.data.GrupyZajeciowe;

public interface PobranieGrupZajService {
	public List<GrupyZajeciowe> pobierzGrupyZajeciowe(int idGrupy);

}
