package com.locadora.project.dao;

import com.locadora.project.model.Local;
import com.locadora.project.model.FilmeLocal;
import com.locadora.project.dao.GenericDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class LocalDAO extends GenericDAO<Local, Long> {
	public LocalDAO() {
		super(Local.class);
	}
	
	@Override
	public void remove(Local local) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			Local localManaged = session.get(Local.class, local.getCodLocal());
			
			for (FilmeLocal filmeLocal : localManaged.getFilmeLocal()) {
				filmeLocal.setLocal(null);
			}
			localManaged.getFilmeLocal().clear();
			
			session.remove(localManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[LocalDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}