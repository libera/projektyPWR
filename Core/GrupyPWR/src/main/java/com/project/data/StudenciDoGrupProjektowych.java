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
@Table(name = "studenci_do_grup_projektowych")
public class StudenciDoGrupProjektowych implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5904500508215173391L;

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************
	@Id
	@Column(name = "idstudenci_do_grup_projektowych")
	@GeneratedValue
	private Integer idStudenciDoProjektu;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_studenta", nullable = false)
	private Studenci idStudenta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_grupy_projektowej", nullable = false)
	private GrupyProjektowe idGrupyProjektowej;

	@Column(name = "stanowisko_w_grupie")
	private String stanowiskoW_Grupie;

	@Column(name = "ocena")
	private String ocena;

	@Column(name = "komentarz")
	private String komentarz;

	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************
	public Integer getIdStudenciDoProjektu() {
		return idStudenciDoProjektu;
	}

	public void setIdStudenciDoProjektu(Integer idStudenciDoProjektu) {
		this.idStudenciDoProjektu = idStudenciDoProjektu;
	}

	public Studenci getIdStudenta() {
		return idStudenta;
	}

	public void setIdStudenta(Studenci idStudenta) {
		this.idStudenta = idStudenta;
	}

	public GrupyProjektowe getIdGrupyProjektowej() {
		return idGrupyProjektowej;
	}

	public void setIdGrupyProjektowej(GrupyProjektowe idGrupyProjektowej) {
		this.idGrupyProjektowej = idGrupyProjektowej;
	}

	public String getStanowiskoW_Grupie() {
		return stanowiskoW_Grupie;
	}

	public void setStanowiskoW_Grupie(String stanowiskoW_Grupie) {
		this.stanowiskoW_Grupie = stanowiskoW_Grupie;
	}

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}

	public String getKomentarz() {
		return komentarz;
	}

	public void setKomentarz(String komentarz) {
		this.komentarz = komentarz;
	}

}
