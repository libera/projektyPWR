package com.project.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pozwolenia_na_laczenia")
public class PozwoleniaNaLaczenia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6916454480794048713L;

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************
	@Id
	@Column(name = "idpozwolenia_na_laczenia")
	@GeneratedValue
	private Integer idPozwoleniaNaLaczenia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_prowadzacego", nullable = false)
	private Prowadzacy idProwadzacego;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_kursu", nullable = false)
	private Kursy idKursu;

	@Column(name = "decyzja")
	private boolean decyzja;

	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************
	public Integer getIdPozwoleniaNaLaczenia() {
		return idPozwoleniaNaLaczenia;
	}

	public void setIdPozwoleniaNaLaczenia(Integer idPozwoleniaNaLaczenia) {
		this.idPozwoleniaNaLaczenia = idPozwoleniaNaLaczenia;
	}

	public Prowadzacy getIdProwadzacego() {
		return idProwadzacego;
	}

	public void setIdProwadzacego(Prowadzacy idProwadzacego) {
		this.idProwadzacego = idProwadzacego;
	}

	public Kursy getIdKursu() {
		return idKursu;
	}

	public void setIdKursu(Kursy idKursu) {
		this.idKursu = idKursu;
	}

	public boolean isDecyzja() {
		return decyzja;
	}

	public void setDecyzja(boolean decyzja) {
		this.decyzja = decyzja;
	}

}
