package com.project.data;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="studenci")
public class Studenci implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7089726535691657138L;

	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="idstudenci")
    @GeneratedValue
    private Integer idStudenci;
	
	@Column(name="imiona")
	private String imie;
	
	@Column(name="nazwisko")
	private String nazwisko;
	
	@Column(name="nr_indeksu")
	private String nrIndeksu;
	
	@Column(name="email")
	private String email;
	
	@Column(name="rok")
	private Integer rok;
	
	@Column(name="semestr")
	private Integer semestr;

	@Column(name="przedmiot_ksztalcenia")
	private String przedmiot_ksztalcenia;
	
	@Column(name="login")
	private String login;
	
	@Column(name="haslo")
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
