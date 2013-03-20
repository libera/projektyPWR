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
@Table(name="STUDENCI_DO_GRUP_PROJEKTOWYCH")
public class StudenciDoGrupProjektowych {


	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="IDSTUDENCI_DO_GRUP_PROJEKTOWYCH")
    @GeneratedValue
    private Integer idStudenciDoProjektu;
	
	
	@OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_STUDENTA", nullable=false) 
    private Studenci idStudenta;
 	
 	@OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name="ID_GRUPY_PROJEKTOWEJ", nullable=false) 
    private GrupyProjektowe idGrupyProjektowej;
 	
	@Column(name="STANOWISKO_W_GRUPIE")
	private String stanowiskoW_Grupie;
	
	@Column(name="Ocena")
	private String ocena;
	
	@Column(name="KOMENTARZ")
	private String komentarz;
	
	//*************************************************************************************************
	//***************************************Getters and Setters***************************************
	//*************************************************************************************************
	public Integer getIdStudenciDoProjektu() {
		return idStudenciDoProjektu;
	}

	public void setIdStudenciDoProjektu(Integer idStudenciDoProjektu) {
		this.idStudenciDoProjektu = idStudenciDoProjektu;
	}

	public Studenci getIdStudenta() {
		return idStudenta;
	}

	public void setIdStudenta(Studenci idStudenta) {
		this.idStudenta = idStudenta;
	}

	public GrupyProjektowe getIdGrupyProjektowej() {
		return idGrupyProjektowej;
	}

	public void setIdGrupyProjektowej(GrupyProjektowe idGrupyProjektowej) {
		this.idGrupyProjektowej = idGrupyProjektowej;
	}

	public String getStanowiskoW_Grupie() {
		return stanowiskoW_Grupie;
	}

	public void setStanowiskoW_Grupie(String stanowiskoW_Grupie) {
		this.stanowiskoW_Grupie = stanowiskoW_Grupie;
	}

	public String getOcena() {
		return ocena;
	}

	public void setOcena(String ocena) {
		this.ocena = ocena;
	}

	public String getKomentarz() {
		return komentarz;
	}

	public void setKomentarz(String komentarz) {
		this.komentarz = komentarz;
	}

}
