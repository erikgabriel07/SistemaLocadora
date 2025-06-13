package com.locadora.project.dao;

import com.locadora.project.model.Filme;
import com.locadora.project.model.AtorFilme;
import com.locadora.project.model.FilmeFita;
import com.locadora.project.model.FilmeLocal;
import com.locadora.project.model.FilmeDiretorSecundario;
import com.locadora.project.dao.GenericDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FilmeDAO extends GenericDAO<Filme, Long> {
	public FilmeDAO() {
		super(Filme.class);
	}
	
	@Override
	public void remove(Filme filme) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			Filme filmeManaged = session.get(Filme.class, filme.getCodFilme());
			
			for (AtorFilme atorFilme : filmeManaged.getAtorFilme()) {
				atorFilme.setFilme(null);
			}
			filmeManaged.getAtorFilme().clear();
			
			for (FilmeFita filmeFita : filmeManaged.getFilmeFita()) {
				filmeFita.setFilme(null);
			}
			filmeManaged.getFilmeFita().clear();
			
			for (FilmeLocal filmeLocal : filmeManaged.getFilmeLocal()) {
				filmeLocal.setFilme(null);
			}
			filmeManaged.getFilmeLocal().clear();
			
			for (FilmeDiretorSecundario filDirSec : filmeManaged.getFilmeDiretorSecundario()) {
				filDirSec.setFilme(null);
			}
			filmeManaged.getFilmeDiretorSecundario().clear();
			
			session.remove(filmeManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[FilmeDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}