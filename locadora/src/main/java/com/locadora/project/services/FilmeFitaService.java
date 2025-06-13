package com.locadora.project.services;

import com.locadora.project.model.FilmeFita;
import com.locadora.project.dao.FilmeFitaDAO;

import com.locadora.project.util.IOperations;
import com.locadora.project.util.HibernateUtil;

import org.hibernate.SessionFactory;

public class FilmeFitaService extends FilmeFitaDAO implements IOperations<FilmeFita, Long> {
	public FilmeFitaService() {
		super();
	}
	
	public FilmeFitaService(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void cadastrar(FilmeFita filmeFita) throws Exception {
		try {
			super.save(filmeFita);
			System.out.println("Fita do filme cadastrado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void atualizar(FilmeFita filmeFita) throws Exception {
		try {
			super.update(filmeFita);
			System.out.println("Fita do filme atualizado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void remover(FilmeFita filmeFita) throws Exception {
		try {
			super.remove(filmeFita);
			System.out.println("Fita do filme removido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public FilmeFita listarId(Long id) throws Exception {
		try {
			FilmeFita filmeFita = super.findBy(id);
			if (filmeFita != null) return filmeFita;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public void listar() {
		for (FilmeFita i : super.findAll()) {
			System.out.println(i);
		}
	}
}