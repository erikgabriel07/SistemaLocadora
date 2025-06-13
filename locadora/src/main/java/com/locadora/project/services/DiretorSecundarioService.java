package com.locadora.project.services;

import com.locadora.project.model.DiretorSecundario;
import com.locadora.project.dao.DiretorSecundarioDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class DiretorSecundarioService extends DiretorSecundarioDAO implements IOperations<DiretorSecundario, Long> {
	public DiretorSecundarioService() {
		super();
	}
	
	public DiretorSecundarioService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(DiretorSecundario diretorSecundario) throws Exception {
		try {
			super.save(diretorSecundario);
			System.out.println("Diretor secundário cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(DiretorSecundario diretorSecundario) throws Exception {
		try {
			super.update(diretorSecundario);
			System.out.println("Diretor secundário atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(DiretorSecundario diretorSecundario) throws Exception {
		try {
			super.remove(diretorSecundario);
			System.out.println("Diretor secundário removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public DiretorSecundario listarId(Long id) throws Exception {
		try {
			DiretorSecundario diretorSecundario = super.findBy(id);
			if (diretorSecundario != null) return diretorSecundario;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (DiretorSecundario i : super.findAll()) {
			System.out.println(i);
		}
	}
}