package com.locadora.project.controller;

import com.locadora.project.model.Ator;
import com.locadora.project.model.Filme;
import com.locadora.project.model.AtorFilme;
import com.locadora.project.dto.AtorFilmeDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;
import com.locadora.project.util.Groups.*;

import com.locadora.project.services.AtorService;
import com.locadora.project.services.FilmeService;
import com.locadora.project.services.AtorFilmeService;

public class AtorFilmeController extends ValidatorUtil<AtorFilmeDTO> {
	private AtorFilmeDTO prototype = new AtorFilmeDTO();
	private ObjectFactory<AtorFilmeDTO> objFactory;
	
	private final AtorService atorService = new AtorService(HibernateUtil.getSessionFactory());
	private final FilmeService filmeService = new FilmeService(HibernateUtil.getSessionFactory());
	private final AtorFilmeService atorFilmeService = new AtorFilmeService(HibernateUtil.getSessionFactory());
	
	public AtorFilmeController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarAtorFilme(String papel, Long atorId, Long filmeId) {
		AtorFilmeDTO dto = objFactory.createInstance();
		
		dto.setPapel(papel);
		dto.setAtorId(atorId);
		dto.setFilmeId(filmeId);
		
		try {
			if (super.validate(dto, null) == 0) {
				Ator ator = atorService.listarId(dto.getAtorId());
				if (ator == null) throw new Exception("Ator não encontrado.");
				
				Filme filme = filmeService.listarId(dto.getFilmeId());
				if (filme == null) throw new Exception("Filme não encontrado.");
				
				AtorFilme atorFilme = new AtorFilme();
				
				atorFilme.setAtor(ator);
				ator.getAtorFilme().add(atorFilme);
				
				atorFilme.setFilme(filme);
				filme.getAtorFilme().add(atorFilme);
				
				atorFilmeService.cadastrar(atorFilme);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarAtorFilme(Long atorFilmeId, String papel, Long atorId, Long filmeId) {
		try {
			AtorFilme atorFilme = atorFilmeService.listarId(atorFilmeId);
			if (atorFilme == null) throw new Exception("Registro não encontrado.");
			
			AtorFilmeDTO dto = objFactory.createInstance();
			
			dto.setPapel(papel);
			dto.setAtorId(atorId);
			dto.setFilmeId(filmeId);
			
			Ator ator = atorService.listarId(dto.getAtorId());
			if (ator != null) {
				atorFilme.setAtor(ator);
				ator.getAtorFilme().add(atorFilme);
			}
			
			Filme filme = filmeService.listarId(dto.getFilmeId());
			if (filme != null) {
				atorFilme.setFilme(filme);
				filme.getAtorFilme().add(atorFilme);
			}
			
			if (ator != null || filme != null) atorFilmeService.atualizar(atorFilme);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerAtor(Long atorFilmeId) {
		try {
			AtorFilme atorFilme = atorFilmeService.listarId(atorFilmeId);
			
			if (atorFilme == null) throw new Exception("Registro não encontrado.");
			
			atorFilmeService.remover(atorFilme);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public AtorFilme buscarAtorFilme(Long atorFilmeId) {
		try {
			return atorFilmeService.listarId(atorFilmeId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarAtorFilme() {
		atorFilmeService.listar();
	}
}