package com.locadora.project.dao;

import com.locadora.project.model.Locacao;
import com.locadora.project.dao.GenericDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LocacaoDAO extends GenericDAO<Locacao, Long> {
	public LocacaoDAO() {
		super(Locacao.class);
	}
	
	@Override
	public void remove(Locacao locacao) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			Locacao locacaoManaged = session.get(Locacao.class, locacao.getCodLocacao());
			
			locacaoManaged.getCliente().getLocacao().remove(locacaoManaged);
			locacaoManaged.getFitaLocacao().getLocacao().remove(locacaoManaged);
			
			locacaoManaged.setCliente(null);
			locacaoManaged.setFitaLocacao(null);
			
			session.remove(locacaoManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[LocacaoDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}