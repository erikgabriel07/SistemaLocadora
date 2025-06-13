package com.locadora.project.services;

import com.locadora.project.model.FilmeLocal;
import com.locadora.project.dao.FilmeLocalDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class FilmeLocalService extends FilmeLocalDAO implements IOperations<FilmeLocal, Long> {
	public FilmeLocalService() {
		super();
	}
	
	public FilmeLocalService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(FilmeLocal filmeLocal) throws Exception {
		try {
			super.save(filmeLocal);
			System.out.println("Filme local cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(FilmeLocal filmeLocal) throws Exception {
		try {
			super.update(filmeLocal);
			System.out.println("Filme local atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(FilmeLocal filmeLocal) throws Exception {
		try {
			super.remove(filmeLocal);
			System.out.println("Filme local removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public FilmeLocal listarId(Long id) throws Exception {
		try {
			FilmeLocal filmeLocal = super.findBy(id);
			if (filmeLocal != null) return filmeLocal;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (FilmeLocal i : super.findAll()) {
			System.out.println(i);
		}
	}
}