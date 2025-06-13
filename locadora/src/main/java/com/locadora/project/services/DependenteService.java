package com.locadora.project.services;

import com.locadora.project.model.Dependente;
import com.locadora.project.dao.DependenteDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class DependenteService extends DependenteDAO implements IOperations<Dependente, Long> {
	public DependenteService() {
		super();
	}
	
	public DependenteService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(Dependente dependente) throws Exception {
		try {
			super.save(dependente);
			System.out.println("Dependente cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(Dependente dependente) throws Exception {
		try {
			super.update(dependente);
			System.out.println("Dependente atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(Dependente dependente) throws Exception {
		try {
			super.remove(dependente);
			System.out.println("Dependente removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Dependente listarId(Long id) throws Exception {
		try {
			Dependente dependente = super.findBy(id);
			if (dependente != null) return dependente;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (Dependente i : super.findAll()) {
			System.out.println(i);
		}
	}
}