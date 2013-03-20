package com.project.data;

import java.util.Date;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PLIKI")
public class Pliki {


	//****************************************************************************************
	//***************************************Attributes***************************************
	//****************************************************************************************
	@Id
    @Column(name="IDPLIKI")
    @GeneratedValue
    private Integer idPliku;
	
	@Column(name="NAZWA")
	private String nazwa;
	
	@Column(name="DATA")
	private Blob data;
	
	@Column(name="OPIS")
	private String opis;
	
	@Column(name="DATA_DODANIA", columnDefinition="DATETIME")
    private Date dataDodania;
	
	@Column(name="DATA_MODYFIKACJI", columnDefinition="DATETIME")
    private Date dataModyfikacji;

	//*************************************************************************************************
	//***************************************Getters and Setters***************************************
	//*************************************************************************************************
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
