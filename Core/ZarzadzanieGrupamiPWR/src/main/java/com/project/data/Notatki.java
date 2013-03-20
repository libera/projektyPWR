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
@Table(name="NOTATKI")
public class Notatki {
	
		//****************************************************************************************
		//***************************************Attributes***************************************
		//****************************************************************************************
	 	@Id
	    @Column(name="IDNOTATKI")
	    @GeneratedValue
	    private Integer idNotatki;
	    
	 	@OneToMany(fetch = FetchType.EAGER) 
	    @JoinColumn(name="IDGRUPY_PROJEKTOWE", nullable=false)
	    private GrupyProjektowe idGrupyProjektowej;
	    
	    @Column(name="TRESC")
	    private String tresc;
	    
	    @Column(name="PLIK")
	    private Integer plik;
	    
	 	@Column(name="DATA_DODANIA", columnDefinition="DATETIME")
	    private Date dataDodania;

	 	@Column(name="DATA_MODYFIKACJI", columnDefinition="DATETIME")
	    private Date dataModyfikacji;
	 	
	    @Column(name="ID_PROWADZACEGO")
	    private Integer idProwadzacego;
	    
	    @Column(name="ID_STUDENTA")
	    private Integer idStudenta;
	    
	    //*************************************************************************************************
	 	//***************************************Getters and Setters***************************************
	 	//*************************************************************************************************
		public Integer getIdNotatki() {
			return idNotatki;
		}

		public void setIdNotatki(Integer idNotatki) {
			this.idNotatki = idNotatki;
		}

		public GrupyProjektowe getIdGrupyProjektowej() {
			return idGrupyProjektowej;
		}

		public void setIdGrupyProjektowej(GrupyProjektowe idGrupyProjektowej) {
			this.idGrupyProjektowej = idGrupyProjektowej;
		}

		public String getTresc() {
			return tresc;
		}

		public void setTresc(String tresc) {
			this.tresc = tresc;
		}

		public Integer getPlik() {
			return plik;
		}

		public void setPlik(Integer plik) {
			this.plik = plik;
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

		public Integer getIdProwadzacego() {
			return idProwadzacego;
		}

		public void setIdProwadzacego(Integer idProwadzacego) {
			this.idProwadzacego = idProwadzacego;
		}

		public Integer getIdStudenta() {
			return idStudenta;
		}

		public void setIdStudenta(Integer idStudenta) {
			this.idStudenta = idStudenta;
		}
		
}