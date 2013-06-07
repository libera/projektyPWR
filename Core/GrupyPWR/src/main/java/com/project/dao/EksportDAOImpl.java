package com.project.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.data.GrupyProjektowe;
import com.project.data.GrupyZajeciowe;
import com.project.data.Studenci;
import com.project.data.StudenciDoGrupProjektowych;
import com.project.data.StudenciDoGrupZajeciowych;

@Repository
public class EksportDAOImpl implements EksportDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public List<GrupyZajeciowe> getGroupsZaj(int idGrupyZaj) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GrupyZajeciowe where idGrupyZajeciowe=:idGrupyZaj")
				.setInteger("idGrupyZaj", idGrupyZaj).list();
	}

	@Transactional
	public List<StudenciDoGrupZajeciowych> getStudZaj(int idGrupy) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from StudenciDoGrupZajeciowych where idGrupyOryginalnej=:idGrupy")
				.setInteger("idGrupy", idGrupy).list();
	}

	@Transactional
	public List<GrupyProjektowe> getGroupsProj(int idGrupy) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GrupyProjektowe where idGrupyZajeciowe=:idGrupy")
				.setInteger("idGrupy", idGrupy).list();
	}

	@Transactional
	public List<StudenciDoGrupProjektowych> studProj(int idStud, int idGrupy) {

		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from StudenciDoGrupProjektowych where idStudenta=:idStud and idGrupyProjektowej=:idGrupy")
				.setInteger("idStud", idStud).setInteger("idGrupy", idGrupy)
				.list();
	}
	
	@Transactional
	public List<Studenci> getStudents(int idStudent) {
		return sessionFactory.getCurrentSession().createQuery("from Studenci where idStudenci=:idStudent").setInteger("idStudent", idStudent ).list();
	}
}