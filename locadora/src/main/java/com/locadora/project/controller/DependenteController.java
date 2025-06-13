package com.locadora.project.controller;

import com.locadora.project.model.Cliente;
import com.locadora.project.model.Dependente;
import com.locadora.project.dto.DependenteDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;
import com.locadora.project.util.Groups.*;

import com.locadora.project.services.ClienteService;
import com.locadora.project.services.DependenteService;

public class DependenteController extends ValidatorUtil<DependenteDTO> {
	private DependenteDTO prototype = new DependenteDTO();
	private ObjectFactory<DependenteDTO> objFactory;
	
	private final ClienteService clienteService = new ClienteService(HibernateUtil.getSessionFactory());
	private final DependenteService dependenteService = new DependenteService(HibernateUtil.getSessionFactory());
	
	public DependenteController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarDependente(String nome, Integer idade, String genero, Long clienteId) {
		DependenteDTO dto = objFactory.createInstance();
		
		dto.setNome(nome);
		dto.setIdade(idade);
		dto.setGenero(genero);
		dto.setClienteId(clienteId);
		
		try {
			if (super.validate(dto, OnCreate.class) == 0) {
				Cliente cliente = clienteService.listarId(dto.getClienteId());
				if (cliente == null) throw new Exception("Cliente não encontrado.");
				
				Dependente dependente = new Dependente(dto);
				
				dependente.setCliente(cliente);
				cliente.getDependente().add(dependente);
				
				dependenteService.cadastrar(dependente);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarDependente(Long dependenteId, String nome, Integer idade, String genero) {
		try {
			Dependente dependente = dependenteService.listarId(dependenteId);
			if (dependente == null) throw new Exception("Dependente não encontrado.");
			
			DependenteDTO dto = objFactory.createInstance();
			
			dto.setNome(nome);
			
			dependente.update(dto);
			
			dependenteService.atualizar(dependente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerDependente(Long dependenteId) {
		try {
			Dependente dependente = dependenteService.listarId(dependenteId);
			
			if (dependente == null) throw new Exception("Dependente não encontrado.");
			
			dependenteService.remover(dependente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Dependente buscarDependente(Long dependenteId) {
		try {
			return dependenteService.listarId(dependenteId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarDependente() {
		dependenteService.listar();
	}
}