package com.project.data;


import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
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
	 	
	 	@NotEmpty
	 	@Column(name="IMIONA")
	    private String imiona;
	 	
	 	@NotEmpty
	 	@Column(name="NAZWISKO")
	    private String nazwisko;
	 	
	 	@NotEmpty
	 	@Email
	 	@Column(name="EMAIL")
	    private String email;
	 	
	 	@NotEmpty
	 	@Column(name="LOGIN")
	    private String login;
	 	
	 	
	 	@NotEmpty
	 	@Size(min = 4, max = 45)
	 	@Column(name="HASLO")
	    private String haslo;
	 	
	 	@NotEmpty
	 	private String comfirmPassword;
	 	
	 	@Column(name="WAZNOSC")
	    private boolean waznosc;
	 	
	 	@Column(name="DATA_DODANIA", columnDefinition="DATETIME")
	    private Date dataDodania;
	 	
	 	@Column(name="AKTYWOWANY")
	    private boolean aktywowany;

	 	
	 	//*************************************************************************************************
	 	//***************************************Getters and Setters***************************************
	 	//*************************************************************************************************

	 	public Prowadzacy() {
	 		
	 	}
	 	
	 	public Prowadzacy(Integer idProwadzacy, String imiona,String nazwisko, String email,
	 			String login, String haslo,boolean waznosc, Date dataDodania, boolean aktywowany) {
	 		
	 		this.idProwadzacy=idProwadzacy;
	 		this.imiona = imiona;
	 		this.nazwisko = nazwisko;
	 		this.email= email;
	 		this.login = login;
	 		this.haslo = haslo;
	 		this.waznosc = waznosc;
	 		this.dataDodania=dataDodania;
	 		this.aktywowany=aktywowany;
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
		//--------------------------------------
		//Trzeba zaszyfrowac MD5 - najlepiej :)
		//complete :):)
		public String getHaslo() {
			return haslo;
		}

		public void setHaslo(String haslo) {
			this.haslo = haslo;
		}
		
		public void setConfirmPassword(String confirmPassword) {
			this.comfirmPassword=confirmPassword;
		}
		
		public String getConfirmPassword() {
			return comfirmPassword;
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
	    

