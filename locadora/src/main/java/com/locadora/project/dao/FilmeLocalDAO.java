package com.locadora.project.dao;

import com.locadora.project.dao.GenericDAO;
import com.locadora.project.model.FilmeLocal;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class FilmeLocalDAO extends GenericDAO<FilmeLocal, Long> {
	public FilmeLocalDAO() {
		super(FilmeLocal.class);
	}
	
	@Override
	public void remove(FilmeLocal filmeLocal) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			FilmeLocal filmeLocalManaged = session.get(FilmeLocal.class, filmeLocal.getCodFilmeLocal());
			
			filmeLocalManaged.getLocal().getFilmeLocal().remove(filmeLocalManaged);
			filmeLocalManaged.getFilme().getFilmeLocal().remove(filmeLocalManaged);
			
			filmeLocalManaged.setLocal(null);
			filmeLocalManaged.setFilme(null);
			
			session.remove(filmeLocalManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[FilmeLocalDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}