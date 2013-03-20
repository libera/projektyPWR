package com.project.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="LACZENIE_NOTATEK_I_PLIKOW")
public class LaczenieNotatekIPlikow {


	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
 	@Id
    @Column(name="IDLACZENIE_NOTATEK_I_PLIKOW")
    @GeneratedValue
    private Integer idLaczenieNoPl;
 	
 	@OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_NOTATKI", nullable=false)
    private Notatki idNotatki;
 	
 	@OneToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_PLIKU", nullable=false)
    private Pliki idPliku;
 	
 	//*************************************************************************************************
 	//***************************************Getters and Setters***************************************
 	//*************************************************************************************************
	public Integer getIdLaczenieNoPl() {
		return idLaczenieNoPl;
	}

	public void setIdLaczenieNoPl(Integer idLaczenieNoPl) {
		this.idLaczenieNoPl = idLaczenieNoPl;
	}

	public Notatki getIdNotatki() {
		return idNotatki;
	}

	public void setIdNotatki(Notatki idNotatki) {
		this.idNotatki = idNotatki;
	}

	public Pliki getIdPliku() {
		return idPliku;
	}

	public void setIdPliku(Pliki idPliku) {
		this.idPliku = idPliku;
	}

}
