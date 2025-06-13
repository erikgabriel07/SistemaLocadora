package com.locadora.project.controller;

import com.locadora.project.model.Fita;
import com.locadora.project.model.Filme;
import com.locadora.project.model.FilmeFita;
import com.locadora.project.dto.FilmeFitaDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;

import com.locadora.project.services.FitaService;
import com.locadora.project.services.FilmeService;
import com.locadora.project.services.FilmeFitaService;

public class FilmeFitaController extends ValidatorUtil<FilmeFitaDTO> {
	private FilmeFitaDTO prototype = new FilmeFitaDTO();
	private ObjectFactory<FilmeFitaDTO> objFactory;
	
	private final FitaService fitaService = new FitaService(HibernateUtil.getSessionFactory());
	private final FilmeService filmeService = new FilmeService(HibernateUtil.getSessionFactory());
	private final FilmeFitaService filmeFitaService = new FilmeFitaService(HibernateUtil.getSessionFactory());
	
	public FilmeFitaController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarFilmeFita(Long FitaId, Long FilmeId) {
		FilmeFitaDTO dto = objFactory.createInstance();
		
		dto.setFitaId(FitaId);
		dto.setFilmeId(FilmeId);
		
		try {
			if (super.validate(dto, null) == 0) {
				Fita fita = fitaService.listarId(dto.getFitaId());	
				if (fita == null) throw new Exception("Fita não encontrada.");
				
				Filme filme = filmeService.listarId(dto.getFilmeId());
				if (filme == null) throw new Exception("Filme não encontrado.");
				
				FilmeFita filmeFita = new FilmeFita();
				
				filmeFita.setFita(fita);
				fita.setFilmeFita(filmeFita);
				
				filmeFita.setFilme(filme);
				filme.getFilmeFita().add(filmeFita);
				
				filmeFitaService.cadastrar(filmeFita);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarFilmeFita(Long filmeFitaId, Long fitaId, Long filmeId) {
		try {
			FilmeFita filmeFita = filmeFitaService.listarId(filmeFitaId);
			if (filmeFita == null) throw new Exception("Registro não encontrado.");
			
			FilmeFitaDTO dto = objFactory.createInstance();
		
			dto.setFitaId(fitaId);
			dto.setFilmeId(filmeId);
			
			Fita fita = fitaService.listarId(dto.getFitaId());	
			if (fita != null) {
				filmeFita.setFita(fita);
				fita.setFilmeFita(filmeFita);
			}
			
			Filme filme = filmeService.listarId(dto.getFilmeId());
			if (filme != null) {
				filmeFita.setFilme(filme);
				filme.getFilmeFita().add(filmeFita);
			}
			
			if (fita != null || filme != null) filmeFitaService.atualizar(filmeFita);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerFilmeFita(Long filmeFitaId) {
		try {
			FilmeFita filmeFita = filmeFitaService.listarId(filmeFitaId);
			
			if (filmeFita == null) throw new Exception("Registro não encontrado.");
			
			filmeFitaService.remover(filmeFita);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public FilmeFita buscarFilmeFita(Long filmeFitaId) {
		try {
			return filmeFitaService.listarId(filmeFitaId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarFilmeFita() {
		filmeFitaService.listar();
	}
}