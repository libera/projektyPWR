package com.project.data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="STUDENCI_DO_GRUP_ZAJECIOWYCH")
public class StudenciDoGrupZajeciowych {
	
	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="IDSTUDENCI_DO_GRUP_ZAJECIOWYCH")
    @GeneratedValue
    private Integer idStudenciDoGrupZajeciowych;
 	
	
	@OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name="IDSTUDENCI", nullable=false) 
    private Studenci idStudenta;
 
	@OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name="IDGRUPY_ZAJECIOWE", nullable=false) 
    private GrupyZajeciowe idGrupyOryginalnej;
 
	@OneToMany(fetch = FetchType.EAGER) 
    @JoinColumn(name="IDGRUPY_ZAJECIOWE", nullable=false) 
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
