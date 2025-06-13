package com.locadora.project.controller;

import com.locadora.project.model.Fita;
import com.locadora.project.model.FilmeFita;
import com.locadora.project.model.FitaLocacao;
import com.locadora.project.dto.FitaDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;
import com.locadora.project.util.Groups.*;

import com.locadora.project.services.FitaService;
import com.locadora.project.services.FilmeFitaService;
import com.locadora.project.services.FitaLocacaoService;

public class FitaController extends ValidatorUtil<FitaDTO> {
	private FitaDTO prototype = new FitaDTO();
	private ObjectFactory<FitaDTO> objFactory;
	
	private final FitaService fitaService = new FitaService(HibernateUtil.getSessionFactory());
	private final FilmeFitaService filmeFitaService = new FilmeFitaService(HibernateUtil.getSessionFactory());
	private final FitaLocacaoService fitaLocacaoService = new FitaLocacaoService(HibernateUtil.getSessionFactory());
	
	public FitaController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarFita(String nome, Double valor, Long filmeFitaId, Long fitaLocacaoId) {
		FitaDTO dto = objFactory.createInstance();
		
		dto.setNome(nome);
		dto.setValor(valor);
		dto.setFilmeFitaId(filmeFitaId);
		dto.setFitaLocacaoId(fitaLocacaoId);
		
		try {
			if (super.validate(dto, OnCreate.class) == 0) {
				Fita fita = new Fita(dto);
				
				FilmeFita filmeFita = filmeFitaService.listarId(dto.getFilmeFitaId());
				if (filmeFita != null) {
					fita.setFilmeFita(filmeFita);
					filmeFita.setFita(fita);
				}
				
				FitaLocacao fitaLocacao = fitaLocacaoService.listarId(dto.getFitaLocacaoId());
				if (fitaLocacao != null) {
					fitaLocacao.setFita(fita);
					fita.getFitaLocacao().add(fitaLocacao);
					fitaLocacaoService.atualizar(fitaLocacao);
				}
				
				fitaService.cadastrar(fita);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarFita(Long fitaId, String nome, Double valor) {
		try {
			Fita fita = fitaService.listarId(fitaId);
			if (fita == null) throw new Exception("Fita não encontrada.");
			
			FitaDTO dto = objFactory.createInstance();
			
			dto.setNome(nome);
			dto.setValor(valor);
			
			fita.update(dto);
			
			fitaService.atualizar(fita);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerFita(Long fitaId) {
		try {
			Fita fita = fitaService.listarId(fitaId);
			
			if (fita == null) throw new Exception("Fita não encontrado.");
			
			fitaService.remover(fita);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Fita buscarFita(Long fitaId) {
		try {
			return fitaService.listarId(fitaId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarFita() {
		fitaService.listar();
	}
}