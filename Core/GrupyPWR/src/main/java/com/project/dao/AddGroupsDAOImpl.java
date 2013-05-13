package com.project.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Spotkania;
import com.project.data.GrupyProjektowe;
import com.project.data.StudenciDoGrupProjektowych;

@Repository
public class AddGroupsDAOImpl implements AddGroupsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<Spotkania> getSpotkaniaByGroup(int idGrupy) {

		return sessionFactory
				.getCurrentSession()
				.createQuery("from Spotkania where idGrupyProjektowej=:idGrupy")
				.setInteger("idGrupy", idGrupy).list();
	}

	@Transactional
	public List<GrupyProjektowe> getIdGrupZaj(int idGrupyProj) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GrupyProjektowe where idGrupyProjektowe=:idGrupyProj")
				.setInteger("idGrupyProj", idGrupyProj).list();
	}

	@Transactional
	public void updateStudZaj(int idStud, int idGrupyStatic, int idGrupyChange) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE StudenciDoGrupZajeciowych SET idGrupyChodzacej=:idGrupyChange WHERE idStudenta=:idStud and idGrupyOryginalnej=:idGrupyStatic")
				.setInteger("idStud", idStud)
				.setInteger("idGrupyStatic", idGrupyStatic)
				.setInteger("idGrupyChange", idGrupyChange).executeUpdate();
		tx.commit();
		session.close();
	}

	@Transactional
	public void addStudents(Integer idStudent, Integer idGrupZaj,
			String stanowisko, String ocena, String komentarz) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String query = "insert into studenci_do_grup_projektowych("
					+ "id_studenta, "
					+ "id_grupy_projektowej, "
					+ "Stanowisko_w_grupie, "
					+ "Ocena, "
					+ "Komentarz)"
					+ "values(:idStudent, :idGrupZaj, :stanowisko, :ocena, :komentarz)";

			sessionFactory.getCurrentSession().createSQLQuery(query)
					.setInteger("idStudent", idStudent)
					.setInteger("idGrupZaj", idGrupZaj)
					.setString("stanowisko", stanowisko)
					.setString("ocena", ocena)
					.setString("komentarz", komentarz).executeUpdate();
			tx.commit();
		} catch (Exception e) {

		} finally {
			session.close();
		}
	}
	
	@Transactional
	public void addSpotkania(Integer idGrupyProj, Date dataSpotkania, String nazwa, Integer waga) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String query = "insert into spotkania ("
					+ "idGrupyProjektowej, "
					+ "dataSpotkania, "
					+ "Nazwa, "
					+ "WagaOceny)"
					+ "values(:idGrupyProj, :dataSpotkania, :nazwa, :waga)";
			
			sessionFactory.getCurrentSession().createSQLQuery(query)
					.setInteger("idGrupyProj", idGrupyProj)
					.setDate("dataSpotkania", dataSpotkania)
					.setString("nazwa", nazwa)
					.setInteger("waga", waga).executeUpdate();
			
			tx.commit();
		} catch(Exception e) {
			
		} finally {
			session.close();
		}
	}

	@Transactional
	public List<OcenyCzastkowe> getOcenki(int idStudenta, int idSpotkania) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from OcenyCzastkowe where idStudenta=:idStudenta and idSpotkania =:idSpotkania")
				.setInteger("idStudenta", idStudenta)
				.setInteger("idSpotkania", idSpotkania).list();
	}
	
	@Transactional
	public List<Spotkania> getSpotByGroupId(int idGrupyProj) {
		return sessionFactory
				.getCurrentSession()
				.createQuery("from Spotkania where idGrupyProjektowej=:idGrupyProj")
				.setInteger("idGrupyProj", idGrupyProj).list();
	}

	@Transactional
	public List<Obecnosc> getObecnosc(int idStudenta, int idSpotkania) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Obecnosc where idStudenta=:idStudenta and isSpotkania =:idSpotkania")
				.setInteger("idStudenta", idStudenta)
				.setInteger("idSpotkania", idSpotkania).list();
	}
	
	@Transactional
	public List<StudenciDoGrupProjektowych> getStudent(int idStudent) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from StudenciDoGrupProjektowych where idStudenta=:idStudent")
				.setInteger("idStudent", idStudent).list();
	}

}
