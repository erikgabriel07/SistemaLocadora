package com.locadora.project.services;

import com.locadora.project.model.FilmeDiretorSecundario;
import com.locadora.project.dao.FilmeDiretorSecundarioDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class FilmeDiretorSecundarioService extends FilmeDiretorSecundarioDAO implements IOperations<FilmeDiretorSecundario, Long> {
	public FilmeDiretorSecundarioService() {
		super();
	}
	
	public FilmeDiretorSecundarioService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(FilmeDiretorSecundario filmeDiretorSecundario) throws Exception {
		try {
			super.save(filmeDiretorSecundario);
			System.out.println("Diretor secundário do filme cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(FilmeDiretorSecundario filmeDiretorSecundario) throws Exception {
		try {
			super.update(filmeDiretorSecundario);
			System.out.println("Diretor secundário do filme atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(FilmeDiretorSecundario filmeDiretorSecundario) throws Exception {
		try {
			super.remove(filmeDiretorSecundario);
			System.out.println("Diretor secundário do filme removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public FilmeDiretorSecundario listarId(Long id) throws Exception {
		try {
			FilmeDiretorSecundario filmeDiretorSecundario = super.findBy(id);
			if (filmeDiretorSecundario != null) return filmeDiretorSecundario;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (FilmeDiretorSecundario i : super.findAll()) {
			System.out.println(i);
		}
	}
}