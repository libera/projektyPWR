package com.project.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "grupy_projektowe")
public class GrupyProjektowe implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3580949225448137379L;

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************
	@Id
	@Column(name = "idgrupy_projektowe")
	@GeneratedValue
	private Integer idGrupyProjektowe;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_grupy_zajeciowej", nullable = false)
	private GrupyZajeciowe idGrupyZajeciowe;

	@Column(name = "nazwa")
	private String nazwa;

	@Column(name = "temat")
	private String temat;

	@Column(name = "resository_link")
	private String resositoryLink;

	@Column(name = "komentarz")
	private String komentarz;

	@Column(name = "ocena")
	private String ocena;

	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************
	public Integer getIdGrupyProjektowe() {
		return idGrupyProjektowe;
	}

	public void setIdGrupyProjektowe(Integer idGrupyProjektowe) {
		this.idGrupyProjektowe = idGrupyProjektowe;
	}

	public GrupyZajeciowe getIdGrupyZajeciowe() {
		return idGrupyZajeciowe;
	}

	public void setIdGrupyZajeciowe(GrupyZajeciowe idGrupyZajeciowe) {
		this.idGrupyZajeciowe = idGrupyZajeciowe;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getTemat() {
		return temat;
	}

	public void setTemat(String temat) {
		this.temat = temat;
	}

	public String getResositoryLink() {
		return resositoryLink;
	}

	public void setResositoryLink(String resositoryLink) {
		this.resositoryLink = resositoryLink;
	}

	public String getKomentarz() {
		return komentarz;
	}

	public void setKomentarz(String komentarz) {
		this.komentarz = komentarz;
	}

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}

}
