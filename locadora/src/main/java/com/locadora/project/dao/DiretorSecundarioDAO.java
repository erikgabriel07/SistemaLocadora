package com.locadora.project.dao;

import com.locadora.project.dao.GenericDAO;
import com.locadora.project.model.DiretorSecundario;
import com.locadora.project.model.FilmeDiretorSecundario;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DiretorSecundarioDAO extends GenericDAO<DiretorSecundario, Long> {
	public DiretorSecundarioDAO() {
		super(DiretorSecundario.class);
	}
	
	@Override
	public void remove(DiretorSecundario diretorSecundario) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			DiretorSecundario dirSecManaged = session.get(DiretorSecundario.class, diretorSecundario.getCodDiretorSecundario());
			
			for (FilmeDiretorSecundario filmeDirSec : dirSecManaged.getFilmeDiretorSecundario()) {
				filmeDirSec.setDiretorSecundario(null);
			}
			
			dirSecManaged.getFilmeDiretorSecundario().clear();
			
			session.remove(dirSecManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[DiretorSecundario:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}