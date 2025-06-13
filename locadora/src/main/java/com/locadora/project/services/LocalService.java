package com.locadora.project.services;

import com.locadora.project.model.Local;
import com.locadora.project.dao.LocalDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class LocalService extends LocalDAO implements IOperations<Local, Long> {
	public LocalService() {
		super();
	}
	
	public LocalService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(Local local) throws Exception {
		try {
			super.save(local);
			System.out.println("local atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(Local local) throws Exception {
		try {
			super.update(local);
			System.out.println("Local atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(Local local) throws Exception {
		try {
			super.remove(local);
			System.out.println("Local removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Local listarId(Long id) throws Exception {
		try {
			Local local = super.findBy(id);
			if (local != null) return local;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (Local i : super.findAll()) {
			System.out.println(i);
		}
	}
}