package com.project.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="KURSY")
public class Kursy implements Serializable {
	
	
		//****************************************************************************************
		//***************************************Attributes***************************************
		//****************************************************************************************

	 	/**
	 * 
	 */
	private static final long serialVersionUID = -2485914145322350750L;

		@Id
	    @Column(name="IDKURSY")
	    @GeneratedValue
	    private Integer idKursy;
	 	
	 	@Column(name="KOD_KURSU")
	    private String kodKursu;
	 	
	 	@Column(name="NAZWA_KURSU")
	    private String nazwaKursu;
		
	 	//*************************************************************************************************
	 	//***************************************Getters and Setters***************************************
	 	//*************************************************************************************************

	
		public Integer getIdKursy() {
			return idKursy;
		}

		public void setIdKursy(Integer idKursy) {
			this.idKursy = idKursy;
		}

		public String getKodKursu() {
			return kodKursu;
		}

		public void setKodKursu(String kodKursu) {
			this.kodKursu = kodKursu;
		}

		public String getNazwaKursu() {
			return nazwaKursu;
		}

		public void setNazwaKursu(String nazwaKursu) {
			this.nazwaKursu = nazwaKursu;
		}
	 
	 
	 	
	 	
}