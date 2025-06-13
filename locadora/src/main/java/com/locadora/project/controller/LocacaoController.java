package com.locadora.project.controller;

import java.time.LocalDateTime;

import com.locadora.project.model.Locacao;
import com.locadora.project.model.Cliente;
import com.locadora.project.model.FitaLocacao;
import com.locadora.project.dto.LocacaoDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;

import com.locadora.project.services.LocacaoService;
import com.locadora.project.services.ClienteService;
import com.locadora.project.services.FitaLocacaoService;

public class LocacaoController extends ValidatorUtil<LocacaoDTO> {
	private LocacaoDTO prototype = new LocacaoDTO();
	private ObjectFactory<LocacaoDTO> objFactory;
	
	private final LocacaoService locacaoService = new LocacaoService(HibernateUtil.getSessionFactory());
	private final ClienteService clienteService = new ClienteService(HibernateUtil.getSessionFactory());
	private final FitaLocacaoService fitaLocacaoService = new FitaLocacaoService(HibernateUtil.getSessionFactory());
	
	public LocacaoController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarLocacao(LocalDateTime data, Double valorTotal, Long clienteId, Long fitaLocacaoId) {
		LocacaoDTO dto = objFactory.createInstance();
		
		dto.setData(data);
		dto.setValorTotal(valorTotal);
		dto.setClienteId(clienteId);
		dto.setFitaLocacaoId(fitaLocacaoId);
		
		try {
			if (super.validate(dto, null) == 0) {
				Cliente cliente = clienteService.listarId(dto.getClienteId());
				if (cliente == null) throw new Exception("Cliente não encontrada.");
				
				FitaLocacao fitaLocacao = fitaLocacaoService.listarId(dto.getFitaLocacaoId());
				if (fitaLocacao == null) throw new Exception("Registro não encontrada.");
				
				Locacao locacao = new Locacao(dto);
				
				locacao.setCliente(cliente);
				cliente.getLocacao().add(locacao);
				
				locacao.setFitaLocacao(fitaLocacao);
				fitaLocacao.getLocacao().add(locacao);
				
				locacaoService.cadastrar(locacao);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarLocacao(Long locacaoId, LocalDateTime data, Double valorTotal) {
		try {
			Locacao locacao = locacaoService.listarId(locacaoId);
			if (locacao == null) throw new Exception("Locação não encontrada.");
			
			LocacaoDTO dto = objFactory.createInstance();
			
			dto.setData(data);
			dto.setValorTotal(valorTotal);
			
			if (super.validate_field(dto, "data") != 0) return;
			if (super.validate_field(dto, "valor_total") != 0) return;
			
			locacao.update(dto);
			
			locacaoService.update(locacao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerLocacao(Long locacaoId) {
		try {
			Locacao locacao = locacaoService.listarId(locacaoId);
			
			if (locacao == null) throw new Exception("Locacao não encontrado.");
			
			locacaoService.remover(locacao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Locacao buscarLocacao(Long locacaoId) {
		try {
			return locacaoService.listarId(locacaoId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarLocacao() {
		locacaoService.listar();
	}
}