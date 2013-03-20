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
@Table(name="OCENY_CZASTKOWE")
public class OcenyCzastkowe {


	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="IDOCENY_CZASTKOWE")
    @GeneratedValue
    private Integer idOcenyCzastkowe;
	
	@Column(name="OCENA")
	private String ocena;
	
	@Column(name="NAZWA")
	private String nazwa;
	
	@Column(name="WAGA")
	private Integer waga;
	
	@Column(name="DATA_DODANIA", columnDefinition="DATETIME")
    private Date dataDodania;
	
	@Column(name="DATA_MODYFIKACJI", columnDefinition="DATETIME")
    private Date dataModyfikacji;
	
 	@OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_STUDENTA", nullable=false) 
    private Studenci idStudenta;
 	
 	@OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_GRUPY_ZAJECIOWEJ", nullable=false) 
    private GrupyZajeciowe idGrupyZajeciowej;
	//*************************************************************************************************
	//***************************************Getters and Setters***************************************
	//*************************************************************************************************

	public Integer getIdOcenyCzastkowe() {
		return idOcenyCzastkowe;
	}

	public void setIdOcenyCzastkowe(Integer idOcenyCzastkowe) {
		this.idOcenyCzastkowe = idOcenyCzastkowe;
	}

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Integer getWaga() {
		return waga;
	}

	public void setWaga(Integer waga) {
		this.waga = waga;
	}

	public Date getDataDodania() {
		return dataDodania;
	}

	public void setDataDodania(Date dataDodania) {
		this.dataDodania = dataDodania;
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

	public GrupyZajeciowe getIdGrupyZajeciowej() {
		return idGrupyZajeciowej;
	}

	public void setIdGrupyZajeciowej(GrupyZajeciowe idGrupyZajeciowej) {
		this.idGrupyZajeciowej = idGrupyZajeciowej;
	}
	 		
}
