package com.project.Json;

import java.util.List;

public class JsonGrupy {

	private String name;
	private int id;
	private List<JsonGrupyZajeciowe> dates;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<JsonGrupyZajeciowe> getDates() {
		return dates;
	}

	public void setDates(List<JsonGrupyZajeciowe> dates) {
		this.dates = dates;
	}
}
