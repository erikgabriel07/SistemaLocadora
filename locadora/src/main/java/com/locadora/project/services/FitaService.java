package com.locadora.project.services;

import com.locadora.project.model.Fita;
import com.locadora.project.dao.FitaDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class FitaService extends FitaDAO implements IOperations<Fita, Long> {
	public FitaService() {
		super();
	}
	
	public FitaService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(Fita fita) throws Exception {
		try {
			super.save(fita);
			System.out.println("Fita cadastrada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(Fita fita) throws Exception {
		try {
			super.update(fita);
			System.out.println("Fita atualizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(Fita fita) throws Exception {
		try {
			super.remove(fita);
			System.out.println("Fita removida com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Fita listarId(Long id) throws Exception {
		try {
			Fita fita = super.findBy(id);
			if (fita != null) return fita;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (Fita i : super.findAll()) {
			System.out.println(i);
		}
	}
}