package com.locadora.project.dao;

import com.locadora.project.model.Ator;
import com.locadora.project.model.AtorFilme;
import com.locadora.project.dao.GenericDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AtorDAO extends GenericDAO<Ator, Long> {
	public AtorDAO() {
		super(Ator.class);
	}
	
	@Override
	public void remove(Ator ator) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			Ator atorManaged = session.get(Ator.class, ator.getCodAtor());
			
			for (AtorFilme atorFilme : atorManaged.getAtorFilme()) {
				atorFilme.setAtor(null);
				atorFilme.setFilme(null);
			}
			
			atorManaged.getAtorFilme().clear();
			
			session.remove(atorManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[AtorDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}