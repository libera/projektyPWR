package com.project.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prowadzacy")
public class Prowadzacy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************

	@Id
	@Column(name = "idprowadzacy")
	@GeneratedValue
	private Integer idProwadzacy;

	@Column(name = "imiona")
	private String imiona;

	@Column(name = "nazwisko")
	private String nazwisko;

	@Column(name = "email")
	private String email;

	@Column(name = "login")
	private String login;

	@Column(name = "haslo")
	private String haslo;

	@Column(name = "waznosc")
	private boolean waznosc;

	@Column(name = "data_dodania", columnDefinition = "DATETIME")
	private Date dataDodania;

	@Column(name = "aktywowany")
	private boolean aktywowany;

	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************

	public Prowadzacy() {

	}

	public Integer getIdProwadzacy() {
		return idProwadzacy;
	}

	public void setIdProwadzacy(Integer idProwadzacy) {
		this.idProwadzacy = idProwadzacy;
	}

	public String getImiona() {
		return imiona;
	}

	public void setImiona(String imiona) {
		this.imiona = imiona;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	// --------------------------------------
	// Trzeba zaszyfrowac MD5 - najlepiej :)
	// complete :):)
	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	// --------------------------------------
	public boolean isWaznosc() {
		return waznosc;
	}

	public void setWaznosc(boolean waznosc) {
		this.waznosc = waznosc;
	}

	public Date getDataDodania() {
		return dataDodania;
	}

	public void setDataDodania(Date dataDodania) {
		this.dataDodania = dataDodania;
	}

	public boolean isAktywowany() {
		return aktywowany;
	}

	public void setAktywowany(boolean aktywowany) {
		this.aktywowany = aktywowany;
	}

}
