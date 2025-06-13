package com.locadora.project.services;

import com.locadora.project.model.AtorFilme;
import com.locadora.project.dao.AtorFilmeDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class AtorFilmeService extends AtorFilmeDAO implements IOperations<AtorFilme, Long> {
	public AtorFilmeService() {
		super();
	}
	
	public AtorFilmeService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(AtorFilme atorFilme) throws Exception {
		try {
			super.save(atorFilme);
			System.out.println("Ator do filme cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(AtorFilme atorFilme) throws Exception {
		try {
			super.update(atorFilme);
			System.out.println("Ator do filme atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(AtorFilme atorFilme) throws Exception {
		try {
			super.remove(atorFilme);
			System.out.println("Ator do filme removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public AtorFilme listarId(Long id) throws Exception {
		try {
			AtorFilme atorFilme = super.findBy(id);
			if (atorFilme != null) return atorFilme;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (AtorFilme i : super.findAll()) {
			System.out.println(i);
		}
	}
}