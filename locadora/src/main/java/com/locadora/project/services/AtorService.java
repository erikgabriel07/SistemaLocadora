package com.locadora.project.services;

import com.locadora.project.model.Ator;
import com.locadora.project.dao.AtorDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class AtorService extends AtorDAO implements IOperations<Ator, Long> {
	public AtorService() {
		super();
	}
	
	public AtorService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(Ator ator) throws Exception {
		try {
			super.save(ator);
			System.out.println("Ator cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(Ator ator) throws Exception {
		try {
			super.update(ator);
			System.out.println("Ator atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(Ator ator) throws Exception {
		try {
			super.remove(ator);
			System.out.println("Ator removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Ator listarId(Long id) throws Exception {
		try {
			Ator ator = super.findBy(id);
			if (ator != null) return ator;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (Ator i : super.findAll()) {
			System.out.println(i);
		}
	}
}