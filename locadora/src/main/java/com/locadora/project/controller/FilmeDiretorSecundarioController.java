package com.locadora.project.controller;

import com.locadora.project.model.Filme;
import com.locadora.project.model.DiretorSecundario;
import com.locadora.project.model.FilmeDiretorSecundario;
import com.locadora.project.dto.FilmeDiretorSecundarioDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;

import com.locadora.project.services.FilmeService;
import com.locadora.project.services.DiretorSecundarioService;
import com.locadora.project.services.FilmeDiretorSecundarioService;

public class FilmeDiretorSecundarioController extends ValidatorUtil<FilmeDiretorSecundarioDTO> {
	private FilmeDiretorSecundarioDTO prototype = new FilmeDiretorSecundarioDTO();
	private ObjectFactory<FilmeDiretorSecundarioDTO> objFactory;
	
	private final FilmeService filmeService = new FilmeService(HibernateUtil.getSessionFactory());
	private final DiretorSecundarioService diretorSecundarioService = new DiretorSecundarioService(HibernateUtil.getSessionFactory());
	private final FilmeDiretorSecundarioService filmeDiretorSecundarioService = new FilmeDiretorSecundarioService(HibernateUtil.getSessionFactory());
	
	public FilmeDiretorSecundarioController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarFilmeDiretorSecundario(Long filmeId, Long diretorSecundarioId) {
		FilmeDiretorSecundarioDTO dto = objFactory.createInstance();
		
		dto.setFilmeId(filmeId);
		dto.setDiretorSecundarioId(diretorSecundarioId);
		
		try {
			if (super.validate(dto, null) == 0) {
				Filme filme = filmeService.listarId(dto.getFilmeId());
				if (filme == null) throw new Exception("Filme não encontrado.");
				
				DiretorSecundario diretorSec = diretorSecundarioService.listarId(dto.getDiretorSecundarioId());
				if (diretorSec == null) throw new Exception("Diretor não encontrado.");
				
				FilmeDiretorSecundario filmeDiretor = new FilmeDiretorSecundario();
				
				filmeDiretor.setFilme(filme);
				filme.getFilmeDiretorSecundario().add(filmeDiretor);
				
				filmeDiretor.setDiretorSecundario(diretorSec);
				diretorSec.getFilmeDiretorSecundario().add(filmeDiretor);
				
				filmeDiretorSecundarioService.cadastrar(filmeDiretor);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarFilmeDiretorSecundario(Long dirSecId, Long filmeId, Long diretorSecundarioId) {
		try {
			FilmeDiretorSecundario filmeDiretor = filmeDiretorSecundarioService.listarId(dirSecId);
			if (filmeDiretor == null) throw new Exception("Registro não encontrado.");
			
			FilmeDiretorSecundarioDTO dto = objFactory.createInstance();
		
			dto.setFilmeId(filmeId);
			dto.setDiretorSecundarioId(diretorSecundarioId);
			
			Filme filme = filmeService.listarId(dto.getFilmeId());
			if (filme != null) {
				filmeDiretor.setFilme(filme);
				filme.getFilmeDiretorSecundario().add(filmeDiretor);
			}
			
			DiretorSecundario diretorSec = diretorSecundarioService.listarId(dto.getDiretorSecundarioId());
			if (diretorSec != null) {
				filmeDiretor.setDiretorSecundario(diretorSec);
				diretorSec.getFilmeDiretorSecundario().add(filmeDiretor);
			}
			
			if (filme != null || diretorSec != null) filmeDiretorSecundarioService.atualizar(filmeDiretor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerFilmeDiretorSecundario(Long dirSecId) {
		try {
			FilmeDiretorSecundario filmeDiretor = filmeDiretorSecundarioService.listarId(dirSecId);
			
			if (filmeDiretor == null) throw new Exception("Registro não encontrado.");
			
			filmeDiretorSecundarioService.remover(filmeDiretor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public FilmeDiretorSecundario buscarFilmeDiretorSecundario(Long dirSecId) {
		try {
			return filmeDiretorSecundarioService.listarId(dirSecId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarFilmeDiretorSecundario() {
		filmeDiretorSecundarioService.listar();
	}
}