package com.project.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name = "kursy")
public class Kursy implements Serializable {

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************

	/**
	 * 
	 */
	private static final long serialVersionUID = -2485914145322350750L;

	@Id
	@Column(name = "idkursy")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idKursy;

	@Column(name = "kod_kursu")
	private String kodKursu;

	@Column(name = "nazwa_kursu")
	private String nazwaKursu;

	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************

	public Integer getIdKursy() {
		return idKursy;
	}

	public void setIdKursy(Integer idKursy) {
		this.idKursy = idKursy;
	}

	public String getKodKursu() {
		return kodKursu;
	}

	public void setKodKursu(String kodKursu) {
		this.kodKursu = kodKursu;
	}

	public String getNazwaKursu() {
		return nazwaKursu;
	}

	public void setNazwaKursu(String nazwaKursu) {
		this.nazwaKursu = nazwaKursu;
	}

}