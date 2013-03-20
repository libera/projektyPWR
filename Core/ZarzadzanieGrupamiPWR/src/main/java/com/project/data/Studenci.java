package com.project.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="STUDENCI")
public class Studenci {


	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="IDSTUDENCI")
    @GeneratedValue
    private Integer idStudenci;
	
	@Column(name="IMIONA")
	private String imie;
	
	@Column(name="NAZWISKO")
	private String nazwisko;
	
	@Column(name="NR_INDEKSU")
	private String nrIndeksu;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ROK")
	private Integer rok;
	
	@Column(name="SEMESTR")
	private Integer semestr;

	@Column(name="PRZEDMIOT_KSZTALCENIA")
	private String przedmiot_ksztalcenia;
	
	@Column(name="LOGIN")
	private String login;
	
	@Column(name="HASLO")
	private String haslo;
	//*************************************************************************************************
	//***************************************Getters and Setters***************************************
	//*************************************************************************************************

	public Integer getIdStudenci() {
		return idStudenci;
	}

	public void setIdStudenci(Integer idStudenci) {
		this.idStudenci = idStudenci;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getNrIndeksu() {
		return nrIndeksu;
	}

	public void setNrIndeksu(String nrIndeksu) {
		this.nrIndeksu = nrIndeksu;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRok() {
		return rok;
	}

	public void setRok(Integer rok) {
		this.rok = rok;
	}

	public Integer getSemestr() {
		return semestr;
	}

	public void setSemestr(Integer semestr) {
		this.semestr = semestr;
	}

	public String getPrzedmiot_ksztalcenia() {
		return przedmiot_ksztalcenia;
	}

	public void setPrzedmiot_ksztalcenia(String przedmiot_ksztalcenia) {
		this.przedmiot_ksztalcenia = przedmiot_ksztalcenia;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHaslo() {
		return haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	
}
