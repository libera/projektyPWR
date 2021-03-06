package com.project.dao;

import java.util.List;

import org.apache.maven.artifact.versioning.Restriction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.annotation.Transactional;

import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;
import com.project.data.Prowadzacy;

@Repository
public class PobierzGrupyDAOImpl implements PobierzGrupyDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<Kursy> pobierzKursy(int idKursu) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Kursy where idKursy=:idKursu")
				.setInteger("idKursu", idKursu).list();
	}

	@Transactional
	public List<GrupyZajeciowe> pobierzGrupyZajeciowe(int idProwadzacego) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GrupyZajeciowe where idProwadzacego =:idProwadzacego")
				.setInteger("idProwadzacego", idProwadzacego).list();
	}

	@Transactional
	public List<GrupyProjektowe> pobierzGrupke(int idGrupyZajeciowe) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GrupyProjektowe where idGrupyZajeciowe =:idGrupyZajeciowe")
				.setInteger("idGrupyZajeciowe", idGrupyZajeciowe).list();
	}

	@Transactional
	public void addGrupyProj(int idGrupyZajeciowe, String nazwa, String temat,
			String resositoryLink, String komentarz) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String query = "insert ignore into Grupy_projektowe(Id_grupy_zajeciowej,nazwa,temat,resository_link,komentarz) values(:idGrupyZajeciowe,:nazwa,:temat,:resositoryLink,:komentarz)";

		sessionFactory.getCurrentSession().createSQLQuery(query)
				.setParameter("idGrupyZajeciowe", idGrupyZajeciowe)
				.setParameter("nazwa", nazwa).setParameter("temat", temat)
				.setParameter("resositoryLink", resositoryLink)
				.setParameter("komentarz", komentarz).executeUpdate();

		tx.commit();
		session.close();
	}

}
