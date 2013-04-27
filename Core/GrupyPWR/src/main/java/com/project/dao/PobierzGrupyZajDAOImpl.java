package com.project.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.data.GrupyZajeciowe;


@Repository
public class PobierzGrupyZajDAOImpl implements PobierzGrupyZajDAO  {

	

		@Autowired
		private SessionFactory sessionFactory;
		
		@Transactional
		public List<GrupyZajeciowe> pobierzGrupyZajeciowe(int idGrupy) {
			return sessionFactory
					.getCurrentSession()
					.createQuery(
							"from GrupyZajeciowe where idGrupyZajeciowe =:idGrupy")
					.setInteger("idGrupy", idGrupy).list();
		}

}
