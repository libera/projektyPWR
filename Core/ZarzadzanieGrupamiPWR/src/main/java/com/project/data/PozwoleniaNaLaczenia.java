package com.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="POZWOLENIA_NA_LACZENIA")
public class PozwoleniaNaLaczenia {

	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="IDPOZWOLENIA_NA_LACZENIA")
    @GeneratedValue
    private Integer idPozwoleniaNaLaczenia;
 	
 	@ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_PROWADZACEGO", nullable=false) 
    private Prowadzacy idProwadzacego;

 	@ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_KURSU", nullable=false) 
    private Kursy idKursu;

 	
 	@Column(name="DECYZJA")
    private boolean decyzja;

	//*************************************************************************************************
	//***************************************Getters and Setters***************************************
	//*************************************************************************************************
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
