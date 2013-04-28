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
import javax.persistence.ManyToOne;

@Entity
@Table(name = "spotkania")
public class Spotkania implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1766364851128075382L;

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************
	@Id
	@Column(name = "idspotkania")
	@GeneratedValue
	private Integer idSpotkania;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idgrupyprojektowej", nullable = false)
	private GrupyProjektowe idGrupyProjektowej;

	@Column(name = "dataspotkania")
	private Date dataSpotkania;

	@Column(name = "nazwa")
	private String nazwa;

	@Column(name = "wagaoceny")
	private Integer wagaOceny;


	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************

	
	public Integer getIdSpotkania() {
		return idSpotkania;
	}

	public void setIdSpotkania(Integer idSpotkania) {
		this.idSpotkania = idSpotkania;
	}

	public GrupyProjektowe getIdGrupyProjektowej() {
		return idGrupyProjektowej;
	}

	public void setIdGrupyProjektowej(GrupyProjektowe idGrupyProjektowej) {
		this.idGrupyProjektowej = idGrupyProjektowej;
	}

	public Date getDataSpotkania() {
		return dataSpotkania;
	}

	public void setDataSpotkania(Date dataSpotkania) {
		this.dataSpotkania = dataSpotkania;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Integer getWagaOceny() {
		return wagaOceny;
	}

	public void setWagaOceny(Integer wagaOceny) {
		this.wagaOceny = wagaOceny;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
