package com.locadora.project.dao;

import com.locadora.project.dao.GenericDAO;
import com.locadora.project.model.Locacao;
import com.locadora.project.model.FitaLocacao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FitaLocacaoDAO extends GenericDAO<FitaLocacao, Long> {
	public FitaLocacaoDAO() {
		super(FitaLocacao.class);
	}
	
	@Override
	public void remove(FitaLocacao fitaLocacao) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			FitaLocacao fitaLocacaoManaged = session.get(FitaLocacao.class, fitaLocacao.getCodFitaLocacao());
			
			fitaLocacaoManaged.getFita().getFitaLocacao().remove(fitaLocacaoManaged);
			fitaLocacao.setFita(null);
			
			for (Locacao locacao : fitaLocacaoManaged.getLocacao()) {
				locacao.setFitaLocacao(null);
			}
			fitaLocacaoManaged.getLocacao().clear();
			
			session.remove(fitaLocacaoManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[FitaLocacaoDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}