package com.project.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.data.GrupyZajeciowe;
import com.project.data.Notatki;
import com.project.data.Obecnosc;
import com.project.data.OcenyCzastkowe;
import com.project.data.Spotkania;
import com.project.data.GrupyProjektowe;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;

@Repository
public class AddGroupsDAOImpl implements AddGroupsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<StudenciDoGrupProjektowych> getStudByGroup(int idGrupy) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from StudenciDoGrupProjektowych where idGrupyProjektowej =:idGrupy")
				.setInteger("idGrupy", idGrupy).list();
	}

	@Transactional
	public List<Spotkania> getSpotkaniaByGroup(int idGrupy) {

		return sessionFactory
				.getCurrentSession()
				.createQuery("from Spotkania where idGrupyProjektowej=:idGrupy")
				.setInteger("idGrupy", idGrupy).list();
	}

	@Transactional
	public List<Notatki> getNote(int idProwadzacy, int idGrupy) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Notatki where idProwadzacego=:idProwadzacy and idGrupyProjektowej=:idGrupy")
				.setInteger("idProwadzacy", idProwadzacy)
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
	public List<StudenciDoGrupZajeciowych> getStudGroupZaj(int idStudent) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from StudenciDoGrupZajeciowych where idStudenta =:idStudent")
				.setInteger("idStudent", idStudent).list();
	}

	@Transactional
	public List<Spotkania> getIdSpotkania(int idSpot) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Spotkania where idSpotkania=:idSpot")
				.setInteger("idSpot", idSpot).list();
	}

	@Transactional
	public void updateSpotkania(int idSpotkania, String nazwa, Date data,
			int waga) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String query = "UPDATE Spotkania " + "SET nazwa=:nazwa, "
					+ "dataSpotkania=:data, " + "wagaOceny=:waga "
					+ "WHERE idSpotkania=:idSpotkania";

			sessionFactory.getCurrentSession().createQuery(query)
					.setInteger("idSpotkania", idSpotkania)
					.setString("nazwa", nazwa).setDate("data", data)
					.setInteger("waga", waga).executeUpdate();
			tx.commit();
			/*
			 * String query = "UPDATE OcenyCzastkowe " + "SET ocena=:wart, " +
			 * "dataModyfikacji=:data_mod " + "WHERE idOcenyCzastkowe=:idOcen";
			 * 
			 * sessionFactory.getCurrentSession().createQuery(query)
			 * .setInteger("idOcen", idOcen).setString("wart", wart)
			 * .setDate("data_mod", data_mod).executeUpdate();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public List<OcenyCzastkowe> getidOcen(int idOcen) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from OcenyCzastkowe where idOcenyCzastkowe=:idOcen")
				.setInteger("idOcen", idOcen).list();
	}

	@Transactional
	public void updateOcenki(int idOcen, String wart, Date data_mod) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String query = "UPDATE OcenyCzastkowe " + "SET ocena=:wart, "
					+ "dataModyfikacji=:data_mod "
					+ "WHERE idOcenyCzastkowe=:idOcen";

			sessionFactory.getCurrentSession().createQuery(query)
					.setInteger("idOcen", idOcen).setString("wart", wart)
					.setDate("data_mod", data_mod).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public List<Obecnosc> getIdObec(int idObec) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Obecnosc where idObecnosc=:idObec")
				.setInteger("idObec", idObec).list();
	}

	@Transactional
	public void updateObecnosci(int idObec, boolean stan, Date data_mod) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String query = "UPDATE Obecnosc " + "SET stan=:stan, "
					+ "dataModyfikacji=:data_mod " + "WHERE idObecnosc=:idObec";

			sessionFactory.getCurrentSession().createQuery(query)
					.setInteger("idObec", idObec).setBoolean("stan", stan)
					.setDate("data_mod", data_mod).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public void updateGrupZaj(int idGrupy, String nazwa, String temat,
			String repository, String comment) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String query = "UPDATE GrupyProjektowe " + "SET nazwa=:nazwa, "
					+ "temat=:temat, " + "resositoryLink=:repository, "
					+ "komentarz=:comment "
					+ "WHERE idGrupyProjektowe=:idGrupy";

			sessionFactory.getCurrentSession().createQuery(query)
					.setInteger("idGrupy", idGrupy).setString("nazwa", nazwa)
					.setString("temat", temat)
					.setString("repository", repository)
					.setString("comment", comment).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public void updateStudenci(int idGrupy, int idStudent, String pozycja,
			String ocena) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String query = "UPDATE StudenciDoGrupProjektowych "
					+ "SET stanowiskoW_Grupie=:pozycja, "
					+ "ocena=:ocena "
					+ "WHERE idStudenta=:idStudent and idGrupyProjektowej=:idGrupy";
			sessionFactory.getCurrentSession().createQuery(query)
					.setInteger("idGrupy", idGrupy)
					.setInteger("idStudent", idStudent)
					.setString("pozycja", pozycja).setString("ocena", ocena)
					.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public void deleteGroupProj(int idGrupyZaj) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String query = "delete from grupy_projektowe "
					+ "where idGrupy_projektowe =:idGrupyZaj";

			sessionFactory.getCurrentSession().createSQLQuery(query)
					.setInteger("idGrupyZaj", idGrupyZaj).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public void deleteGroupZaj(int idGrupZaj) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String query = "delete from grupy_zajeciowe "
					+ "where idGrupy_zajeciowe =:idGrupZaj";

			sessionFactory.getCurrentSession().createSQLQuery(query)
					.setInteger("idGrupZaj", idGrupZaj).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public void deleteStudents(Integer idStudent) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String query = " delete from studenci_do_grup_projektowych "
					+ "where id_studenta =:idStudent";

			sessionFactory.getCurrentSession().createSQLQuery(query)
					.setInteger("idStudent", idStudent).executeUpdate();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
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
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public void addSpotkania(Integer idGrupyProj, Date dataSpotkania,
			String nazwa, Integer waga) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String query = "insert into spotkania (" + "idGrupyProjektowej, "
					+ "dataSpotkania, " + "Nazwa, " + "WagaOceny)"
					+ "values(:idGrupyProj, :dataSpotkania, :nazwa, :waga)";

			sessionFactory.getCurrentSession().createSQLQuery(query)
					.setInteger("idGrupyProj", idGrupyProj)
					.setDate("dataSpotkania", dataSpotkania)
					.setString("nazwa", nazwa).setInteger("waga", waga)
					.executeUpdate();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Transactional
	public void addNote(Integer idProwadzacy, Integer idGrupy, Integer plik,
			String tresc, Date dataDodania, Date dataModyfikacji) {
		Session session = null;
		Transaction tx = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String query = "insert into notatki ("
					+ "id_grupy_projektowej, "
					+ "Tresc, "
					+ "plik, "
					+ "data_dodania, "
					+ "data_modyfikacji, "
					+ "id_Prowadzacego) "
					+ "values(:idGrupy, :tresc, :plik, :dataDodania, :dataModyfikacji, :idProwadzacy)";

			sessionFactory.getCurrentSession().createSQLQuery(query)
					.setInteger("idProwadzacy", idProwadzacy)
					.setInteger("idGrupy", idGrupy).setInteger("plik", plik)
					.setDate("dataDodania", dataDodania)
					.setDate("dataModyfikacji", dataModyfikacji)
					.setString("tresc", tresc).executeUpdate();

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
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
	public List<GrupyZajeciowe> getGroupZaj(int idGrupy) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GrupyZajeciowe where idGrupyZajeciowe=:idGrupy")
				.setInteger("idGrupy", idGrupy).list();
	}

	@Transactional
	public List<StudenciDoGrupProjektowych> getStudentsProj(int idStudent,
			int idGroup) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from StudenciDoGrupProjektowych where idStudenta=:idStudent and idGrupyProjektowej =:idGroup")
				.setInteger("idStudent", idStudent)
				.setInteger("idGroup", idGroup).list();
	}

	@Transactional
	public List<Spotkania> getSpotByGroupId(int idGrupyProj) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Spotkania where idGrupyProjektowej=:idGrupyProj")
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
