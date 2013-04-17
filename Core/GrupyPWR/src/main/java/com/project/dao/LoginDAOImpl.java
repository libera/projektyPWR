package com.project.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.data.GrupyZajeciowe;
import com.project.data.Kursy;
import com.project.data.Prowadzacy;
import com.project.data.Studenci;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addProwadzacy(Prowadzacy prowadzacy) {
		sessionFactory.getCurrentSession().save(prowadzacy);

	}

	public List<Kursy> listKursy() {

		return sessionFactory.getCurrentSession().createQuery("from kursy")
				.list();

	}

	public List<GrupyZajeciowe> listGrupyZajeciowe() {
		return sessionFactory.getCurrentSession()
				.createQuery("from grupy_zajeciowe").list();

	}

	public List<Studenci> listStudenci() {
		return sessionFactory.getCurrentSession().createQuery("from studenci")
				.list();

	}

	public List<Prowadzacy> listProwadzacy() {

		return sessionFactory.getCurrentSession()
				.createQuery("from Prowadzacy").list();
	}

	public List<Prowadzacy> validateLogin(String login, String haslo) {

		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Prowadzacy where login=:login and haslo=:haslo")
				.setString("login", login).setString("haslo", haslo).list();

	}

	public List<Kursy> validateKursy(String kod_kursu, String nazwa_kursu) {

		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from kursy where kod_kursu=:kod_kursu and nazwa_kursu=:nazwa_kursu")
				.setString("kod_kursu", kod_kursu)
				.setString("nazwa_kursu", nazwa_kursu).list();

	}

	public List<Prowadzacy> validatePname(String imiona, String nazwisko) {

		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from prowadzacy where imiona=:imiona and nazwisko=:nazwisko")
				.setString("imiona", imiona).setString("nazwisko", nazwisko)
				.list();

	}

	public List<GrupyZajeciowe> validateGrupyza(String kod_grupy) {

		return sessionFactory.getCurrentSession()
				.createQuery("from grupy_zajeciowe where kod_grupy=:kod_grupy")
				.setString("kod_grupy", kod_grupy).list();

	}

	public List<Studenci> validateSname(String nr_indeksu) {

		return sessionFactory.getCurrentSession()
				.createQuery("from studenci where nr_indeksu=:nr_indeksu")
				.setString("nr_indeksu", nr_indeksu).list();

	}

	public void aktywacja(String Login, boolean Aktywowany) {
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE Prowadzacy SET Aktywowany=:Aktywowany WHERE Login=:Login")
				.setString("Login", Login).setBoolean("Aktywowany", Aktywowany)
				.executeUpdate();
	}

	public List<Prowadzacy> validateRegister(String imiona, String nazwisko,
			String email, String login) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from Prowadzacy where imiona=:imiona and nazwisko=:nazwisko and email=:email and login=:login")
				.setString("imiona", imiona).setString("nazwisko", nazwisko)
				.setString("email", email).setString("login", login).list();
	}

	public void addKursy(String kodkursu, String nazwakursu) {

		String query = "insert into kursy(kodkursu,nazwakursu) values(:kodkursu,:nazwakursu)";

		sessionFactory.getCurrentSession().createSQLQuery(query)
				.setParameter("kodkursu", kodkursu)
				.setParameter("nazwakursu", nazwakursu).executeUpdate();

	}

	public void addGrupyZajeciowe(String kodGrupy, String infoEdu,
			Integer idProwadzacego, Integer idKursu, String nazwa,
			String termin, String komentarz) {

		String query = "insert ignore into grupy_zajeciowe(kodGrupy,infoEdu,idProwadzacego, idKursu, nazwa, termin, komentarz) values(:kodGrupy,:infoEdu,:idProwadzacego,:idKursu,:nazwa,:termin,:komentarz)";

		sessionFactory.getCurrentSession().createSQLQuery(query)
				.setParameter("kodGrupy", kodGrupy)
				.setParameter("infoEdu", infoEdu)
				.setParameter("idProwadzacego", idProwadzacego)
				.setParameter("idKursu", idKursu).setParameter("nazwa", nazwa)
				.setParameter("termin", termin)
				.setParameter("komentarz", komentarz).executeUpdate();

	}

	public void addStudenciDoGrupZajeciowych(Integer idStudenta,
			Integer idGrupyOryginalnej, Integer idGrupyChodzacej) {

		String query = "insert ignore into kursy(idStudenta,idGrupyOryginalnej,idGrupyChodzacej) values(:idStudenta,:idGrupyOryginalnej,:idGrupyChodzacej)";

		sessionFactory.getCurrentSession().createSQLQuery(query)
				.setParameter("idStudenta", idStudenta)
				.setParameter("idGrupyOryginalnej", idGrupyOryginalnej)
				.setParameter("idGrupyChodzacej", idGrupyOryginalnej)
				.executeUpdate();

	}

	public void addStudenci(String imie, String nazwisko, String nrIndeksu,
			String email, Integer rok, Integer semestr, String przedmiot,
			String login, String haslo) {

		String query = "insert into studenci(imie,nazwisko,nrIndeksu, email, rok, semestr, przedmiot, login, haslo) values(:imie,:nazwisko,:nrIndeksu,:email,:rok,:semestr,:przedmiot,:login,:haslo)";

		sessionFactory.getCurrentSession().createSQLQuery(query)
				.setParameter("imie", imie).setParameter("nazwisko", nazwisko)
				.setParameter("nrIndeksu", nrIndeksu)
				.setParameter("email", email).setParameter("rok", rok)
				.setParameter("semestr", semestr)
				.setParameter("przedmiot", przedmiot)
				.setParameter("login", login).setParameter("haslo", haslo)
				.executeUpdate();

	}
}