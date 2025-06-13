package com.locadora.project.services;

import com.locadora.project.model.Filme;
import com.locadora.project.dao.FilmeDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class FilmeService extends FilmeDAO implements IOperations<Filme, Long> {
	public FilmeService() {
		super();
	}
	
	public FilmeService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(Filme filme) throws Exception {
		try {
			super.save(filme);
			System.out.println("Filme cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(Filme filme) throws Exception {
		try {
			super.update(filme);
			System.out.println("Filme atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(Filme filme) throws Exception {
		try {
			super.remove(filme);
			System.out.println("Filme removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Filme listarId(Long id) throws Exception {
		try {
			Filme filme = super.findBy(id);
			if (filme != null) return filme;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (Filme i : super.findAll()) {
			System.out.println(i);
		}
	}
}