package com.locadora.project.services;

import com.locadora.project.model.FitaLocacao;
import com.locadora.project.dao.FitaLocacaoDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class FitaLocacaoService extends FitaLocacaoDAO implements IOperations<FitaLocacao, Long> {
	public FitaLocacaoService() {
		super();
	}
	
	public FitaLocacaoService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(FitaLocacao fitaLocacao) throws Exception {
		try {
			super.save(fitaLocacao);
			System.out.println("Fita locação cadastrada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(FitaLocacao fitaLocacao) throws Exception {
		try {
			super.update(fitaLocacao);
			System.out.println("Fita locação atualizada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(FitaLocacao fitaLocacao) throws Exception {
		try {
			fitaLocacao.getLocacao().clear();
			fitaLocacao.setFita(null);
			super.remove(fitaLocacao);
			System.out.println("Fita locação removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public FitaLocacao listarId(Long id) throws Exception {
		try {
			FitaLocacao fitaLocacao = super.findBy(id);
			if (fitaLocacao != null) return fitaLocacao;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (FitaLocacao i : super.findAll()) {
			System.out.println(i);
		}
	}
}