package com.project.data;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="STUDENCI_DO_GRUP_ZAJECIOWYCH")
public class StudenciDoGrupZajeciowych implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7742740472186425750L;


	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="IDSTUDENCI_DO_GRUP_ZAJECIOWYCH")
    @GeneratedValue
    private Integer idStudenciDoGrupZajeciowych;
 	
	
	@ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_STUDENTA", nullable=false) 
    private Studenci idStudenta;
 
	@ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_GRUPY_ORYGINLNEJ", nullable=false) 
    private GrupyZajeciowe idGrupyOryginalnej;
 
	@ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_GRUPY_CHODZACEJ", nullable=false) 
    private GrupyZajeciowe idGrupyChodzacej;
	
 	//*************************************************************************************************
	//***************************************Getters and Setters***************************************
	//*************************************************************************************************
	public Integer getIdStudenciDoGrupZajeciowych() {
		return idStudenciDoGrupZajeciowych;
	}

	public void setIdStudenciDoGrupZajeciowych(Integer idStudenciDoGrupZajeciowych) {
		this.idStudenciDoGrupZajeciowych = idStudenciDoGrupZajeciowych;
	}

	public Studenci getIdStudenta() {
		return idStudenta;
	}

	public void setIdStudenta(Studenci idStudenta) {
		this.idStudenta = idStudenta;
	}

	public GrupyZajeciowe getIdGrupyOryginalnej() {
		return idGrupyOryginalnej;
	}

	public void setIdGrupyOryginalnej(GrupyZajeciowe idGrupyOryginalnej) {
		this.idGrupyOryginalnej = idGrupyOryginalnej;
	}

	public GrupyZajeciowe getIdGrupyChodzacej() {
		return idGrupyChodzacej;
	}

	public void setIdGrupyChodzacej(GrupyZajeciowe idGrupyChodzacej) {
		this.idGrupyChodzacej = idGrupyChodzacej;
	}

 	
}
