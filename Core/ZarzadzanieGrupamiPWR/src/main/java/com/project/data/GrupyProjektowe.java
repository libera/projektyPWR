package com.project.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@Table(name="GRUPY_PROJEKTOWE")
public class GrupyProjektowe {

			//****************************************************************************************
			//***************************************Attributes***************************************
			//****************************************************************************************
		 	@Id
		    @Column(name="IDGRUPY_PROJEKTOWE")
		    @GeneratedValue
		    private Integer idGrupyProjektowe;
		    
		 	@OneToMany(fetch = FetchType.EAGER) 
		    @JoinColumn(name="IDGRUPY_ZAJECIOWE", nullable=false)
		    private GrupyZajeciowe idGrupyZajeciowe;
		 
		 	 
		    @Column(name="NAZWA")
		    private String nazwa;
		    
		    @Column(name="TEMAT")
		    private String temat;
		    
		    @Column(name="RESOSITORY_LINK")
		    private String resositoryLink;
		    
		    @Column(name="KOMENTARZ")
		    private String komentarz;
		    
		    @Column(name="OCENA")
		    private String ocena;
		    
		 	//*************************************************************************************************
		 	//***************************************Getters and Setters***************************************
		 	//*************************************************************************************************
			public Integer getIdGrupyProjektowe() {
				return idGrupyProjektowe;
			}

			public void setIdGrupyProjektowe(Integer idGrupyProjektowe) {
				this.idGrupyProjektowe = idGrupyProjektowe;
			}

			public GrupyZajeciowe getIdGrupyZajeciowe() {
				return idGrupyZajeciowe;
			}

			public void setIdGrupyZajeciowe(GrupyZajeciowe idGrupyZajeciowe) {
				this.idGrupyZajeciowe = idGrupyZajeciowe;
			}

			public String getNazwa() {
				return nazwa;
			}

			public void setNazwa(String nazwa) {
				this.nazwa = nazwa;
			}

			public String getTemat() {
				return temat;
			}

			public void setTemat(String temat) {
				this.temat = temat;
			}

			public String getResositoryLink() {
				return resositoryLink;
			}

			public void setResositoryLink(String resositoryLink) {
				this.resositoryLink = resositoryLink;
			}

			public String getKomentarz() {
				return komentarz;
			}

			public void setKomentarz(String komentarz) {
				this.komentarz = komentarz;
			}

			public String getOcena() {
				return ocena;
			}

			public void setOcena(String ocena) {
				this.ocena = ocena;
			}

}
