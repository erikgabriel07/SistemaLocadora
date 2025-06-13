package com.locadora.project.controller;

import com.locadora.project.model.Cliente;
import com.locadora.project.dto.ClienteDTO;

import com.locadora.project.util.HibernateUtil;
import com.locadora.project.util.ValidatorUtil;
import com.locadora.project.util.ObjectFactory;
import com.locadora.project.util.Groups.*;

import com.locadora.project.services.ClienteService;

public class ClienteController extends ValidatorUtil<ClienteDTO> {
	private ClienteDTO prototype = new ClienteDTO();
	private ObjectFactory<ClienteDTO> objFactory;
	
	private final ClienteService clienteService = new ClienteService(HibernateUtil.getSessionFactory());
	
	public ClienteController() {
		super();
		objFactory = new ObjectFactory<>(prototype);
	}
	
	public void cadastrarCliente(String nome, Integer idade, String cpf, String email, String telefone, String endereco) {
		ClienteDTO dto = objFactory.createInstance();
		
		dto.setNome(nome);
		dto.setIdade(idade);
		dto.setCPF(cpf.replaceAll("\\D", ""));
		dto.setEmail(email);
		dto.setTelefone(telefone);
		dto.setEndereco(endereco);
		
		try {
			if (super.validate(dto, OnCreate.class) == 0) {
				Cliente cliente = new Cliente(dto);
				clienteService.cadastrar(cliente);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void atualizarCliente(Long clienteId, String nome, Integer idade, String cpf, String email, String telefone, String endereco) {
		try {
			Cliente cliente = clienteService.listarId(clienteId);
			if (cliente == null) throw new Exception("Cliente não encontrado.");
			
			ClienteDTO dto = objFactory.createInstance();
			
			dto.setNome(nome);
			dto.setIdade(idade);
			dto.setCPF(cpf.replaceAll("\\D", ""));
			dto.setEmail(email);
			dto.setTelefone(telefone);
			dto.setEndereco(endereco);
			
			if (cpf != null && !cpf.trim().isEmpty() && super.validate_field(dto, "cpf") != 0) return;
			if (email != null && !email.trim().isEmpty() && super.validate_field(dto, "email") != 0) return;
			if (telefone != null && !telefone.trim().isEmpty() && super.validate_field(dto, "telefone") != 0) return;
			
			cliente.update(dto);
			
			clienteService.atualizar(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void removerCliente(Long clienteId) {
		try {
			Cliente cliente = clienteService.listarId(clienteId);
			
			if (cliente == null) throw new Exception("Cliente não encontrado.");
			
			System.out.print(cliente);
			
			clienteService.remover(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Cliente buscarCliente(Long clienteId) {
		try {
			return clienteService.listarId(clienteId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void listarCliente() {
		clienteService.listar();
	}
	
	public void listarFiltro(String nome, Integer idade, String telefone, String email) {
		ClienteDTO dto = objFactory.createInstance();
		
		dto.setNome(nome);
		dto.setIdade(idade);
		dto.setTelefone(telefone);
		dto.setEmail(email);
		
		if (email != null && !email.trim().isEmpty() && super.validate_field(dto, "email") != 0) return;
		if (telefone != null && !telefone.trim().isEmpty() && super.validate_field(dto, "telefone") != 0) return;
		
		Cliente clienteFiltro = new Cliente(dto);
		
		try {
			clienteService.listarComFiltro(clienteFiltro);
		}  catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarClienteCPF(String cpf) {
		try {
			clienteService.listarClientePorCPF(cpf.replaceAll("\\D", ""));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void listarHistorico(Long clienteId) {
		try {
			clienteService.listarClienteHistorico(clienteId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}