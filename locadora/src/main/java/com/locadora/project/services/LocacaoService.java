package com.locadora.project.services;

import com.locadora.project.model.Locacao;
import com.locadora.project.dao.LocacaoDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class LocacaoService extends LocacaoDAO implements IOperations<Locacao, Long> {
	public LocacaoService() {
		super();
	}
	
	public LocacaoService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(Locacao locacao) throws Exception {
		try {
			super.save(locacao);
			System.out.println("Locação cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(Locacao locacao) throws Exception {
		try {
			super.update(locacao);
			System.out.println("Locação atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(Locacao locacao) throws Exception {
		try {
			locacao.setCliente(null);
			locacao.setFitaLocacao(null);
			super.remove(locacao);
			System.out.println("Locação removida com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	@Override
	public Locacao listarId(Long id) throws Exception {
		try {
			Locacao locacao = super.findBy(id);
			if (locacao != null) return locacao;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (Locacao i : super.findAll()) {
			System.out.println(i);
		}
	}
}