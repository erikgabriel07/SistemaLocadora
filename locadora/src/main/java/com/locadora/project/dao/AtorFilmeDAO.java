package com.locadora.project.dao;

import com.locadora.project.dao.GenericDAO;
import com.locadora.project.model.AtorFilme;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class AtorFilmeDAO extends GenericDAO<AtorFilme, Long> {
	public AtorFilmeDAO() {
		super(AtorFilme.class);
	}
	
	@Override
	public void remove(AtorFilme atorFilme) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			AtorFilme atorFilmeManaged = session.get(AtorFilme.class, atorFilme.getCodAtorFilme());
			
			atorFilmeManaged.getAtor().getAtorFilme().remove(atorFilmeManaged);
			atorFilmeManaged.getFilme().getAtorFilme().remove(atorFilmeManaged);
			
			atorFilmeManaged.setAtor(null);
			atorFilmeManaged.setFilme(null);
			
			session.remove(atorFilmeManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[AtorFilmeDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}