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
@Table(name = "grupy_zajeciowe")
public class GrupyZajeciowe implements Serializable {

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************

	/**
	 * 
	 */
	private static final long serialVersionUID = -9093136662149609705L;

	@Id
	@Column(name = "idgrupy_zajeciowe")
	@GeneratedValue
	private Integer idGrupyZajeciowe;

	@Column(name = "kod_grupy")
	private String kodGrupy;

	@Column(name = "info_edukacja")
	private String infoEdu;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_prowadzacego", nullable = false)
	private Prowadzacy idProwadzacego;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_kursu", nullable = false)
	private Kursy idKursu;

	@Column(name = "nazwa")
	private String nazwa;

	@Column(name = "termin")
	private Integer termin;

	@Column(name = "komentarz")
	private Integer komentarz;

	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************

	public Integer getIdGrupyZajeciowe() {
		return idGrupyZajeciowe;
	}

	public void setIdGrupyZajeciowe(Integer idGrupyZajeciowe) {
		this.idGrupyZajeciowe = idGrupyZajeciowe;
	}

	public String getKodGrupy() {
		return kodGrupy;
	}

	public void setKodGrupy(String kodGrupy) {
		this.kodGrupy = kodGrupy;
	}

	public String getInfoEdu() {
		return infoEdu;
	}

	public void setInfoEdu(String infoEdu) {
		this.infoEdu = infoEdu;
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

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Integer getTermin() {
		return termin;
	}

	public void setTermin(Integer termin) {
		this.termin = termin;
	}

	public Integer getKomentarz() {
		return komentarz;
	}

	public void setKomentarz(Integer komentarz) {
		this.komentarz = komentarz;
	}
}