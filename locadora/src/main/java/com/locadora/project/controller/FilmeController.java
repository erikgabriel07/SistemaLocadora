package com.locadora.project.controller;

import com.locadora.project.model.Filme;
import com.locadora.project.model.AtorFilme;
import com.locadora.project.model.FilmeFita;
import com.locadora.project.model.FilmeLocal;
import com.locadora.project.model.FilmeDiretorSecundario;
import com.locadora.project.dto.FilmeDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;
import com.locadora.project.util.Groups.*;

import com.locadora.project.services.FilmeService;
import com.locadora.project.services.AtorFilmeService;
import com.locadora.project.services.FilmeFitaService;
import com.locadora.project.services.FilmeLocalService;
import com.locadora.project.services.FilmeDiretorSecundarioService;

public class FilmeController extends ValidatorUtil<FilmeDTO> {
	private FilmeDTO prototype = new FilmeDTO();
	private ObjectFactory<FilmeDTO> objFactory;
	
	private final FilmeService filmeService = new FilmeService(HibernateUtil.getSessionFactory());
	private final AtorFilmeService atorFilmeService = new AtorFilmeService(HibernateUtil.getSessionFactory());
	private final FilmeFitaService filmeFitaService = new FilmeFitaService(HibernateUtil.getSessionFactory());
	private final FilmeLocalService filmeLocalService = new FilmeLocalService(HibernateUtil.getSessionFactory());
	private final FilmeDiretorSecundarioService filmeDiretorSecundarioService = new FilmeDiretorSecundarioService(HibernateUtil.getSessionFactory());
	
	public FilmeController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarFilme(String nome, String diretor, String sinopse, Integer anoLancamento, String genero, Integer duracao,
	String classificacao, String idioma, String status, Long filmeFitaId, Long atorFilmeId, Long filmeLocalId, Long filmeDiretorSecundarioId) {
		FilmeDTO dto = objFactory.createInstance();
		
		dto.setNome(nome);
		dto.setDiretor(diretor);
		dto.setSinopse(sinopse);
		dto.setAnoLancamento(anoLancamento);
		dto.setGenero(genero);
		dto.setDuracao(duracao);
		dto.setClassificacao(classificacao);
		dto.setIdioma(idioma);
		dto.setStatus(status);
		dto.setFilmeFitaId(filmeFitaId);
		dto.setAtorFilmeId(atorFilmeId);
		dto.setFilmeLocalId(filmeLocalId);
		dto.setFilmeDiretorSecundarioId(filmeDiretorSecundarioId);
		
		try {
			if (super.validate(dto, OnCreate.class) == 0) {
				Filme filme = new Filme(dto);
				
				if (dto.getFilmeFitaId() != null && dto.getFilmeFitaId() > 0) {
					FilmeFita filmeFita = filmeFitaService.listarId(dto.getFilmeFitaId());
					
					if (filmeFita != null) {
						filmeFita.setFilme(filme);
						filme.getFilmeFita().add(filmeFita);
						filmeFitaService.atualizar(filmeFita);
					}
				}
				
				if (dto.getAtorFilmeId() != null && dto.getAtorFilmeId() > 0) {
					AtorFilme atorFilme = atorFilmeService.listarId(dto.getAtorFilmeId());
					
					if (atorFilme != null) {
						atorFilme.setFilme(filme);
						filme.getAtorFilme().add(atorFilme);
						atorFilmeService.atualizar(atorFilme);
					}
				}
				
				if (dto.getFilmeLocalId() != null && dto.getFilmeLocalId() > 0) {
					FilmeLocal filmeLocal = filmeLocalService.listarId(dto.getFilmeLocalId());
					
					if (filmeLocal != null) {
						filmeLocal.setFilme(filme);
						filme.getFilmeLocal().add(filmeLocal);
						filmeLocalService.atualizar(filmeLocal);
					}
				}
				
				if (dto.getFilmeDiretorSecundarioId() != null && dto.getFilmeDiretorSecundarioId() > 0) {
					FilmeDiretorSecundario diretorSec = filmeDiretorSecundarioService.listarId(dto.getFilmeDiretorSecundarioId());
					
					if (diretorSec != null) {
						diretorSec.setFilme(filme);
						filme.getFilmeDiretorSecundario().add(diretorSec);
						filmeDiretorSecundarioService.atualizar(diretorSec);
					}
				}
				
				filmeService.cadastrar(filme);
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarFilme(Long filmeId, String nome, String diretor, String sinopse, Integer anoLancamento, String genero, Integer duracao,
	String classificacao, String idioma, String status, Long filmeFitaId, Long atorFilmeId, Long filmeLocalId, Long filmeDiretorSecundarioId) {
		try {
			Filme filme = filmeService.listarId(filmeId);
			if (filme == null) throw new Exception("Filme não encontrado.");
			
			FilmeDTO dto = objFactory.createInstance();
		
			dto.setNome(nome);
			dto.setDiretor(diretor);
			dto.setSinopse(sinopse);
			dto.setAnoLancamento(anoLancamento);
			dto.setGenero(genero);
			dto.setDuracao(duracao);
			dto.setClassificacao(classificacao);
			dto.setIdioma(idioma);
			dto.setStatus(status);
			dto.setFilmeFitaId(filmeFitaId);
			dto.setAtorFilmeId(atorFilmeId);
			dto.setFilmeLocalId(filmeLocalId);
			dto.setFilmeDiretorSecundarioId(filmeDiretorSecundarioId);
			
			if (super.validate(dto, null) == 0) {
				filme.update(dto);
				
				if (dto.getFilmeFitaId() != null && dto.getFilmeFitaId() > 0) {
					FilmeFita filmeFita = filmeFitaService.listarId(dto.getFilmeFitaId());
					
					if (filmeFita != null) {
						filmeFita.setFilme(filme);
						filme.getFilmeFita().add(filmeFita);
						filmeFitaService.atualizar(filmeFita);
					}
				}
				
				if (dto.getAtorFilmeId() != null && dto.getAtorFilmeId() > 0) {
					AtorFilme atorFilme = atorFilmeService.listarId(dto.getAtorFilmeId());
					
					if (atorFilme != null) {
						atorFilme.setFilme(filme);
						filme.getAtorFilme().add(atorFilme);
						atorFilmeService.atualizar(atorFilme);
					}
				}
				
				if (dto.getFilmeLocalId() != null && dto.getFilmeLocalId() > 0) {
					FilmeLocal filmeLocal = filmeLocalService.listarId(dto.getFilmeLocalId());
					
					if (filmeLocal != null) {
						filmeLocal.setFilme(filme);
						filme.getFilmeLocal().add(filmeLocal);
						filmeLocalService.atualizar(filmeLocal);
					}
				}
				
				if (dto.getFilmeDiretorSecundarioId() != null && dto.getFilmeDiretorSecundarioId() > 0) {
					FilmeDiretorSecundario diretorSec = filmeDiretorSecundarioService.listarId(dto.getFilmeDiretorSecundarioId());
					
					if (diretorSec != null) {
						diretorSec.setFilme(filme);
						filme.getFilmeDiretorSecundario().add(diretorSec);
						filmeDiretorSecundarioService.atualizar(diretorSec);
					}
				}
				
				filmeService.atualizar(filme);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerFilme(Long filmeId) {
		try {
			Filme filme = filmeService.listarId(filmeId);
			
			if (filme == null) throw new Exception("Filme não encontrado.");
			
			filmeService.remover(filme);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Filme buscarFilme(Long filmeId) {
		try {
			return filmeService.listarId(filmeId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listar() {
		filmeService.listar();
	}
}