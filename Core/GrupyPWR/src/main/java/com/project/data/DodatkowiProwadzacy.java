package com.project.data;



import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table(name="DODATKOWI_PROWADZACY")
public class DodatkowiProwadzacy implements Serializable {
	
		//****************************************************************************************
		//***************************************Attributes***************************************
		//****************************************************************************************

	 	/**
	 * 
	 */
	private static final long serialVersionUID = 8899988495420534651L;

		@Id
	    @Column(name="IDDODATKOWI_PROWADZACY")
	    @GeneratedValue
	    private Integer idDodatkowiProwadzacy;
	    
	 	@ManyToOne(fetch = FetchType.EAGER) 
	    @JoinColumn(name="ID_PROWADZACEGO", nullable=false)
	    private Prowadzacy id_Prowadzacego;
	 
	 	@ManyToOne(fetch = FetchType.EAGER) 
	    @JoinColumn(name="ID_GRUPY", nullable=false)
	    private GrupyZajeciowe idGrupy;
	    
	    @Column(name="KOMENTARZ")
	    private String komentarz;

		
	 	//*************************************************************************************************
	 	//***************************************Getters and Setters***************************************
	 	//*************************************************************************************************

		public Integer getIdDodatkowiProwadzacy() {
			return idDodatkowiProwadzacy;
		}

		public void setIdDodatkowiProwadzacy(Integer idDodatkowiProwadzacy) {
			this.idDodatkowiProwadzacy = idDodatkowiProwadzacy;
		}

		public Prowadzacy getId_Prowadzacego() {
			return id_Prowadzacego;
		}

		public void setId_Prowadzacego(Prowadzacy id_Prowadzacego) {
			this.id_Prowadzacego = id_Prowadzacego;
		}

		public GrupyZajeciowe getIdGrupy() {
			return idGrupy;
		}

		public void setIdGrupy(GrupyZajeciowe idGrupy) {
			this.idGrupy = idGrupy;
		}

		public String getKomentarz() {
			return komentarz;
		}

		public void setKomentarz(String komentarz) {
			this.komentarz = komentarz;
		}

		
	    
	   
}	