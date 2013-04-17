package com.project.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "laczenie_notatek_i_plikow")
public class LaczenieNotatekIPlikow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5139120564639562149L;

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************
	@Id
	@Column(name = "idlaczenie_notatek_i_plikow")
	@GeneratedValue
	private Integer idLaczenieNoPl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_notatki", nullable = false)
	private Notatki idNotatki;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_pliku", nullable = false)
	private Pliki idPliku;

	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************
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
