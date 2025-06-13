package com.locadora.project.dao;

import com.locadora.project.dao.GenericDAO;
import com.locadora.project.model.Dependente;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DependenteDAO extends GenericDAO<Dependente, Long> {
	public DependenteDAO() {
		super(Dependente.class);
	}
	
	@Override
	public void remove(Dependente dependente) {
		Transaction tx = null;
		
		try (Session session = super.sessionFactory.openSession()) {
			tx = session.beginTransaction();
			
			Dependente dependenteManaged = session.get(Dependente.class, dependente.getCodDependente());
			
			dependenteManaged.getCliente().getDependente().remove(dependenteManaged);
			dependenteManaged.setCliente(null);
			
			session.remove(dependenteManaged);
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive()) tx.rollback();
			System.err.println("[DependenteDAO:ERR] Erro ao remover a entidade: " + e.getMessage());
			throw e;
		}
	}
}