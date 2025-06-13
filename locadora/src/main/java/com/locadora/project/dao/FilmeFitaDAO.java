package com.locadora.project.dao;

import com.locadora.project.dao.GenericDAO;
import com.locadora.project.model.FilmeFita;
import com.locadora.project.model.Filme;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FilmeFitaDAO extends GenericDAO<FilmeFita, Long> {
	public FilmeFitaDAO() {
		super(FilmeFita.class);
	}
	
	@Override
	public void remove(FilmeFita filmeFita) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			FilmeFita filmeFitaManaged = session.get(FilmeFita.class, filmeFita.getCodFilmeFita());
			
			filmeFitaManaged.getFita().setFilmeFita(null);
			filmeFitaManaged.getFilme().getFilmeFita().remove(filmeFitaManaged);
			
			session.remove(filmeFitaManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[FilmeFitaDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}