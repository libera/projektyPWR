package com.project.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

	
@Entity
@Table(name="OBECNOSC")
public class Obecnosc {

	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="IDOBECNOSC")
    @GeneratedValue
    private Integer idObecnosc;
 	
 	@Column(name="STAN")
    private boolean stan;
 	
 	@Column(name="NAZWA")
    private String nazwa;
 	
 	@Column(name="DATA_WPROWADZENIA", columnDefinition="DATETIME")
    private Date dataWprowadzenia;
 	
 	@Column(name="DATA_MODYFIKACJI", columnDefinition="DATETIME")
    private Date dataModyfikacji;
 	
 	@ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_STUDENTA", nullable=false) 
    private Studenci idStudenta;
 	
 	@ManyToOne(fetch = FetchType.EAGER) 	
    @JoinColumn(name="IS_SPOTKANIA", nullable=false)
    private Spotkania isSpotkania;
 	
	//*************************************************************************************************
 	//***************************************Getters and Setters***************************************
 	//*************************************************************************************************
 		
	
	public Integer getIdObecnosc() {
		return idObecnosc;
	}

	public void setIdObecnosc(Integer idObecnosc) {
		this.idObecnosc = idObecnosc;
	}

	public boolean isStan() {
		return stan;
	}

	public void setStan(boolean stan) {
		this.stan = stan;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Date getDataWprowadzenia() {
		return dataWprowadzenia;
	}

	public void setDataWprowadzenia(Date dataWprowadzenia) {
		this.dataWprowadzenia = dataWprowadzenia;
	}

	public Date getDataModyfikacji() {
		return dataModyfikacji;
	}

	public void setDataModyfikacji(Date dataModyfikacji) {
		this.dataModyfikacji = dataModyfikacji;
	}

	public Studenci getIdStudenta() {
		return idStudenta;
	}

	public void setIdStudenta(Studenci idStudenta) {
		this.idStudenta = idStudenta;
	}

	public Spotkania getIsSpotkania() {
		return isSpotkania;
	}

	public void setIsSpotkania(Spotkania isSpotkania) {
		this.isSpotkania = isSpotkania;
	}

 
 		
 	

}
