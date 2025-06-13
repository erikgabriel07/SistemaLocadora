package com.locadora.project.dao;

import com.locadora.project.model.Fita;
import com.locadora.project.model.FitaLocacao;
import com.locadora.project.dao.GenericDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FitaDAO extends GenericDAO<Fita, Long> {
	public FitaDAO() {
		super(Fita.class);
	}
	
	@Override
	public void remove(Fita fita) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			Fita fitaManaged = session.get(Fita.class, fita.getCodFita());
			
			for (FitaLocacao fitaLocacao : fitaManaged.getFitaLocacao()) {
				fitaLocacao.setFita(null);
			}
			fitaManaged.getFitaLocacao().clear();
			
			fitaManaged.getFilmeFita().setFita(null);
			fitaManaged.setFilmeFita(null);
			
			session.remove(fitaManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[FitaDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}