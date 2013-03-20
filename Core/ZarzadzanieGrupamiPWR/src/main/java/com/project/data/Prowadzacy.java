package com.project.data;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
@Table(name="PROWADZACY")
public class Prowadzacy {
		//****************************************************************************************
 		//***************************************Attributes***************************************
 		//****************************************************************************************

	 	@Id
	    @Column(name="IDPROWADZACY")
	    @GeneratedValue
	    private Integer idProwadzacy;
	 	
	 	@Column(name="IMIONA")
	    private String imiona;
	 	
	 	@Column(name="NAZWISKO")
	    private String nazwisko;
	 	
	 	@Column(name="EMAIL")
	    private String email;
	 	
	 	@Column(name="LOGIN")
	    private String login;
	 	
	 	@Column(name="HASLO")
	    private String haslo;
	 	
	 	@Column(name="WAZNOSC")
	    private boolean waznosc;
	 	
	 	@Column(name="DATA_DODANIA", columnDefinition="DATETIME")
	    private Date dataDodania;
	 	
	 	@Column(name="AKTYWOWANY")
	    private boolean aktywowany;

	 	
	 	//*************************************************************************************************
	 	//***************************************Getters and Setters***************************************
	 	//*************************************************************************************************

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
		//--------------------------------------
		//Trzeba zaszyfrowac MD5 - najlepiej :)
		public String getHaslo() {
			return haslo;
		}

		public void setHaslo(String haslo) {
			this.haslo = haslo;
		}
		//--------------------------------------
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
	    

