package com.project.data;

import java.util.Date;
import java.io.Serializable;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pliki")
public class Pliki implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8211021558785463402L;

	// ****************************************************************************************
	// ***************************************Attributes***************************************
	// ****************************************************************************************
	@Id
	@Column(name = "idpliki")
	@GeneratedValue
	private Integer idPliku;

	@Column(name = "nazwa")
	private String nazwa;

	@Column(name = "data")
	private Blob data;

	@Column(name = "opis")
	private String opis;

	@Column(name = "data_dodania", columnDefinition = "DATETIME")
	private Date dataDodania;

	@Column(name = "data_modyfikacji", columnDefinition = "DATETIME")
	private Date dataModyfikacji;

	// *************************************************************************************************
	// ***************************************Getters and
	// Setters***************************************
	// *************************************************************************************************
	public Integer getIdPliku() {
		return idPliku;
	}

	public void setIdPliku(Integer idPliku) {
		this.idPliku = idPliku;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public Blob getData() {
		return data;
	}

	public void setData(Blob data) {
		this.data = data;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
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

}
