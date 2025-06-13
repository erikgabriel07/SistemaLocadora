package com.locadora.project.controller;

import com.locadora.project.model.Fita;
import com.locadora.project.model.Locacao;
import com.locadora.project.model.FitaLocacao;
import com.locadora.project.dto.FitaLocacaoDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;

import com.locadora.project.services.FitaService;
import com.locadora.project.services.LocacaoService;
import com.locadora.project.services.FitaLocacaoService;

public class FitaLocacaoController extends ValidatorUtil<FitaLocacaoDTO> {
	private FitaLocacaoDTO prototype = new FitaLocacaoDTO();
	private ObjectFactory<FitaLocacaoDTO> objFactory;
	
	private final FitaService fitaService = new FitaService(HibernateUtil.getSessionFactory());
	private final LocacaoService locacaoService = new LocacaoService(HibernateUtil.getSessionFactory());
	private final FitaLocacaoService fitaLocacaoService = new FitaLocacaoService(HibernateUtil.getSessionFactory());
	
	public FitaLocacaoController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarFitaLocacao(Long locacaoId, Long fitaId) {
		FitaLocacaoDTO dto = objFactory.createInstance();
		
		dto.setLocacaoId(locacaoId);
		dto.setFitaId(fitaId);
		
		try {
			if (super.validate(dto, null) == 0) {
				Locacao locacao = locacaoService.listarId(dto.getLocacaoId());
				if (locacao == null) throw new Exception("Locação não encontrada.");
				
				Fita fita = fitaService.listarId(dto.getFitaId());
				if (fita == null) throw new Exception("Fita não encontrada.");
				
				FitaLocacao fitaLocacao = new FitaLocacao();
				
				locacao.setFitaLocacao(fitaLocacao);
				fitaLocacao.getLocacao().add(locacao);
				
				fitaLocacao.setFita(fita);
				fita.getFitaLocacao().add(fitaLocacao);
				
				locacaoService.atualizar(locacao);
				
				fitaLocacaoService.cadastrar(fitaLocacao);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarFitaLocacao(Long fitaLocacaoId, Long locacaoId, Long fitaId) {
		try {
			FitaLocacao fitaLocacao = fitaLocacaoService.listarId(fitaLocacaoId);
			if (fitaLocacao == null) throw new Exception("Locação da fita não encontrada.");
			
			FitaLocacaoDTO dto = objFactory.createInstance();
		
			dto.setLocacaoId(locacaoId);
			dto.setFitaId(fitaId);
			
			if (super.validate(dto, null) == 0) {
				Locacao locacao = locacaoService.listarId(dto.getLocacaoId());
				if (locacao == null) {
					locacao.setFitaLocacao(fitaLocacao);
					fitaLocacao.getLocacao().add(locacao);
					locacaoService.atualizar(locacao);
				}
				
				Fita fita = fitaService.listarId(dto.getFitaId());
				if (fita == null) {
					fitaLocacao.setFita(fita);
					fita.getFitaLocacao().add(fitaLocacao);
				}
				
				if (locacao != null || fita != null) fitaLocacaoService.atualizar(fitaLocacao);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerFitaLocacao(Long fitaLocacaoId) {
		try {
			FitaLocacao fitaLocacao = fitaLocacaoService.listarId(fitaLocacaoId);
			
			if (fitaLocacao == null) throw new Exception("Registro não encontrado.");
			
			fitaLocacaoService.remover(fitaLocacao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public FitaLocacao buscarFitaLocacao(Long fitaLocacaoId) {
		try {
			return fitaLocacaoService.listarId(fitaLocacaoId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarFitaLocacao() {
		fitaLocacaoService.listar();
	}
}