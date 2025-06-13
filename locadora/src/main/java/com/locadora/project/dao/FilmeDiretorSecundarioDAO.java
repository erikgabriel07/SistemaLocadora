package com.locadora.project.dao;

import com.locadora.project.dao.GenericDAO;
import com.locadora.project.model.FilmeDiretorSecundario;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FilmeDiretorSecundarioDAO extends GenericDAO<FilmeDiretorSecundario, Long> {
	public FilmeDiretorSecundarioDAO() {
		super(FilmeDiretorSecundario.class);
	}
	
	@Override
	public void remove(FilmeDiretorSecundario filmeDiretorSecundario) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			FilmeDiretorSecundario filDirSecManaged = session.get(FilmeDiretorSecundario.class, filmeDiretorSecundario.getCodFilmeDiretorSecundario());
			
			filDirSecManaged.getFilme().getFilmeDiretorSecundario().remove(filDirSecManaged);
			filDirSecManaged.getDiretorSecundario().getFilmeDiretorSecundario().remove(filDirSecManaged);
			
			filDirSecManaged.setFilme(null);
			filDirSecManaged.setDiretorSecundario(null);
			
			session.remove(filDirSecManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[FilmeDiretorSecundarioDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}